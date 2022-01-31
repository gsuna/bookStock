package com.gsuna.project.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.LockModeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsuna.project.dto.BookDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.entity.Customer;
import com.gsuna.project.entity.Order;
import com.gsuna.project.entity.OrderDetail;
import com.gsuna.project.enums.Status;
import com.gsuna.project.error.ApiError;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.exception.ExceptionCodes;
import com.gsuna.project.repository.BookRepository;
import com.gsuna.project.repository.OrderRepository;
import com.gsuna.project.service.BookService;
import com.gsuna.project.service.CustomerService;
import com.gsuna.project.service.OrderDetailService;
import com.gsuna.project.service.OrderService;
import com.gsuna.project.util.DateUtil;
import com.gsuna.project.util.MapperUtil;
import com.gsuna.project.util.RandomNumberGenerator;
import com.gsuna.project.webservice.client.OrderListRequest;
import com.gsuna.project.webservice.client.OrderRequest;

@Service
@Transactional(rollbackFor = BusinessException.class )
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private MapperUtil mapper;

	@Override
	public ResponseEntity<?> createOrder(OrderListRequest orderRequest) throws BusinessException {
		
		//validations //customerid must , count > 0 , bookDto id must
		Map<String,String> validationErrors = validationOrderRequest(orderRequest);
		if(validationErrors.size()==0) {
			List<Long> bookIdList = orderRequest.getOrderRequestList().stream().
					map(r->r.getBookDto().getId()).collect(Collectors.toList());
			List<Book> bookList = bookRepository.findByIdIn(bookIdList);

			List<OrderRequest> filledRequest = fillBookDto(orderRequest,bookList);
			Order order = saveOrder(orderRequest, filledRequest);
			saveOrderDetail(order, filledRequest);
			return (ResponseEntity<?>) ResponseEntity.ok("Create Order succesfully completed");
		}
		ApiError apiError = new ApiError("400", "validation erorrs", "api/book-management/orders");
		apiError.setValidationErrors(validationErrors);
		return new ResponseEntity<ApiError>(apiError, HttpStatus.BAD_REQUEST);
		
	}

	private Map<String,String> validationOrderRequest(OrderListRequest orderRequest) {
		Map<String,String> validationErrors = new HashMap<>();
		
		if(orderRequest.getCustomerId()==null) {
			validationErrors.put("customerId", "must not be null");
		}
		List<OrderRequest> orderRequestList = orderRequest.getOrderRequestList();
		if(orderRequestList.stream().filter(request->request.getCount()<=0).count()>0) {
			validationErrors.put("count", "must be bigger than 0");
		}
		if(orderRequestList.stream().filter(request->request.getBookDto()==null || request.getBookDto().getId()==null).count()>0) {
			validationErrors.put("bookDtoId", "must not be null");
		}
		return validationErrors;
	}

	private void saveOrderDetail(Order order, List<OrderRequest> filledRequest) {
		for (OrderRequest request : filledRequest) {
			updateBookCount(request);
			
			int requestCount = request.getCount();
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(mapper.dtoToBook(request.getBookDto()));
			orderDetail.setCount(requestCount);
			orderDetail.setStatus(Status.ACTIVE);
			orderDetail.setOrder(order);
			orderDetailService.save(orderDetail);
		}
	}

	private void updateBookCount(OrderRequest request) {
		int requestCount = request.getCount();
		//race condition via optimistic lock
		Book book = bookService.getEntityManager().find(Book.class, request.getBookDto().getId(), LockModeType.OPTIMISTIC);
		if(book.getCount()>=requestCount) {
			book.setCount(book.getCount()-requestCount);
			book.setVersion(book.getVersion()+1);
			bookRepository.save(book);
			bookService.getEntityManager().flush();
		}else {
			throw new BusinessException(ExceptionCodes.INSUFFICENT_STOCK_CODE,ExceptionCodes.INSUFFICENT_STOCK_MESSAGE);
		}
	}

	private Order saveOrder(OrderListRequest orderRequest,List<OrderRequest> filledRequest) {
		BigDecimal totalAmount = filledRequest.stream().
				map(orderDetail->orderDetail.getBookDto().getPrice().multiply(BigDecimal.valueOf(orderDetail.getCount()))).
				reduce(BigDecimal.ZERO, BigDecimal::add);
		Date now =new Date();
		Order order = new Order();
		Customer customer = customerService.findById(orderRequest.getCustomerId());
		order.setCustomer(customer);
		order.setOrderDate(now);
		order.setMonth(DateUtil.getMonth(now));
		order.setOrderNo(RandomNumberGenerator.generateNumber());
		order.setStatus(Status.ACTIVE);
		order.setTotalAmount(totalAmount);
		order = orderRepository.save(order);
		return order;
	}
	
	private List<OrderRequest> fillBookDto(OrderListRequest orderRequest,List<Book> bookList) {
		List<BookDto> bookDtoList = mapper.bookListToDtoList(bookList);
		return bookDtoList.stream().map(book-> {
			OrderRequest orderReq = new OrderRequest();
			int count = orderRequest.getOrderRequestList().stream().filter(request->request.getBookDto().getId().equals(book.getId())).findFirst().get().getCount();
			orderReq.setCount(count);
			orderReq.setBookDto(book);
			return orderReq;
		}).collect(Collectors.toList());
	}

	@Override
	public List<Order> getOrdersByInterval(Date fromDate, Date toDate) {
		return orderRepository.findByOrderDateGreaterThanEqualAndOrderDateLessThanEqual(fromDate, toDate);
	}

	
	
}

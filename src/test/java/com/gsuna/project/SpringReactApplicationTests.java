package com.gsuna.project;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gsuna.project.dto.BookDto;
import com.gsuna.project.dto.CustomerDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.entity.Customer;
import com.gsuna.project.entity.Order;
import com.gsuna.project.service.BookService;
import com.gsuna.project.service.CustomerService;
import com.gsuna.project.service.OrderService;
import com.gsuna.project.util.DateUtil;
import com.gsuna.project.util.MapperUtil;
import com.gsuna.project.webservice.client.OrderListRequest;
import com.gsuna.project.webservice.client.OrderRequest;

@SpringBootTest
class SpringReactApplicationTests {
	
	@Autowired
	MapperUtil mapper;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	OrderService orderService;
	
	@Test
	void testMapper() {
		Customer customer = new Customer();
		customer.setName("Ahmet");
		customer.setSurname("Yazgi");
		CustomerDto customerDto = mapper.customerToDto(customer);
		assertThat(customerDto.getName()).isNotEmpty();
	}
	
	@Test
	void testCustomerServiceSaveAndGetList() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName("Ahmet");
		customerDto.setSurname("Yazgi");
		customerService.saveDto(customerDto);
		
		List<CustomerDto> customerDtoList = customerService.findAllDto();
		List<CustomerDto> customerDtoFilterList = customerDtoList.stream().
				filter(c-> c.getName().equals("Ahmet") && c.getSurname().equals("Yazgi") ).
				collect(Collectors.toList());
		assertThat(customerDtoFilterList.size()>0);
	}
	
	@Test
	void testBookServiceUpdateAndGetList() {
		Book book =new Book();
		book.setAuthor("Cem");
		book.setCount(3);
		book.setPublishYear(2003);
		bookService.save(book);
		
		BookDto bookDto = mapper.bookToDto(book);
		bookDto.setCount(25);
		bookService.updateDto(book.getId(), bookDto);
		List<Book> bookList = bookService.findAll();
		Book updatedBook = bookList.stream().
				filter(c-> c.getAuthor().equals("Cem") && c.getPublishYear() == 2003).
				findFirst().get();
		assertThat(updatedBook.getCount()==25);
	}
	@Test
	void testOrder() throws Exception {
		bookSave();
		customerSave();
		createOrderAndDetails();
		
		List<Book> afterOrderBookList = bookService.findAll();
		Book afterOrderBook = afterOrderBookList.stream().
				filter(c-> c.getAuthor().equals("Pelin") && c.getPublishYear() == 2010).
				findFirst().get();
		
//		35-11=24 the rest book count
		assertThat(afterOrderBook.getCount()==24);
	}

	private void createOrderAndDetails() throws Exception {
		List<Book> bookList = bookService.findAll();
		Book book = bookList.stream().
				filter(c-> c.getAuthor().equals("Pelin") && c.getPublishYear() == 2010).
				findFirst().get();
		
		List<CustomerDto> customerDtoList = customerService.findAllDto();
		CustomerDto customerDto = customerDtoList.stream().
				filter(c-> c.getName().equals("Derya") && c.getSurname().equals("Hisar") ).
				findFirst().get();
		
		OrderListRequest request = new OrderListRequest();
		List<OrderRequest> orderRequestList = new ArrayList<>();
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setBookDto(mapper.bookToDto(book));
		orderRequest.setCount(11);
		request.setOrderRequestList(orderRequestList);
		request.setCustomerId(customerDto.getId());
		orderService.createOrder(request);
	}
	
	void bookSave() {
		Book book =new Book();
		book.setAuthor("Pelin");
		book.setCount(35);
		book.setPublishYear(2010);
		bookService.save(book);
	}
	void customerSave() {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName("Derya");
		customerDto.setSurname("Hisar");
		customerService.saveDto(customerDto);
	}
	
	@Test
	void getOrderBetweenDay() throws Exception {
		bookSave();
		customerSave();
		createOrderAndDetails();
		
		Date now = new Date();
		Date fromDate =DateUtil.addDay(now, -1);
		Date toDate =DateUtil.addDay(now, 1);
		List<Order> orderList = orderService.getOrdersByInterval(fromDate, toDate);
		
		assertThat(orderList.stream().filter(order->order.getOrderDate().getTime()<=toDate.getTime() && order.getOrderDate().getTime()>=fromDate.getTime()).count()>0);
	}
	
}

package com.gsuna.project.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.gsuna.project.entity.Order;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.webservice.client.OrderListRequest;

public interface OrderService extends BaseService {
	
	ResponseEntity<?> createOrder(OrderListRequest orderListRequest) throws Exception;
	List<Order> getOrdersByInterval(Date fromDate, Date toDate) ;
	Order findById(Long orderId) throws BusinessException;
	
}

package com.gsuna.project.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gsuna.project.entity.Order;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.service.OrderService;
import com.gsuna.project.webservice.client.OrderListRequest;

@RestController
public class OrderController extends BaseController {
	
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orders")
	public List<Order> getOrdersByInterval(@RequestParam("from") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
			@RequestParam("to") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate){
		return orderService.getOrdersByInterval(fromDate, toDate);
	}
	
	@GetMapping("/orders/{id}")
	public Order getOrdersById(@PathVariable("id") Long id) throws BusinessException{
		return orderService.findById(id);
	}
	
	@PostMapping("/orders")
	public ResponseEntity<?> createOrder(@Valid @RequestBody OrderListRequest orderListRequest) throws Exception {
		return orderService.createOrder(orderListRequest);
	}
	
}

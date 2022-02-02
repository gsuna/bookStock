package com.gsuna.project.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gsuna.project.entity.OrderDetail;
import com.gsuna.project.service.OrderDetailService;

@RestController
public class OrderDetailController extends BaseController {
	
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@GetMapping("/orderDetails/{orderId}")
	public List<OrderDetail> createOrder(@PathVariable("orderId") Long orderId) throws Exception {
		return orderDetailService.getOrderDetails(orderId);
	}
	
}

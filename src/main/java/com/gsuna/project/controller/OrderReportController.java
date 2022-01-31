package com.gsuna.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsuna.project.report.OrderReport;
import com.gsuna.project.service.OrderReportService;

@RestController
public class OrderReportController extends BaseController {
	
	
	@Autowired
	private OrderReportService orderReportService;
	
	@GetMapping("/orderDetails")
	public List<OrderReport> createOrderReport() throws Exception {
		return orderReportService.createOrderReport();
	}
	
}

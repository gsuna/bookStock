package com.gsuna.project.service;

import java.util.List;

import com.gsuna.project.report.OrderReport;

public interface OrderReportService extends BaseService {
	
	List<OrderReport> createOrderReport() ;
}

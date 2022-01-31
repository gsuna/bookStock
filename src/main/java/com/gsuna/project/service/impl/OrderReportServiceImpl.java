package com.gsuna.project.service.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import com.gsuna.project.report.OrderReport;
import com.gsuna.project.service.OrderReportService;

@Service
public class OrderReportServiceImpl extends BaseServiceImpl implements OrderReportService{
	
	@Override
	public List<OrderReport> createOrderReport() {
		 TypedQuery<OrderReport> query = getEntityManager().createNamedQuery(
				"Order.summaryOrderMonthly",
				OrderReport.class);
		return query.getResultList();
	}

}

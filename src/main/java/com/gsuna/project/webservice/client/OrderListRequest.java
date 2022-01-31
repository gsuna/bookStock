package com.gsuna.project.webservice.client;

import java.util.List;

public class OrderListRequest {
	
	private List<OrderRequest> orderRequestList;
	private Long customerId;

	public List<OrderRequest> getOrderRequestList() {
		return orderRequestList;
	}

	public void setOrderRequestList(List<OrderRequest> orderRequestList) {
		this.orderRequestList = orderRequestList;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
}

package com.gsuna.project.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDto extends BaseEntityDto {
	
	private Long orderNo;
	
	private Date orderDate;
	
	private BigDecimal totalAmount;
	
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}

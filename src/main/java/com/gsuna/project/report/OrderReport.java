package com.gsuna.project.report;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//integer month can convert to month name
	private int month;
	private long totalOrderCount;
	private long totalBookCount;
	private BigDecimal totalPurchasedAmount;
	
	public OrderReport() {
		
	}
	
	public OrderReport(int month, long totalOrderCount, long totalBookCount, BigDecimal totalPurchasedAmount) {
		this.month = month;
		this.totalOrderCount = totalOrderCount;
		this.totalBookCount = totalBookCount;
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
	
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public long getTotalOrderCount() {
		return totalOrderCount;
	}
	public void setTotalOrderCount(long totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}
	public long getTotalBookCount() {
		return totalBookCount;
	}
	public void setTotalBookCount(long totalBookCount) {
		this.totalBookCount = totalBookCount;
	}
	public BigDecimal getTotalPurchasedAmount() {
		return totalPurchasedAmount;
	}
	public void setTotalPurchasedAmount(BigDecimal totalPurchasedAmount) {
		this.totalPurchasedAmount = totalPurchasedAmount;
	}
	
	

}

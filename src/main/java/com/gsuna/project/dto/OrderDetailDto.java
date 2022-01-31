package com.gsuna.project.dto;

import java.math.BigDecimal;

import com.gsuna.project.entity.Book;

public class OrderDetailDto extends BaseEntityDto {
	
	private int count;  
	
	private BigDecimal amount;  
	
	private OrderDto order;
	
	private Book book;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public OrderDto getOrder() {
		return order;
	}
	public void setOrder(OrderDto order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}  
	
}

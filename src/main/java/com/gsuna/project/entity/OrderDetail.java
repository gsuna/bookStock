package com.gsuna.project.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(
            name = "Order.summaryOrderMonthly",
            query = "select NEW com.gsuna.project.report.OrderReport(o.month,COUNT(DISTINCT o.id), COUNT(od.count), SUM(o.totalAmount) ) " +
                    " FROM OrderDetail od JOIN od.order o" +
                    " GROUP BY o.month" 
    )
})
public class OrderDetail extends BaseEntity {
	
	@Column
	private int count;  
	
	@Column
	private BigDecimal amount;  
	
	@JoinColumn
	@ManyToOne
	private Order order;
	
	@JoinColumn
	@OneToOne
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
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}  
	
}

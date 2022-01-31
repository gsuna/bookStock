package com.gsuna.project.webservice.client;

import com.gsuna.project.dto.BookDto;

public class OrderRequest {
	
	private BookDto bookDto;
	private int count;

	public BookDto getBookDto() {
		return bookDto;
	}
	
	public void setBookDto(BookDto bookDto) {
		this.bookDto = bookDto;
	} 
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}

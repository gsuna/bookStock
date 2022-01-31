package com.gsuna.project.service;

import java.util.List;

import com.gsuna.project.dto.BookDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.exception.BusinessException;

public interface BookService extends BaseService {
	
	List<Book> findAll() ;
	void save(Book book);
	void updateDto(Long bookId,BookDto book) throws BusinessException;
	
}

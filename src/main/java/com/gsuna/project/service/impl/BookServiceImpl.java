package com.gsuna.project.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsuna.project.dto.BookDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.exception.ExceptionCodes;
import com.gsuna.project.repository.BookRepository;
import com.gsuna.project.service.BookService;
import com.gsuna.project.util.MapperUtil;

@Service
public class BookServiceImpl extends BaseServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private MapperUtil mapper;

	@Override
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	@Override
	public void updateDto(Long bookId,BookDto bookDto) throws BusinessException {
		bookRepository.findById(bookId).orElseThrow(()->new BusinessException(ExceptionCodes.NOT_FOUND_CODE, ExceptionCodes.NOT_FOUND_MESSAGE,"Book"));
		Book book = mapper.dtoToBook(bookDto);
		book.setId(bookId);
		bookRepository.save(book);
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}
	
}

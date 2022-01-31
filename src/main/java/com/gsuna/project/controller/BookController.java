package com.gsuna.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsuna.project.dto.BookDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.service.BookService;
import com.gsuna.project.util.GenericResponse;

@RestController
public class BookController extends BaseController {
	
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/books")
	public List<Book> getBooks() {
		return bookService.findAll();
	}
	
	@PostMapping("/books")
	public GenericResponse saveBook(@RequestBody Book book) {
		bookService.save(book);
		return new GenericResponse("Book created");
	}
	
	@PutMapping("/books/{id}")
	public GenericResponse updateBook(@PathVariable(value = "id") Long bookId,@RequestBody BookDto book) throws BusinessException {
		bookService.updateDto(bookId,book);
		return new GenericResponse("Book updated");
	}
}

package com.gsuna.project.util;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.gsuna.project.dto.BookDto;
import com.gsuna.project.dto.CustomerDto;
import com.gsuna.project.entity.Book;
import com.gsuna.project.entity.Customer;


@Mapper(componentModel = "spring")
public interface MapperUtil {
	MapperUtil MAPPER = Mappers.getMapper(MapperUtil.class);
	
	@Mapping(target = "createDate", ignore = true)
	public Customer dtoToCustomer(CustomerDto customerDto);
	public CustomerDto customerToDto(Customer customer);
	public List<CustomerDto> customerListToDtoList(List<Customer> customerList);
	
	@Mapping(target = "createDate", ignore = true)
	public Book dtoToBook(BookDto bookDto);
	public BookDto bookToDto(Book book);
	public List<BookDto> bookListToDtoList(List<Book> bookList);

}

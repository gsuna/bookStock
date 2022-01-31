package com.gsuna.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuna.project.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long >{
	
	List<Book> findByIdIn(List<Long> bookIdList);

}

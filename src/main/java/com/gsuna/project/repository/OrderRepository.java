package com.gsuna.project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuna.project.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long >{
	
	List<Order> findByOrderDateGreaterThanEqualAndOrderDateLessThanEqual(Date fromDate,Date toDate);
	
}

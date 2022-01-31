package com.gsuna.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuna.project.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long >{
	
//	List<Customer> findByGender(GenderType genderType);

}

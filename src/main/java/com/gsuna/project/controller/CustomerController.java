package com.gsuna.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsuna.project.dto.CustomerDto;
import com.gsuna.project.entity.Customer;
import com.gsuna.project.service.CustomerService;
import com.gsuna.project.util.GenericResponse;

@RestController
public class CustomerController extends BaseController{
	
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	public Page<Customer> getCustomers(Pageable pageable) {
		return customerService.findAllDtoPageable(pageable);
	}
	
	@PostMapping("/customers")
	public GenericResponse createCustomer(@RequestBody CustomerDto customerDto) {
		customerService.saveDto(customerDto);
		return new GenericResponse("Customer created");
	}
	
}

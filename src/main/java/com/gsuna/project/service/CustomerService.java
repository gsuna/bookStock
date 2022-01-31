package com.gsuna.project.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gsuna.project.dto.CustomerDto;
import com.gsuna.project.entity.Customer;
import com.gsuna.project.exception.BusinessException;

public interface CustomerService extends BaseService {
	
	void saveDto(CustomerDto customerDto);
	Page<Customer> findAllDtoPageable(Pageable pageable);
	List<CustomerDto> findAllDto();
	Customer findById(Long customerId) throws BusinessException;
	
}

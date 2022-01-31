package com.gsuna.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gsuna.project.dto.CustomerDto;
import com.gsuna.project.entity.Customer;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.exception.ExceptionCodes;
import com.gsuna.project.repository.CustomerRepository;
import com.gsuna.project.service.CustomerService;
import com.gsuna.project.util.MapperUtil;

@Service
public class CustomerServiceImpl extends BaseServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MapperUtil mapper;
	
	@Override
	public void saveDto(CustomerDto customerDto) {
		Customer customer = mapper.dtoToCustomer(customerDto);
		customerRepository.save(customer);
	}

	@Override
	public Page<Customer> findAllDtoPageable(Pageable pageable) {
		Page<Customer> customerList = customerRepository.findAll(pageable);
		return customerList;
	}

	@Override
	public Customer findById(Long customerId) throws BusinessException {
		return customerRepository.findById(customerId).orElseThrow(()->new BusinessException(ExceptionCodes.NOT_FOUND_CODE, ExceptionCodes.NOT_FOUND_MESSAGE,"Book"));
	}

	@Override
	public List<CustomerDto> findAllDto() {
		List<Customer> customerList = customerRepository.findAll();
		return mapper.customerListToDtoList(customerList);
	}
	
}

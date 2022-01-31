package com.gsuna.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsuna.project.entity.OrderDetail;
import com.gsuna.project.exception.BusinessException;
import com.gsuna.project.exception.ExceptionCodes;
import com.gsuna.project.repository.OrderDetailRepository;
import com.gsuna.project.service.OrderDetailService;
import com.gsuna.project.util.CollectionUtil;

@Service
public class OrderDetailServiceImpl extends BaseServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public void save(OrderDetail orderDetail) {
		orderDetailRepository.save(orderDetail);
	}

	@Override
	public List<OrderDetail> getOrderDetails(Long orderId) throws BusinessException {
		List<OrderDetail> orderList = orderDetailRepository.findByOrderId(orderId);
		if(CollectionUtil.isEmpty(orderList)) {
			throw new BusinessException(ExceptionCodes.NOT_FOUND_CODE, ExceptionCodes.NOT_FOUND_MESSAGE,"OrderDetail");
		}
		return orderList;
	}
	
}

package com.gsuna.project.service;

import java.util.List;

import com.gsuna.project.entity.OrderDetail;
import com.gsuna.project.exception.BusinessException;

public interface OrderDetailService extends BaseService {
	
	void save(OrderDetail orderDetail);
	List<OrderDetail> getOrderDetails(Long orderId) throws BusinessException;
	
}

package com.raghava.mockito.service;

import com.raghava.mockito.dto.Order;
import com.raghava.mockito.exception.OrderServiceException;

public interface OrderService {
	public boolean placeOrder(Order order) throws OrderServiceException;

	public boolean cancelOrder(int orderId) throws OrderServiceException;

	public boolean deleteOrder(int orderId) throws OrderServiceException;
}

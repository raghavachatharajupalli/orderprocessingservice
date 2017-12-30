package com.raghava.mockito.service;

import java.sql.SQLException;

import com.raghava.mockito.dao.OrderDAO;
import com.raghava.mockito.dto.Order;
import com.raghava.mockito.exception.OrderServiceException;

public class OrderServiceImpl implements OrderService {
	private OrderDAO orderDao;

	@Override
	public boolean placeOrder(Order order) throws OrderServiceException {
		try {
			int orderId = orderDao.create(order);
			if(orderId==0){
				return false;
			}
		} catch (SQLException e) {
			throw new OrderServiceException(e);
		}
		return true;
	}

	@Override
	public boolean cancelOrder(int orderId) throws OrderServiceException {
		try {
			Order order=orderDao.read(orderId);
			order.setStatus("Cancelled");
			orderId = orderDao.update(order);
			if(orderId==0){
				return false;
			}
		} catch (SQLException e) {
			throw new OrderServiceException(e);
		}
		return true;
	}

	@Override
	public boolean deleteOrder(int orderId) throws OrderServiceException {
		try {
			orderId = orderDao.delete(orderId);
			if(orderId==0){
				return false;
			}
		} catch (SQLException e) {
			throw new OrderServiceException(e);
		}
		return true;
	}

	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

}

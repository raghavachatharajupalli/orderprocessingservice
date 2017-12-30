package com.raghava.mockito.dao;

import java.sql.SQLException;

import com.raghava.mockito.dto.Order;

public interface OrderDAO {
	public int create(Order order) throws SQLException;

	public Order read(int orderId) throws SQLException;

	public int update(Order order) throws SQLException;

	public int delete(int orderId) throws SQLException;
}

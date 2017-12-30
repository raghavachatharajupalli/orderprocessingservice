package com.raghava.mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.raghava.mockito.dao.OrderDAO;
import com.raghava.mockito.dto.Order;
import com.raghava.mockito.exception.OrderServiceException;
import com.raghava.mockito.service.OrderServiceImpl;

public class OrderServiceImplTest {
	@Mock
	private OrderDAO orderDao;
	private OrderServiceImpl orderService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		orderService = new OrderServiceImpl();
		orderService.setOrderDao(orderDao);
	}

	@Test
	public void placeOrder_shouldCreateOrder() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(1);
		order.setStatus("created");

		when(orderDao.create(order)).thenReturn(1);

		boolean result = orderService.placeOrder(order);
		assertTrue(result);
		// verifies it is called at least once
		verify(orderDao).create(order);

	}

	@Test
	public void placeOrder_shouldNotCreateOrder() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(1);
		order.setStatus("created");

		when(orderDao.create(order)).thenReturn(0);

		boolean result = orderService.placeOrder(order);
		assertFalse(result);
		// verifies it is called at least once
		verify(orderDao).create(order);

	}

	@Test(expected = OrderServiceException.class)
	public void placeOrder_shouldThowServiceException() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(1);
		order.setStatus("created");
		when(orderDao.create(order)).thenThrow(SQLException.class);
		orderService.placeOrder(order);

	}

	@Test
	public void cancel_order_shouldCancelTheOrder() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(123);
		order.setStatus("In Progress");

		when(orderDao.read(123)).thenReturn(order);
		when(orderDao.update(order)).thenReturn(123);
		boolean cancelOrder = orderService.cancelOrder(123);
		assertTrue(cancelOrder);
		verify(orderDao).read(123);
		verify(orderDao).update(order);

	}

	@Test
	public void cancel_order_shouldNotCancelTheOrder() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(123);
		order.setStatus("In Progress");

		when(orderDao.read(123)).thenReturn(order);
		when(orderDao.update(order)).thenReturn(0);
		boolean cancelOrder = orderService.cancelOrder(123);
		assertFalse(cancelOrder);
		verify(orderDao).read(123);
		verify(orderDao).update(order);

	}

	@Test(expected = OrderServiceException.class)
	public void cancel_order_shouldThrowExceptionOnRead() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(123);
		order.setStatus("In Progress");

		when(orderDao.read(123)).thenThrow(SQLException.class);
		orderService.cancelOrder(123);

	}
	@Test(expected = OrderServiceException.class)
	public void cancel_order_shouldThrowExceptionOnUpdate() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(123);
		order.setStatus("In Progress");

		when(orderDao.read(123)).thenReturn(order);
		when(orderDao.update(order)).thenThrow(SQLException.class);
		orderService.cancelOrder(123);
	}
	
	@Test
	public void deleteOrder_shouldDeleteOrder() throws SQLException,
			OrderServiceException {

		Order order = new Order();
		order.setOrderId(1);
		order.setStatus("created");

		when(orderDao.delete(123)).thenReturn(123);

		boolean result = orderService.deleteOrder(123);
		assertTrue(result);
		// verifies it is called at least once
		verify(orderDao).delete(123);

	}

}

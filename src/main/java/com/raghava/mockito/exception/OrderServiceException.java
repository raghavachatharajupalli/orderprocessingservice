package com.raghava.mockito.exception;

import java.sql.SQLException;

public class OrderServiceException extends Exception {

	public OrderServiceException(SQLException e) {
		super(e);
	}

	private static final long serialVersionUID = 1L;

}

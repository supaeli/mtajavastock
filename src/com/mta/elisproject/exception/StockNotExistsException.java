package com.mta.elisproject.exception;

public class StockNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public StockNotExistsException(String symbol) {
		super("Stock " + symbol + " doesn't exists in the portfolio!");
	}
}

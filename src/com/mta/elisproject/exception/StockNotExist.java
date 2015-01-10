package com.mta.elisproject.exception;

public class StockNotExist extends Exception {
	public StockNotExist(String symbol){
		super("Stock " + symbol + " does not exist in the portfolio") ;
	}
}

package com.mta.elisproject.exception;


public class StockAlreadyExist extends Exception {
	public StockAlreadyExist(String symbol){
		super("stock " + symbol + " already exists") ;
	}
}

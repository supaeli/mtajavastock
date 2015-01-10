package com.mta.elisproject.exception;

public class PortfolioFullException extends Exception {
	public PortfolioFullException(){ 
		super("Portfolio is full, cannot add more stocks.") ;
	}
}

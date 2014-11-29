package com.mta.elisproject.model;

import java.util.Date;

import com.mta.elisproject.Stock;

public class Portfolio {
	final static int MAX_PORTFOLIO_SIZE = 5 ;
	
	Stock stocks[] ;
	StockStatus[] stocksStatus ; 
	
	stocks = new Stock[MAX_PORTFOLIO_SIZE] ;
	stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ;
	
	public void addStock(Stock stock, int portfolioSize) {
		if(portfolioSize > 3)
		{
			stocks[portfolioSize] = stock ;
			portfolioSize++ ;
			return;
		}
		else
		{
			//error msg
			return;
		}
	}
	public Stock[] getStocks() {
		return stocks; 
	}
	private class StockStatus{
		final static int DO_NOTHING = 0 ; 
		final static int BUY = 1 ;
		final static int SELL = 2 ;
		
		public String symbol ;
		public float currentBid ;
		public float currentAsk ;
		public Date date ;
		public int recommendation ;
		public int stockQuantity ;
		
	}
	
}

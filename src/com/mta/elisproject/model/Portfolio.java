package com.mta.elisproject.model;


import java.util.Date;

public class Portfolio {
	final static int MAX_PORTFOLIO_SIZE = 5 ;
	
	private String title = new String("Default portfolio");
	private Stock stocks[] = new Stock[MAX_PORTFOLIO_SIZE] ;
	private StockStatus[] stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ; 
	private int portfolioSize = 0 ;
	
	public Portfolio () {
		stocks = new Stock[MAX_PORTFOLIO_SIZE] ;
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ;
	}
	

	public Stock[] getStocks() {
		return stocks; 
	}
	public StockStatus[] getStockStatus(){
		return this.stocksStatus ;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String portfolioTitle){
		title = portfolioTitle ;
	}
	public int getPortfolioSize(){
		return portfolioSize;
	}
	public String getHtmlString() {
		int i =0 ;
		String resStr = new String() ;
		resStr = "<h1>"+title+"</h1>" ;
		while(stocks[i] != null && i < MAX_PORTFOLIO_SIZE){
			resStr = resStr + "<br>" + stocks[i].getHtmlDescription() ;// getHtmlDescription(stocks );// still cannot figure y cant i use noted method- get a 'not defined for type portfolio'->A: didn't approach stock.function, did just function which is wrong.
			i++ ;
		}
		return resStr; 
	}
	public void addStock(Stock stock) {
		
		if(portfolioSize >= 0 && portfolioSize < MAX_PORTFOLIO_SIZE-1)//array is not full
		{
			stocks[portfolioSize] = stock ;
			portfolioSize++ ;
			return ;
		}
		else//array is full
		{
			//present array is full msg
			return ;
		}
	}
	
	public class StockStatus{
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
	
	


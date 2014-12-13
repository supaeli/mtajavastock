package com.mta.elisproject.model;

import java.util.Date;
/**
 * 
 * @author sup4eli
 * a portfolio of Stock instances, holds 
 * multiple Stock objects
 * can add stock to a portfolio, present 
 * the whole portfolio in html string 
 * and has inner class for stock status, explained below.
 *
 */
public class Portfolio {
	final static int MAX_PORTFOLIO_SIZE = 5 ;
	
	private String title ;
	private Stock stocks[] ;
	private StockStatus[] stocksStatus ; 
	private int portfolioSize = 0 ;
	
	
	public Portfolio (){
		setTitle("Portfolio #1");
		 stocks = new Stock[MAX_PORTFOLIO_SIZE];
		 stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ;
	}
	
	public Portfolio (Portfolio portfolio) {//copy c'tor
		this(portfolio.getTitle(), portfolio.getStockStatus(), portfolio.getStocks()) ; 
		
	}
	public Portfolio(String title,StockStatus[] stockStatus, Stock[] stocks ){
		 setTitle(title);
		 this.stocks = new Stock[MAX_PORTFOLIO_SIZE] ;
	     this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ;
		copyStocksArray(stocks, getLogicalSizeStocks(stocks));
		copyStockStatusArray(stockStatus, getLogicalSizeStatus(stockStatus));
	}
	private int getLogicalSizeStocks(Stock[] stocks){//add description later
		int i =0 ;
		while(stocks[i]!=null && i<MAX_PORTFOLIO_SIZE){
			i++;
		}
		return i;
	}
	private int getLogicalSizeStatus(StockStatus[] OtherstockStatus){//add description later
		int i =0 ;
		while(OtherstockStatus[i]!=null && i<MAX_PORTFOLIO_SIZE){
			i++;
		}
		return i;
	}
	private void copyStocksArray(Stock[] stocks, int size){
		
		if(size > 0 && size<= MAX_PORTFOLIO_SIZE-1)//array is not full
		{
		for (int i = 0; i<size; i++){//len dont give logical(3) but physical(5)->find func!
			this.addStock(stocks[i]);  
		}
		}
		else{
			
		}
		//this.stocks[i] = new Stock(stocks[i]) ;
		  //this.portfolioSize++; 
		  // does the same
	}
public void copyStockStatusArray(StockStatus[] stockStatus,int size){
		
		if(size >= 0 && size<= MAX_PORTFOLIO_SIZE-1 && stockStatus != null)//array is not full and not pointing to null
		{
			for(int i =0 ;i <=size ;i++){
					i++; 
				
				stocksStatus[i] = new StockStatus(stockStatus[i])  ;
			}
		}
		else//array is full
		{
			//present array is full msg
			return ;
		}
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
	public void setPortfolioSize(int size){
		portfolioSize = size ;
	}
	public int getPortfolioSize(){
		return portfolioSize;
	}
	/**
	 * purpose: return String display of Porfolio object
	 * @return as mentioned above
	 */
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
	/**
	 * add Stock object to a Stock array 
	 * @param stock - Stock object to add
	 */
	public void addStock(Stock stock) {
		
		if(portfolioSize >= 0 && portfolioSize < MAX_PORTFOLIO_SIZE-1)//array is not full
		{
			stocks[portfolioSize] = new Stock(stock)  ;
			portfolioSize++ ;
			return ;
		}
		else//array is full
		{
			//present array is full msg
			return ;
		}
	}
	
	/**
	 * 
	 * @author sup4eli
	 * description: holds Stock object data fields
	 * and other other data concerns the instance such as:
	 * whether buy or not and stock holding number.
	 *
	 */
	public class StockStatus{
		final static int DO_NOTHING = 0 ; 
		final static int BUY = 1 ;
		final static int SELL = 2 ;
		
		public String symbol = new String() ;
		public float currentBid ;
		public float currentAsk ;
		public Date date = new Date() ;
		public int recommendation ;
		public int stockQuantity ;
		
		public StockStatus(){
			
			setSymbol("N/A") ;
			setCurrentBid(0) ;
			setCurrentAsk(0) ;
			setDate(getDate()) ;
			setRecommendation(0) ;
			setStockQuantity(0) ;
		}
		public StockStatus(String symbol, float currentBid, float currentAsk, Date date, int recommendation, int stockQuantity){//c'tor
			setSymbol( symbol) ;
			setCurrentBid(currentBid) ;
			setCurrentAsk(currentAsk) ;
			setDate(date) ;
			setRecommendation(recommendation) ;
			setStockQuantity(stockQuantity) ;
		}
		
		public StockStatus(StockStatus stockStatus){//copy c'tor
			this(stockStatus.getSymbol(),
					stockStatus.getCurrentBid(),
					stockStatus.getCurrentAsk(),
					stockStatus.getDate(),
					stockStatus.getRecommendation(),
					stockStatus.getStockQuantity()) ;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol =new String (symbol);
		}
		public float getCurrentBid() {
			return currentBid;
		}
		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}
		public float getCurrentAsk() {
			return currentAsk;
		}
		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public int getRecommendation() {
			return recommendation;
		}
		public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
		public int getStockQuantity() {
			return stockQuantity;
		}
		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}
		
	}	
}
	
	


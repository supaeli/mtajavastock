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
	enum ALGO_RECOMMENDATION{DO_NOTHING,BUY, SELL};
	private String title ;
	private StockStatus[] stockStatus ; 
	private int portfolioSize = 0 ;
	private float balance = 0 ;
	
	
	public Portfolio (){
		setTitle("Portfolio #1");
		 stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ;
		  if(getLogicalSizeStatus(stockStatus) > 0 && getLogicalSizeStatus(stockStatus)<= MAX_PORTFOLIO_SIZE-1){ //array is not full, figure this if statement ! ! !	
				int logicalSizeStocks = getLogicalSizeStatus(stockStatus);
				for (int i = 0; i<logicalSizeStocks; i++){
					this.addStock(stockStatus[i]) ;  
					stockStatus[i] = new StockStatus() ;
				}
			}
		 
	}
	
	public Portfolio (Portfolio portfolio) {//copy c'tor
		this(portfolio.getTitle(), portfolio.getStockStatus()) ; 
		
	}
	public Portfolio(String title,StockStatus[] stockStatusP){// c'tor non-empty
		
		setTitle(title);
		this.stockStatus = new StockStatus[MAX_PORTFOLIO_SIZE] ;
	    
	     if(getLogicalSizeStatus(stockStatus) > 0 && getLogicalSizeStatus(stockStatus)<= MAX_PORTFOLIO_SIZE-1)//array is not full
		{
			int logicalSizeStocks = getLogicalSizeStatus(stockStatus);
			for (int i = 0; i<logicalSizeStocks; i++){
				this.addStock(stockStatus[i]) ;  
				//stocksStatus[i] = new StockStatus(stockStatusP[i]) ;//fix it
			}
		}
	}
	
	private int getLogicalSizeStocks(Stock[] stocks){//add description later
		int i =0 ;
		while(stocks[i]!=null && i<MAX_PORTFOLIO_SIZE){
			i++;
		}
		return i;//-1;//else i would be returning the spot AFTER last item position(on array)
	}
	private int getLogicalSizeStatus(StockStatus[] OtherstockStatus){//add description later
		int i =0 ;
		while(OtherstockStatus[i]!=null && i<MAX_PORTFOLIO_SIZE){
			i++;
		}
		return i;//-1;
	}
	private void copyStocksArray(Stock[] stocks, int size){// combine two next func to one in the copy c'tor
		
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
		
		if(size >= 0 && size<= MAX_PORTFOLIO_SIZE-1)//array is not full and not pointing to null
		{
			for(int i =0 ;i <=size ;i++){	
				this.stockStatus[i] = new StockStatus(stockStatus[i])  ;
			}
		}
		else//array is full
		{
			//present array is full msg
			return ;
		}
	}	
	
	public StockStatus[] getStockStatus(){
		return this.stockStatus ;
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
		while(stockStatus[i] != null && i < portfolioSize){
			resStr = resStr + "<br>" + stockStatus[i].getHtmlDescription() ;// getHtmlDescription(stocks );// still cannot figure y cant i use noted method- get a 'not defined for type portfolio'->A: didn't approach stock.function, did just function which is wrong.
			i++ ;
		}
		resStr = resStr + "<br>" + "Total portfolio value " + "$" + this.getTotalValue(this) +  ", Total stocks value " + "$"  + this.getStocksValue(this) + ", Balance " + "$" + this.getBalance()  ;
		return resStr; 
	}
	/**
	 * add Stock object to a Stock array 
	 * updates the related stock status field using the parameter data(e.g symbol, date, etc.)
	 * @param stock - Stock object to add
	 */
	public void addStock(Stock stock) {
		int i;
		for( i = 0; i < portfolioSize; i++ ){//validate in-existence of stock prior to the actual add action.
			if(stock.getSymbol().equals(this.stockStatus[i].getSymbol())){
				System.out.println("Stock already exist in the portfolio, cannot add it again ");
				return;
			}	
		}
		
		i = portfolioSize ;
		if(i < MAX_PORTFOLIO_SIZE)//array is not full
		{
			stockStatus[i] = new StockStatus( stock.getBid(),stock.getAsk(), stock.getDate(),stock.getSymbol() , ALGO_RECOMMENDATION.DO_NOTHING, 0) ;//
			portfolioSize++ ;
			
		}
		else//array is full
		{
			System.out.println("Can't add new stock, portfolio can only have "+MAX_PORTFOLIO_SIZE+" stocks");
		}
	}
	
		
	
	/**
	 * removes stocks from both stocks and stock status arrays and update the balance of 
	 * the portfolio using method sellStock.
	 * @param stockSymbol used to locate the stock in stocks array
	 * @return bool for success/failure
	 */
	public boolean removeStock(String stockSymbol){// means --> selling all quantity of stock
		int i ;
		boolean removeFlag = false ;
		for(i = 0; i < this.portfolioSize; i++ ){//validate existence of stock prior to the actual remove action.
			if(stockSymbol.equals(this.stockStatus[i].getSymbol())){
				removeFlag = true;
				break;
			}
				
		}
		if(removeFlag){//proceed if stock existing in array
			removeFlag = sellStock(stockSymbol, -1);//sell stock, validate operation was successful
			if(removeFlag){//if in the above line flag became false - sellStock failed -->removeStock return false.
				this.stockStatus[i] = this.stockStatus[portfolioSize] ;//remove from stockStatus[]
				portfolioSize-- ;
			}
		}
		return removeFlag ;
	}
	/**
	 * 	method using to sell stocks, can sell all existing stocks with "-1" input to quantity.
	 * if so, it also removes the stock from both stocks and stock status arrays.
	 * @param stockSymbol is a string, uses to locate the stock in stocks array
	 * @param quantity amount of stocks wished to sell
	 * @return bool for success/failure of operation
	 */
	public boolean sellStock(String stockSymbol, int quantity){// ignoring zero amount of stocks?
		int i ;
		int currentQuantity ;
		boolean sellFlag = false ;
		
		if(quantity != -1 && quantity < 0)//negative quantity is not an option.
			return false ;
		
		for(i = 0; i < this.portfolioSize; i++ ){//validate existence of stock 
			if(stockSymbol.equals(this.stockStatus[i].getSymbol())){
				break;
			}
			sellFlag = true;
		}
		
		currentQuantity = this.stockStatus[i].getStockQuantity() ;
		if(currentQuantity < quantity){
			System.out.println("Not enough stocks to sell.");
			return false ;
		}
		if(sellFlag){//stock present in portfolio and requested amount to sell is present as well
			if(quantity == -1){//sell all stock quantity(and remove the stock..
				this.updateBalance(this.stockStatus[i].getStockQuantity() * this.stockStatus[i].bid) ;//update balance
				this.stockStatus[i].setStockQuantity(0) ;//update quantity
			}
			else{//sell requested quantity(stock remains in portfolio
				this.updateBalance(quantity * this.stockStatus[i].bid);
				this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity()-quantity) ;//update quantity
			}	
		}
		return sellFlag;
	}
	/**
	 * 
	 * @param amount
	 * gets 'amount' , adds it to the balance,
	 * amount can be negative
	 */
	public void updateBalance(float amount){
		balance += amount ;
	}
	/**
	 * 
	 * @param symbol
	 * @param quantity
	 * @return
	 */
	public boolean buyStock(String symbol, int quantity){
		int i ;
		boolean flag = false; 
		
		
		
		//create stockStatus object according to the new stock-in case of need
		if(quantity < 0 && quantity != -1)// validate input
			return flag ;
		else{
		for(i = 0; i < this.portfolioSize; i++ ){//validate existence/in-existence of stock 
			if(symbol.equals(this.stockStatus[i].getSymbol())){
				flag = true;
				break;
			}
		if((quantity * this.stockStatus[i].getAsk() > this.balance)){
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}
				
		//check if balance fit request, later
		
		}
		if(flag && quantity > 0){//stock exist, adding data
			this.updateBalance(-1*(this.stockStatus[i].getAsk()*quantity)) ;//update balance
			this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + quantity);//update quantity
		}
		else if(quantity == -1){
			this.updateBalance((int)this.balance / this.stockStatus[i].getAsk() * (this.stockStatus[i].getAsk() * -1)) ;//update balance
			this.stockStatus[i].setStockQuantity(this.stockStatus[i].getStockQuantity() + quantity);//update quantity
		}
			
		
		else{//stock doens't exist, create new stock, update data
			//cannot implement at current time, need to get stock details 
			//from unavailable source(JSON, web page , etc.)
			//portfolioSize++;
		}
		return flag;
		}
	}
	public float getStocksValue(Portfolio portfolio){
		float res = 0 ;
		for(int i = 0; i < portfolioSize; i++){
			if(this.stockStatus[i] == null)
				continue;
			res += this.stockStatus[i].getBid() * this.stockStatus[i].getStockQuantity();
		}
			return res;
	}
	public float getBalance(){
		return this.balance;
	}
	public float getTotalValue(Portfolio portfolio){
		float res = 0 ;
		res = portfolio.getBalance() + portfolio.getStocksValue(portfolio) ;
		return res ;
	}
	
}
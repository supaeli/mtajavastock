
package com.mta.elisproject.model;
import java.util.Date;
import java.util.List;

import com.mta.elisproject.exception.BalanceException;
import com.mta.elisproject.exception.PortfolioFullException;
import com.mta.elisproject.exception.StockAlreadyExistsException;
import com.mta.elisproject.exception.StockNotExistsException;

/**
 * 
 * @author sup4eli
 * a portfolio of Stock instances, holds 
 * multiple Stock objects
 * can add stock(stockstatus) to a portfolio, present 
 * the whole portfolio in html string 
 * 
 *
 */
public class Portfolio {
	public enum ALGO_RECOMMENDATION {DO_NOTHING,BUY,SELL};
	public final static int MAX_PORTFOLIO_SIZE= 5;
	public int portfolioSize = 0;
	private String title;
	private StockStatus[] stocksStatus;
	private float balance;
	
	public Portfolio (){
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		setTitle("sup4eli's portfolio biatch");
		setBalance(0);
	}
	
	public Portfolio (String title,StockStatus[] stocksStatus) throws StockAlreadyExistsException, PortfolioFullException{
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		setTitle("Portfolio ");
		try {
			setStocks(stocksStatus,checkRealSizeOfStockArray(stocksStatus,0));
		} catch (PortfolioFullException e) {
			throw e;
		} catch (StockAlreadyExistsException e) {
			throw e;
		}
	}
	
	public Portfolio (Portfolio otherPortfolio) throws StockAlreadyExistsException, PortfolioFullException{
		this (otherPortfolio.getTitle(),otherPortfolio.getStockStatus());
	}
	public Portfolio(List<StockStatus> stockStatuses) throws StockAlreadyExistsException, PortfolioFullException {
		this();
		for (int i = 0; i < stockStatuses.size(); i++) {
			this.stocksStatus[i] = stockStatuses.get(i);
			this.portfolioSize++;
		}
	}
	//methods laid down here
	
	private int checkRealSizeOfStockArray(StockStatus[] stocksStatus,int i){
		i=0;
		while (stocksStatus[i]!=null)
		{
			i++;
		}
		return i ;
	}
	public void updateBalance (float amount){
		this.balance+=amount;
	}
	/**
	 *
	 * returns int as the position of desired stock, otherwise return -1.
	 */
	public int findStockPositionBySymbol (String s)
	{
		int i = 0 ;
		for (;!this.stocksStatus[i].getSymbol().equalsIgnoreCase(s) && i<this.portfolioSize && i<MAX_PORTFOLIO_SIZE ; i++ ){ }
		if (i>=this.portfolioSize && i>=MAX_PORTFOLIO_SIZE){
			i=-1;
		}
		return i;
	}
	
	public void removeStock (String s ) throws StockNotExistsException{
		int num = findStockPositionBySymbol(s);
		if (num == -1)
		{
			System.out.println("stock not found");
			throw new StockNotExistsException(s);
		}
		else{
			try{
				this.sellStock(s,-1); 
			}
			catch (StockNotExistsException e) {
				throw e;
			}
			stocksStatus[num]=stocksStatus[portfolioSize-1];
			stocksStatus[portfolioSize-1]=null;
			portfolioSize--;
		}
	}
	
	public void sellStock(String s ,int quantity) throws StockNotExistsException{
		if (quantity<-1 || quantity==0 ){
			System.out.println(" number is not vaild  (<-1 or ==0)");
		}
		int num = findStockPositionBySymbol(s);
		if (num == -1 )
		{
			System.out.println("stock not found");
			throw new StockNotExistsException(s);
		}
		if (quantity > this.stocksStatus[num].getStockQuantity() ){
			System.out.println("your'e trying to sell more then you actually have");
		}
		if (quantity==-1)
		{
			this.updateBalance(this.stocksStatus[num].getStockQuantity() * this.stocksStatus[num].getBid());
			this.stocksStatus[num].setStockQuantity(0);
		}
		else{
			this.updateBalance(quantity * this.stocksStatus[num].getBid());
			this.stocksStatus[num].setStockQuantity(this.stocksStatus[num].getStockQuantity()-quantity);
		}
	}
	
	public void buyStock(String OtherSymbol ,int quantity) throws StockNotExistsException,BalanceException{
		if (quantity<-1 || quantity==0 ){
			System.out.println(" number is not vaild (<-1 or =0)");
			//return false;
		}
		int num = findStockPositionBySymbol(OtherSymbol);
		if (num == -1 )
		{
			System.out.println("stock not found");
			throw new StockNotExistsException(OtherSymbol);
		}
		float Value = quantity * this.stocksStatus[num].getAsk();
		if (Value > this.balance ){
			System.out.println("you are trying to buy more then you can afford");
			throw new BalanceException();
		}
		if (quantity==-1)
		{
			int numOfstocks = (int) (this.balance/this.stocksStatus[num].getAsk());
			int leftOver = (int) (this.balance%this.stocksStatus[num].getAsk());
			this.setBalance(leftOver);
			this.stocksStatus[num].setStockQuantity(this.stocksStatus[num].getStockQuantity()+numOfstocks);
		}
		else{
			this.updateBalance(-quantity * this.stocksStatus[num].getAsk());
			this.stocksStatus[num].setStockQuantity(this.stocksStatus[num].getStockQuantity()+quantity);
		}
	}
	
	public void addStock (Stock stock) throws PortfolioFullException,StockAlreadyExistsException
	{
		boolean check=false;
		if (this.portfolioSize >= MAX_PORTFOLIO_SIZE || stock==null ){
			throw new PortfolioFullException();
		}
		else{
			for(int i =0 ; i<this.portfolioSize;i++)
			{
				if ((stock.getSymbol().equals(this.stocksStatus[i].getSymbol()))){
					check=true;
					throw new StockAlreadyExistsException(stock.getSymbol());
				}
			}
			if(check==false){
				this.stocksStatus[portfolioSize]= new StockStatus(stock);
				portfolioSize++;
			}
		}
	}
	/**
	 *
	 * @return an html form of portfolio 
	 */
	public String getHtmlDescription(){
		String str = new String();
		str += "<h1>"+this.title +"</h1>"+"<br>" ;
		for (int i =0 ; i < portfolioSize ;i++){
			str += "<tr>"+stocksStatus[i].getHtmlDescription()+"</tr>"+"<br>";
		}
		str+="Total Portfolio Value: "+ this.getTotalValue()+"$"+", Total Stocks value: "+this.getStocksValue()+"$ , Balance: "+this.getbalance()+"$";
		return str;
	}
	
	public void setTitle(String title){
		this.title=new String(title);
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	private void setStocks (Stock[] stocks,int PortfolioSize) throws PortfolioFullException,StockAlreadyExistsException{
		if (PortfolioSize<MAX_PORTFOLIO_SIZE && PortfolioSize!=0 ){
			for (int i=0 ;i<PortfolioSize;i++)
			{
				Stock temp = stocks[i];
				try {
					this.addStock(temp);
				} catch (PortfolioFullException e) {
					throw e;
				} catch (StockAlreadyExistsException e) {
					throw e;
				}
			}
		}
	}
	
	public String getTitle(){
		return this.title;
	}
	public float getStocksValue(){
		float Totalvalue=0 ;
		for (int i =0 ; i < this.portfolioSize ; i++){
			Totalvalue += this.stocksStatus[i].getStockQuantity() * this.stocksStatus[i].getBid();
		}
		return Totalvalue;
	}
	public StockStatus[] getStockStatus(){
		return this.stocksStatus;
	}
	public float getbalance() {
		return this.balance;
	}
	public float getTotalValue() {
		return this.getStocksValue()+this.getbalance();
	}
}
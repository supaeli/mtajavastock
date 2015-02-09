
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
 * can add stock to a portfolio, present 
 * the whole portfolio in html string 
 * and has inner class for stock status, explained below.
 *
 */
public class Portfolio {
	public enum ALGO_RECOMMENDATION {DO_NOTHING,BUY,SELL};
	public final static int MAX_PORTFOLIO_SIZE= 5;//max size of stocks
	public int portfolioSize = 0;// current head
	private String title;// the title of the Portfolio (currently not defined )
	private StockStatus[] stocksStatus;
	private float balance;
	//-----------------------------------------------------Contractor------------------------
	/**
	 * build a Stock's array
	 * build a StockStatus array
	 *
	 */
	public Portfolio (){
		this.stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		setTitle("sup4eli's portfolio biatch");
		setBalance(0);
	}
	/**
	 *
	 * @param title - the Portfolio title
	 * @param stocks - an Array of stocks
	 * @param stocksStatus - unknow use at the moment
	 * @throws StockAlreadyExistsException
	 * @throws PortfolioFullException
	 */
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
	/**
	 * copy constractor
	 * @param otherPortfolio
	 * @throws PortfolioFullException
	 * @throws StockAlreadyExistsException
	 */
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
	//-----------------------------------------------------------Methods------------------------
	/**
	 * this is an internal method that is use in the copy Constaractor
	 *
	 * @param stocks
	 * @param i
	 * @return integer - the real size of the Stock[] array
	 */
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
	 * @param OtherSymbol
	 * @return -1 if not found / int pos - position that found
	 */
	public int findStock (String OtherSymbol)
	{
		int i = 0 ;
		for (;!this.stocksStatus[i].getSymbol().equalsIgnoreCase(OtherSymbol) && i<this.portfolioSize && i<MAX_PORTFOLIO_SIZE ; i++ ){ }
		if (i>=this.portfolioSize && i>=MAX_PORTFOLIO_SIZE){
			i=-1;//Not fount
		}
		return i;//the position
	}
	/**
	 *
	 * @param OtherSymbol
	 * @return True-success / false unsuccess
	 */
	public void removeStock (String OtherSymbol ) throws StockNotExistsException{
		int num = findStock(OtherSymbol);
		if (num == -1)
		{
			System.out.println("Erorr - not found");
			throw new StockNotExistsException(OtherSymbol);
		}
		else{
			try{
				this.sellStock(OtherSymbol,-1); //sell all
			}
			catch (StockNotExistsException e) {
				throw e;
			}
			stocksStatus[num]=stocksStatus[portfolioSize-1]; //stocks[i] = LAST ,
			stocksStatus[portfolioSize-1]=null;// last=null
			portfolioSize--;
		}
	}
	/**
	 *
	 *
	 * @param OtherSymbol
	 * @param quantity
	 * @return True-success sell / false unsuccess sell
	 */
	public void sellStock(String OtherSymbol ,int quantity) throws StockNotExistsException{
		if (quantity<-1 || quantity==0 ){
			System.out.println("Erorr - not vaild num (<-1|| =0)");
			/*return false;*/
		}
		int num = findStock(OtherSymbol);
		if (num == -1 )
		{
			System.out.println("Erorr - stock not found");
			throw new StockNotExistsException(OtherSymbol);
		}
		if (quantity > this.stocksStatus[num].getStockQuantity() ){
			System.out.println("Erorr - you are asking to sell more then u have");
			/*return false;*/
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
	/**
	 *
	 * @param OtherSymbol
	 * @param quantity
	 * @return True-success buy / false unsuccess buy
	 */
	public void buyStock(String OtherSymbol ,int quantity) throws StockNotExistsException,BalanceException{
		if (quantity<-1 || quantity==0 ){
			System.out.println("Erorr - not vaild num (<-1|| =0)");
			//return false;
		}
		int num = findStock(OtherSymbol);
		if (num == -1 )
		{
			System.out.println("Erorr - stock not found");
			throw new StockNotExistsException(OtherSymbol);
		}
		float Value = quantity * this.stocksStatus[num].getAsk();
		if (Value > this.balance ){
			System.out.println("Erorr - you are asking to buy more then u can afford");
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
	/**
	 * add a new Stock to Protfolio
	 * @param stock - get's a Stock
	 */
	public void addStock (Stock stock) throws PortfolioFullException,StockAlreadyExistsException
	{
		boolean check=false;
		if (this.portfolioSize >= MAX_PORTFOLIO_SIZE || stock==null ){//if full or not valid input
			throw new PortfolioFullException();
		}
		else{
			for(int i =0 ; i<this.portfolioSize;i++)
			{
				if ((stock.getSymbol().equals(this.stocksStatus[i].getSymbol()))){//check no - same symbols
					check=true;
					throw new StockAlreadyExistsException(stock.getSymbol());
				}
			}
			if(check==false){//all is well
				this.stocksStatus[portfolioSize]= new StockStatus(stock);
				portfolioSize++;
			}
		}
	}
	/**
	 *
	 * @return the html description of this protflio plz notice it use getHtmlDescription from "Stocks"
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
	//-------------------------------------------------------------Setters----------------------------------------------------------------------------
	public void setTitle(String title){
		this.title=new String(title);
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	//--------------------------------------------------------Setters for the copyConstractors----------------------------
	private void setStocks (Stock[] otherStocks,int otherPortfolioSize) throws PortfolioFullException,StockAlreadyExistsException{
		if (otherPortfolioSize<MAX_PORTFOLIO_SIZE && otherPortfolioSize!=0 ){
			for (int i=0 ;i<otherPortfolioSize;i++)
			{
				Stock temp = otherStocks[i];
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
	//----------------------------------------------------------------getters----------------------------------------------------------------------------------------------
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
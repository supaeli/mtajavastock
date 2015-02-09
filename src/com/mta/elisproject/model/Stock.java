package com.mta.elisproject.model ;
import java.util.Date ;
//import java.text.SimpleDateFormat;
/**
 * 
 * @author sup4eli
 *   of a stock(e.g stock market)
 * including buying price, selling price, date, and stock symbol 
 * as presented in the stock market.
 * using as super class for stockStatus in which we actually use.
 *
 */


public class Stock {
	protected float bid ;
	protected float ask ;
	protected Date date ;
	protected String symbol = new String();
	
	public Stock (){
		bid = 0;
		ask = 0;
		date = new Date();
		symbol = "N/A";
	}
	public Stock (float bid, float ask, Date date, String symbol) {
		setBid(bid);
		setAsk(ask);
		setSymbol(symbol);
		setDate(date);
	}
	public Stock (Stock stock){
		this(stock.getAsk(), stock.getBid(), stock.getDate(), stock.getSymbol());
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float assignedBid) {
		bid = assignedBid ;
	}
	public float getAsk() {
		return ask;
	}
	public void setAsk(float assignedAsk) {
		ask = assignedAsk ;
	}
	public Date getDate(){
		return date;
	}
	public void setDate(Date assignedDate) {
		date =  assignedDate ;
	}
	public String getSymbol() {
		return symbol ;
	}
	public void setSymbol(String assignedSymbol) {
		symbol =new String(assignedSymbol);
	}
	/**
	 *
	 * @return html string form of mentioned above stock states
	 */
	public String getHtmlDescription() {
		String  combined ;
		
		combined =  new String("<b>Symbol</b> "+getSymbol()+"<b> ask: </b>" +getAsk()+", <b>bid: </b>"+getBid()+" <b>Date </b>"+ getDate()) ;//how to include a String inside brackets??
		return combined;
		
	}

}

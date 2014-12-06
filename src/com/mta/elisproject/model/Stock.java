package com.mta.elisproject.model ;
import java.util.Date ;
import java.text.SimpleDateFormat;

/**
 * 
 * @author sup4eli
 * description: presentation of a stock(e.g stock market)
 * including buying price, selling price, date, and stock symbol 
 * as presented in the stock market.
 *
 */
public class Stock {
	private float bid ;
	private float ask ;
	private Date date ;
	String symbol = new String();
	
	public Stock (float bid, float ask, Date date, String symbol) {
		setBid(bid);
		setAsk(ask);
		setSymbol(symbol);
		setDate(date);
	}
	public Stock (Stock stock){//copy c'tor
		this(stock.getAsk(), stock.getBid(), stock.getDate(), stock.getSymbol());
	}
	public float getBid() {//bid getter
		return bid;
	}
	public void setBid(float assignedBid) {//bid setter
		bid = assignedBid ;
	}
	public float getAsk() {//ask getter
		return ask;
	}
	public void setAsk(float assignedAsk) {//ask setter
		ask = assignedAsk ;
	}
	public Date getDate(){//in case it doesnt work replace return value to String
		//SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");  
		//return format1.format(this.date);
		return date;
	}
	public void setDate(Date assignedDate) {//date setter
		date = assignedDate ;
	}
	public String getSymbol() {//symbol getter
		return symbol ;
	}
	public void setSymbol(String assignedSymbol) {//symbol setter
		symbol = assignedSymbol ;
	}
	/**
	 * @return html string presentation of mentioned above stock states
	 */
	public String getHtmlDescription() {
		String  combined ;
		
		combined =  new String("<b>Symbol</b> "+getSymbol()+"<b> ask: </b>" +getAsk()+", <b>bid: </b>"+getBid()+" <b>Date </b>"+ getDate()) ;//how to include a String inside brackets??
		return combined;//last line
		
	}

}

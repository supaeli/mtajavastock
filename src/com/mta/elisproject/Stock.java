package com.mta.elisproject ;
import java.util.Date ;


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
	public Date getDate() {//date getter
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
	public String getHtmlDescription() {
		String symbol, date, combined ;
		symbol = new String() ;
		date = new String() ;
		
		symbol = getSymbol().toString() ;//  need for 'symbol=new String' ??? 
		//and also to the other other String objects in the method 
		ask = getAsk() ;
		bid = getBid() ;
		date = getDate().toString() ;
		// defined 4 'sentence' parts then combine to final 'combined string' in HTML format 
		combined =  new String("<h1>symbol ask:"+ask+", bid:"+bid+" date</h1>") ;//how to include a String inside brackets??
		return combined;//last line
		
	}

}

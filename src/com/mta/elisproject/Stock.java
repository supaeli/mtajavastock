package com.mta.elisproject ;
import java.util.Date ;
import java.text.SimpleDateFormat;

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
	public String getDate(){
		SimpleDateFormat format1 = new SimpleDateFormat("MM-dd-yyyy");  
		return format1.format(this.date);
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
		String  combined ;
		
		combined =  new String("<b>Symbol</b>"+getSymbol()+"<b> ask: </b>" +getAsk()+", <b>bid: </b>"+getBid()+" <b>Date </b>"+ getDate()) ;//how to include a String inside brackets??
		return combined;//last line
		
	}

}

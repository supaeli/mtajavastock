package com.mta.elisproject.model;

import java.util.Date;

import com.mta.elisproject.model.Portfolio.ALGO_RECOMMENDATION;
/**
 * 
 * @author sup4eli
 * description: holds Stock object data fields
 * and other other data concerns the instance such as:
 * whether buy or not and stock holding number.
 *
 */
public class StockStatus extends Stock {
	
	private ALGO_RECOMMENDATION recommendation;
	//public String symbol = new String() ;
	//public float currentBid ;
	//public float currentAsk ;
	//public Date date = new Date() ;
	private int stockQuantity ;
	
	public StockStatus(){//c'tor #1
		super();
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}
	public StockStatus(float bid, float ask, Date date, String symbol, ALGO_RECOMMENDATION recommendation, int stockQuantity){
		setSymbol( symbol) ;
		setBid(bid);
		setAsk(ask); ;
		setDate(date) ;
		setRecommendation(recommendation) ;
		setStockQuantity(stockQuantity) ;
	}
	public StockStatus(Stock s){
		super(s);
		setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
		setStockQuantity(0);
		setAsk(s.getAsk());
		setBid(s.getBid());
	}
	public StockStatus(StockStatus stockStatus){//copy c'tor
		super(stockStatus.bid, stockStatus.ask, new Date(stockStatus.getDate().getTime()), stockStatus.symbol );
	//	this(stockStatus.getBid(), stockStatus.getAsk(), stockStatus.getDate(), stockStatus.getSymbol(),
		//		stockStatus.getRecommendation(), stockStatus.getStockQuantity() );
	}
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	
}

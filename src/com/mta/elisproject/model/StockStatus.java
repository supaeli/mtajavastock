
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
ALGO_RECOMMENDATION recommendation;
private int stockQuantity;
/**
*
* @param symbol
* @param Ask
* @param Bid
* @param date
*/
public StockStatus(String symbol, float Ask, float Bid, Date date) {
super(Bid,Ask,date,symbol);
setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
setStockQuantity(0);
}
/**
* Empty Copy constractor
*/
public StockStatus() {
super();
setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
setStockQuantity(0);
}
/** Copy constractor using Stock`s model
* @param otherStock
*/
public StockStatus(Stock otherStock) {
super(otherStock);
setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
setStockQuantity(0);
setAsk(otherStock.getAsk());
setBid(otherStock.getBid());
}
/**
* Copy constractor using StockStatus model
* @param otherStockStatus
*/
public StockStatus(StockStatus otherStockStatus) {
this(otherStockStatus.getSymbol(), otherStockStatus.getAsk(), otherStockStatus.getBid(), new Date(otherStockStatus.getDate().getTime()));
// TODO Auto-generated constructor stub
}
/**
* @return recommendation (DO_NOTHING/BUY/SELL)
*/
public ALGO_RECOMMENDATION getRecommendation() {
return recommendation;
}
/**
* Set recommendation (DO_NOTHING/BUY/SELL)
* @param recommendation
*/
public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
this.recommendation=ALGO_RECOMMENDATION.valueOf(recommendation.name());
}
/**
*
* @return Stock Quantity
*/
public int getStockQuantity() {
return stockQuantity;
}
/**
* set Stock Quantity
* @param stockQuantity
*/
public void setStockQuantity(int stockQuantity) {
this.stockQuantity = stockQuantity;
}
}

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

public StockStatus(String symbol, float Ask, float Bid, Date date) {
super(Bid,Ask,date,symbol);
setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
setStockQuantity(0);
}

public StockStatus() {
super();
setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
setStockQuantity(0);
}

public StockStatus(Stock s) {
super(s);
setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);
setStockQuantity(0);
setAsk(s.getAsk());
setBid(s.getBid());
}

public StockStatus(StockStatus s) {
this(s.getSymbol(), s.getAsk(), s.getBid(), new Date(s.getDate().getTime()));
}

public ALGO_RECOMMENDATION getRecommendation() {
return recommendation;
}

public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
this.recommendation=ALGO_RECOMMENDATION.valueOf(recommendation.name());
}

public int getStockQuantity() {
return stockQuantity;
}

public void setStockQuantity(int stockQuantity) {
this.stockQuantity = stockQuantity;
}
}
package com.mta.elisproject.service;

import com.mta.elisproject.dto.PortfolioTotalStatus;
import com.mta.elisproject.exception.BalanceException;
import com.mta.elisproject.exception.IllegalQuantityException;
import com.mta.elisproject.exception.PortfolioFullException;
import com.mta.elisproject.exception.StockAlreadyExistsException;
import com.mta.elisproject.exception.StockNotExistsException;
import com.mta.elisproject.exception.SymbolNotFoundInNasdaq;
import com.mta.elisproject.model.Portfolio;
import com.mta.elisproject.model.Stock;
import com.mta.elisproject.model.StockStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
<<<<<<< HEAD
 * @author hanan.gitliz@gmail.com
=======
 * @author sup4eli
 * Portfolio 'process' class, manipulates data in 
 * aportfolio
 *
>>>>>>> 3b35c5c71645918327c4e0c1024bb16d2df20f2f
 */
public class PortfolioService {
	
	private final static Logger log = Logger.getLogger(PortfolioService.class.getSimpleName());
	
	private Portfolio portfolio;
	
	public enum OPERATION {
		ADD, REMOVE, SELL, BUY
	}
	
	private static final int DAYS_BACK = 30;
	private static PortfolioService instance = new PortfolioService();

	public static PortfolioService getInstance() {
		return instance;
	}
	
	private DatastoreService datastoreService;
	
	private PortfolioService() {
		datastoreService = DatastoreService.getInstance();
	}

	public Portfolio getPortfolio() throws StockAlreadyExistsException, PortfolioFullException {
		if(portfolio == null) {
			portfolio = datastoreService.loadPortfolilo();
		}
		
		return portfolio;
	}
	
	/**
	 * Updates Portfolio with algo recommendation.
	 * @throws PortfolioFullException 
	 * @throws StockAlreadyExistsException 
	 */
	public void update() throws StockAlreadyExistsException, PortfolioFullException {
		StockStatus[] stocks = getPortfolio().getStockStatus();
		List<String> symbols = new ArrayList<>(Portfolio.MAX_PORTFOLIO_SIZE);
		for (StockStatus stockStatus : stocks) {
			symbols.add(stockStatus.getSymbol());
		}
		
		List<StockStatus> update = new ArrayList<>(Portfolio.MAX_PORTFOLIO_SIZE);
		List<Stock> currentStocksList;
		try {
			currentStocksList = MarketService.getInstance().getStocks(symbols);
			for (Stock stock : currentStocksList) {
				update.add(new StockStatus(stock));
			}
			
			datastoreService.saveToDataStore(update);
			
			//load fresh data from database.
			portfolio = null;
		} catch (SymbolNotFoundInNasdaq e) {
			log.severe(e.getMessage());
		}
	}
	
	public PortfolioTotalStatus[] getPortfolioTotalStatus () throws StockAlreadyExistsException, PortfolioFullException {
		
		Portfolio portfolio = getPortfolio();
		Map<Date, Float> map = new HashMap<>();
		
		//get stock status from db.
		Stock[] stocks = portfolio.getStockStatus();
		for (int i = 0; i < stocks.length; i++) {
			Stock stock = stocks[i];
			
			if(stock != null) {
				List<StockStatus> history = datastoreService.getStockHistory(stock.getSymbol(), DAYS_BACK);
				
				for (int j = 0; j < history.size(); j++) {
					StockStatus curr = history.get(j);
					Date date = dateMidnight(curr.getDate());
					float value = curr.getBid()*curr.getStockQuantity();
					
					Float total = map.get(date);
					if(total == null) {
						total = value;
					}else {
						total += value;
					}
					
					map.put(date, value);
				}
			}
		}
		
		PortfolioTotalStatus[] ret = new PortfolioTotalStatus[map.size()];
		
		int index = 0;
		//create dto objects
		for (Date date : map.keySet()) {
			ret[index] = new PortfolioTotalStatus(date, map.get(date));
			index++;
		}
		
		//sort by date ascending.
		Arrays.sort(ret);
		
		return ret;
	}
	
	public void setTitle(String title) throws StockAlreadyExistsException, PortfolioFullException {
		Portfolio portfolio = getPortfolio();
		portfolio.setTitle(title);
		datastoreService.updatePortfolio(portfolio);
		
		flush();
	}
	
	public void setBalance(float newBalance) throws BalanceException, StockAlreadyExistsException, PortfolioFullException {
		Portfolio portfolio = getPortfolio();
		portfolio.updateBalance(newBalance);
		datastoreService.updatePortfolio(portfolio);
		
		flush();
	}
	
	public void addStock(String symbol) throws StockAlreadyExistsException, PortfolioFullException, StockNotExistsException, SymbolNotFoundInNasdaq {
		Portfolio portfolio = getPortfolio();
		
		//get current symbol values from nasdaq.
		Stock stock = MarketService.getInstance().getStock(symbol);
		
		
		if(stock != null) {
			
			//first thing, add it to portfolio.
			portfolio.addStock(stock);
			
			//second thing, save the new stock to the database.
			datastoreService.saveStock(portfolio.getStockStatus()[ portfolio.findStock(symbol)]);
			
			flush();
		}
	}

	
	public void buyStock(String symbol, int quantity) throws BalanceException, StockNotExistsException, StockAlreadyExistsException, PortfolioFullException {
		getPortfolio().buyStock(symbol, quantity);
		flush();
	}

	public void sellStock(String symbol, int quantity) throws StockNotExistsException, IllegalQuantityException,BalanceException, StockAlreadyExistsException, PortfolioFullException {
		getPortfolio().sellStock(symbol, quantity);
		flush();
	}

	public void removeStock(String symbol) throws StockNotExistsException, IllegalQuantityException, BalanceException, StockAlreadyExistsException, PortfolioFullException {
		getPortfolio().removeStock(symbol);
		flush();
	}
	
	private void flush() throws StockAlreadyExistsException, PortfolioFullException {
		//update db
		datastoreService.updatePortfolio(getPortfolio());
		//now make next call to portfolio to fetch data from updated db.
		portfolio = null;
	}
	
	/**
	 * Transform a given date to start day date.
	 * @param date
	 * @return
	 */
	private Date dateMidnight(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
}

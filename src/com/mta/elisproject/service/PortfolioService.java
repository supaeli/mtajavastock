package com.mta.elisproject.service;
import java.util.Calendar;
import java.util.Date;

import com.mta.elisproject.exception.BalanceException;
import com.mta.elisproject.exception.PortfolioFullException;
import com.mta.elisproject.exception.StockAlreadyExist;
import com.mta.elisproject.exception.StockNotExist;
import com.mta.elisproject.model.*;
/**
<<<<<<< HEAD
 * @author sup4eli
 * Portfolio 'process' class, manipulates data in 
 * a portfolio
=======
 * 
 * @author sup4eli
 * Portfolio 'process' class, manipulates data in 
 * aportfolio
 *
>>>>>>> branch 'master' of https://github.com/supaeli/mtajavastock.git
 */
public class PortfolioService  {
	
	/**
	 * put data in a portfolio:
	 * date, prices, stock market symbol
	 * @return portfolio with data(not empty)
	 */
	public Portfolio getPortfolio() throws BalanceException, PortfolioFullException, StockNotExist, StockAlreadyExist{
	Portfolio myPortfolio = new Portfolio() ;
	myPortfolio.setTitle("Exercise 7 portfolio");
	myPortfolio.updateBalance(10000);
	Calendar c =  Calendar.getInstance() ;
	Date date = new Date() ;
	c.set(2014, 11, 23) ;
	date = c.getTime() ;
	
	Stock stock1 = new Stock(8.5f, 10f, date, "PIH" );//data from exe details) ;
	Stock stock2 = new Stock(25.5f, 30f, date, "AAL");//data from exe details) ;
	Stock stock3 = new Stock(15.5f, 20f, date, "CAAS");//data from exe details) ;
	
	
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		myPortfolio.buyStock("PIH", 20) ;
		myPortfolio.buyStock("AAL", 30) ;
		myPortfolio.buyStock("CAAS", 40) ;
		myPortfolio.sellStock("AAL", -1) ;
		myPortfolio.removeStock("CAAS") ;
		myPortfolio.addStock(stock3);
	
	
	return myPortfolio;
	
	
	}
}

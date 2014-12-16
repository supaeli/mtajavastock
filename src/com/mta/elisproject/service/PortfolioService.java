package com.mta.elisproject.service;
import java.util.Calendar;
import java.util.Date;

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
public class PortfolioService {
	
	/**
	 * put data in a portfolio:
	 * date, prices, stock market symbol
	 * @return portfolio with data(not empty)
	 */
	public Portfolio getPortfolio(){
	Portfolio myPortfolio = new Portfolio() ;
	
	
	Calendar c =  Calendar.getInstance() ;
	Date date = new Date() ;
	c.set(2014, 11, 4) ;
	date = c.getTime() ;
	
	Stock stock1 = new Stock((float)13.1, (float)12.4, date, "PIH" );//data from exe details) ;
	Stock stock2 = new Stock((float)5.78, (float)5.5, date, "AAL");//data from exe details) ;
	Stock stock3 = new Stock((float)31.2, (float)31.5, date, "CAAS");//data from exe details) ;
	

	
	myPortfolio.addStock(stock1);
	myPortfolio.addStock(stock2);
	myPortfolio.addStock(stock3);
	
	return myPortfolio;
	
	
	}
}

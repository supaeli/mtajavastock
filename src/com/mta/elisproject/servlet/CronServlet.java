package com.mta.elisproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.elisproject.exception.PortfolioFullException;
import com.mta.elisproject.exception.StockAlreadyExistsException;

/**
 * @author hanan.gitliz@gmail.com
 * @since Jan 12, 2015
 */
@SuppressWarnings("serial")
public class CronServlet extends AbstractAlgoServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*Portfolio portfolio = portfolioService.getPortfolio();
		if(portfolio.getStocks().length == 0) {
			createMockStocks();
		}*/
		
		try {
			portfolioService.update();
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	/*private void createMockStocks() {
		String[] symbols = {"PIH","GOOG","BIRT","ALLT","ZNGA"};
		for (int i=0; i<symbols.length; i++){
			try {
				portfolioService.addStock(symbols[i]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}

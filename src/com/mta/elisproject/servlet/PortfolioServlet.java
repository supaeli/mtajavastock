package com.mta.elisproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.elisproject.exception.BalanceException;
import com.mta.elisproject.exception.PortfolioFullException;
import com.mta.elisproject.exception.StockAlreadyExist;
import com.mta.elisproject.exception.StockNotExist;
import com.mta.elisproject.model.*;
import com.mta.elisproject.service.PortfolioService ;
/**
 * @author sup4eli
 * inserts data into portfolio instance-happens in portfolioService method
 * 'getPortfolio'
 * presents a Portfolio instance as html String.(on browser)
 *
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PortfolioService portfolioService = new PortfolioService() ;
		Portfolio portfolio1;
		try {
			portfolio1 = portfolioService.getPortfolio();
		} catch (Exception e) {//same exception handling for each current exception
		resp.getWriter().println(e.getMessage());
		}
		resp.setContentType("text/html");
		
		
		
	}
}

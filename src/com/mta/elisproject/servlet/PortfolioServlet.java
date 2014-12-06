package com.mta.elisproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.elisproject.model.*;
import com.mta.elisproject.service.PortfolioService ;

/**
 * @author sup4eli
 * inserts data into portfolio instance-happens in portfolioService method
 * 'getPortfolio'
 * presents a Portfolio instance as html String.(on browser)
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet{
	@SuppressWarnings("unused")//remove in next exercise
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PortfolioService portfolioService = new PortfolioService() ;
		Portfolio portfolio = portfolioService.getPortfolio() ;
		Stock[] stocks = portfolio.getStocks() ;
		String string = new String() ;
		
		string = portfolio.getHtmlString();
		resp.setContentType("text/html");
		resp.getWriter().println(string);
	}
}

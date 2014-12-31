package com.mta.elisproject.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mta.elisproject.model.*;

import com.mta.elisproject.service.PortfolioService ;
/**
<<<<<<< HEAD
 * @author sup4eli
 * inserts data into portfolio instance-happens in portfolioService method
 * 'getPortfolio'
 * presents a Portfolio instance as html String.(on browser)
=======
 * 
 * @author sup4eli
 * inserts data into portfolio instance-happens in portfolioService method
 * 'getPortfolio'
 * presents a Portfolio instance as html String.(on browser)
 *
>>>>>>> branch 'master' of https://github.com/supaeli/mtajavastock.git
 */
@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PortfolioService portfolioService = new PortfolioService() ;
		Portfolio portfolio1 = portfolioService.getPortfolio() ;
		
		
		resp.setContentType("text/html");
		resp.getWriter().println(portfolio1.getHtmlString());//print #1
		
		
	}
}

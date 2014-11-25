/**
 * 
 */
package com.mta.elisproject;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar; 
import java.util.Date ;
/**
 * @author eligr
 *
 */

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
	

		Calendar c =  Calendar.getInstance() ;
		
		Date date = new Date() ;
		c.set(2014, 11, 15) ;
		date = c.getTime() ;
		Stock stock1 = new Stock((float)13.1, (float)12.4, date, "PIH" );//data from exe details) ;
		Stock stock2 = new Stock((float)5.78, (float)5.5, date, "AAL");//data from exe details) ;
		Stock stock3 = new Stock((float)31.2, (float)31.5, date, "CAAS");//data from exe details) ;
		// call for getHTML for each stock, use res.*
		resp.getWriter().println(stock1.getHtmlDescription()+"<br> "+stock2.getHtmlDescription()+"<br> "+stock3.getHtmlDescription()) ;
	}
	
}
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
	
//here we write shit
		Calendar c =  Calendar.getInstance() ;
		
		Date date = new Date() ;
		c.set(2014, 11, 15) ;
		date = c.getTime() ;
	
	}
	
}
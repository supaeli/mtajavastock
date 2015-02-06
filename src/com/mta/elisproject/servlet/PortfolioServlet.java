package com.mta.elisproject.servlet;

import com.mta.elisproject.dto.PortfolioDto;
import com.mta.elisproject.dto.PortfolioTotalStatus;
import com.mta.elisproject.exception.PortfolioFullException;
import com.mta.elisproject.exception.StockAlreadyExistsException;
import com.mta.elisproject.model.StockStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
public class PortfolioServlet extends AbstractAlgoServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
=======
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
>>>>>>> 3b35c5c71645918327c4e0c1024bb16d2df20f2f
		
		PortfolioTotalStatus[] totalStatus = null;
		try {
<<<<<<< HEAD
			totalStatus = portfolioService.getPortfolioTotalStatus();
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
		StockStatus[] stockStatusArray = null;
		try {
			stockStatusArray = portfolioService.getPortfolio().getStocks();
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
		List<StockStatus> stockStatusList = new ArrayList<>();
		for (StockStatus ss : stockStatusArray) {
			if(ss != null)
				stockStatusList.add(ss);
=======
			portfolio1 = portfolioService.getPortfolio();
		} catch (Exception e) {//same exception handling for each current exception
		resp.getWriter().println(e.getMessage());
>>>>>>> 3b35c5c71645918327c4e0c1024bb16d2df20f2f
		}
		
		PortfolioDto pDto = new PortfolioDto();
		try {
			pDto.setTitle(portfolioService.getPortfolio().getTitle());
		} catch (StockAlreadyExistsException e) {
			resp.getWriter().print(e.getMessage());
		} catch (PortfolioFullException e) {
			resp.getWriter().print(e.getMessage());
		}
		pDto.setTotalStatus(totalStatus);
		pDto.setStockTable(stockStatusList);
		resp.getWriter().print(withNullObjects().toJson(pDto));
	}
}

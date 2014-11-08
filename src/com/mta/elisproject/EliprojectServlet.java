package com.mta.elisproject;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class EliprojectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		int  num1, num2, num3, res;
		num1=4;
		num2=3;
		num3=7;
		res=(num1+num2)*num3;
		String resultString = new String("<h1>Result of	("+num1+"+"+num2+")*"+num3+"="+res+"</h1>");
		resp.getWriter().println(resultString);
	}
}

package com.mta.elisproject;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class EliprojectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		int  num1, num2, num3, res;
		num1 = 4;
		num2 = 3;
		num3 = 7;
		res = (num1+num2)*num3;
		
		
		int radius, angleDeg, hypoCem ;
		float circleArea ;
		double opposite,  expResult, base, exponent ;
		radius = 50 ;
		hypoCem = 50 ;
		angleDeg = 30 ;
		base= 20 ;
		exponent= 13 ;
		
		circleArea = (float) (Math.PI * Math.pow(radius, 2)) ;
		String line1 = new String("<h1>Area of circle with radius "+radius+" cm is "+circleArea+" cm</h1>") ;
		
		opposite = Math.sin(Math.toRadians(angleDeg)) * hypoCem ;
		String line2 = new String("<h1>Length of opposite edge with hypotenuse of "+hypoCem+" cm and angle "+angleDeg+" degrees is "+(int)Math.round(opposite)+" cm</h1>") ;
		
		expResult =  Math.pow(base, exponent) ;
		String line3 = new String("<h1>Power of "+(int)base+" with exp of "+(int)exponent+" is "+(long)expResult+" </h1>") ;
		
		String resultString = line1 + "<br>" + line2 + "<br>" + line3 ;
		resp.getWriter().println(resultString) ;
		
	}
}

package com.foodDelivery;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class orderHandler
 */
@WebServlet("/orderHandler")
public class orderHandler extends HttpServlet {
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		List<Food> allFoods = (List)session.getAttribute("allFoods");
		Cart cart = (Cart) session.getAttribute("foodCart");
		System.out.println("Total keys in map "+cart.cart.keySet().size());
		
		 Enumeration enumeration = request.getParameterNames();
		 String parameterName = null;
		 	if(request.getParameter("finalize")!=null) {
	        	cart.printCart();
	        	response.sendRedirect("FinalizeOrder.jsp");
	        	return;
	        }
	        while (enumeration.hasMoreElements()) {
	            parameterName = (String) enumeration.nextElement();
	            
	            System.out.println(parameterName);
	        }
	        
	        String foodId = parameterName.split("_")[0];
	        String operation = parameterName.split("_")[1];
	        int id = Integer.parseInt(foodId);
	        for(Food f: allFoods) {
	        	if(f.getId()==id && operation.equals("add"))
	        	{
	        		cart.addFood(f);
	        		break;
	        	}
	        	if(f.getId()==id && operation.equals("sub"))
	        	{
	        		cart.subFood(f);
	        		break;
	        	}
	        }
	        
	       
	        
	        
		
		
		
			/*
			 * if(request.getParameter("1_add") != null) {
			 * System.out.println("Add Pizza clicked!"); } else
			 * if(request.getParameter("food2Add")!=null) { cart.addFood2(); } else
			 * if(request.getParameter("food3Add")!=null) { cart.addFood3(); } else
			 * if(request.getParameter("food4Add")!=null) { cart.addFood4(); } else
			 * if(request.getParameter("food1Sub")!=null){ cart.removeFood1(); } else
			 * if(request.getParameter("food2Sub")!=null){ cart.removeFood2(); } else
			 * if(request.getParameter("food3Sub")!=null){ cart.removeFood3(); } else
			 * if(request.getParameter("food4Sub")!=null){ cart.removeFood4(); } else
			 * if(request.getParameter("finalize") != null) { System.out.println(cart);
			 * out.println(cart);
			 * 
			 * }
			 */
		
		response.sendRedirect("order.jsp");
	}

}

package com.foodDelivery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/AddFoodHandler")
public class AddFoodHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration params = request.getParameterNames();
		
		while(params.hasMoreElements()) {
			String paramName = (String) params.nextElement();
			System.out.println(paramName);
		}
		
		
		
		 String foodName = request.getParameter("foodName"); 
		 String priceString = request.getParameter("price"); 
		 String img_url = request.getParameter("img_url");
		 
		 try {
				
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
				if(!validateFoodName(foodName, con))
				{
					//redirect to a specific page to show this error....error may occur if foodname already exists 
					response.sendRedirect("duplicate.jsp");
					return;
					
				}
				if(!validatePrice(priceString)) {
					//redirect when price format error
					response.sendRedirect("priceFormat.jsp");
					return;
				}
				con.close();
				PreparedStatement st = con.prepareStatement("insert into food(food_name,img_url,price) values (?,?,?)  ");
				int price = Integer.parseInt(priceString);
				st.setString(1, foodName);
				st.setString(2, img_url);
				st.setInt(3, price);
				
				int i = st.executeUpdate();
				System.out.println(i + " records inserted");
				
				
				
			}
		 	catch(SQLException ex)
		 	{
		 		//redirect to a specific page to show this error
		 		response.sendRedirect("sqlError.jsp");
		 		return;
		 	}
		 	catch(Exception e) {
					e.printStackTrace();
			}
		 
		
		
	}
	
	private boolean validateParams(String food_name,String priceVal, String img_url,Connection con) throws SQLException
	{
		return validateFoodName(food_name, con) && validatePrice(priceVal);
	}

	private boolean validateFoodName(String food_name, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement st = con.prepareStatement("select * from food where food_name = ?");
		st.setString(1, food_name);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			return false;
		}
		return true;
		
		
	}
	
	private boolean validatePrice(String price) {
		// TODO Auto-generated method stub
		try
		{
			int priceInt = Integer.parseInt(price);
			if(priceInt <= 0)
				return false;
		}
		catch(NumberFormatException ex)
		{
			return false;
		}
		return true;
	}

}

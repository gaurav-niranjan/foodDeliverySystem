package com.foodDelivery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/DisableFoodHandler")
public class DisableFoodHandler extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String foodItem = request.getParameter("item");
		
		if(session.getAttribute("adminObj") == null || foodItem == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("update food set isActive=false where food_name = ?");
			st.setString(1, foodItem);
			int i = st.executeUpdate();
			System.out.println(i + " records deleted");
			if(i == 0) {
				response.sendRedirect("fooddisableError.jsp");  
			}
			con.close();
			response.sendRedirect("fooddisableSuccess.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
	}

}

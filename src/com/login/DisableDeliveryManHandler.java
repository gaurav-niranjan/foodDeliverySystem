package com.login;

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

/**
 * Servlet implementation class DisableDeliveryManHandler
 */
@WebServlet("/DisableDeliveryManHandler")
public class DisableDeliveryManHandler extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int deliveryManID  = Integer.parseInt(request.getParameter("item"));
		
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("update delivery_man set isActive=false where delivery_man_id = ?");
			st.setInt(1, deliveryManID);
			int i = st.executeUpdate();
			System.out.println(i + " records deleted");
			if(i == 0) {
				response.sendRedirect("deliveryMandisableError.jsp"); 
			}
			con.close();
			response.sendRedirect("deliveryMandisableSuccess.jsp"); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}

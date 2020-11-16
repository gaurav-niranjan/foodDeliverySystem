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


@WebServlet("/ChangeAddress")
public class ChangeAddress extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("usermail") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		Customer customerObj = (Customer) session.getAttribute("customerObj");
		
		String address = request.getParameter("address");
		
		customerObj.setCustomer_address(address);
		
		int customerID = customerObj.getCustomer_id();
		
		String sql = "update customers set address = ? where customer_id = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, address);
			st.setInt(2, customerID);
			int i  = st.executeUpdate();
			System.out.println(i + " records updated");
			con.close();
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
		response.sendRedirect("order.jsp");
	}

}

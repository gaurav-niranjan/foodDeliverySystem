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


@WebServlet("/ChangeContact")
public class ChangeContact extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("In Post of ChangeContact");
		
		HttpSession session = request.getSession();
		
		System.out.println("Here1");
		
		if(session.getAttribute("usermail") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		System.out.println("Here2");
		
		Customer customerObj = (Customer) session.getAttribute("customerObj");
		
		String contact = request.getParameter("contact");
		
		
		
		if(!validateContact(contact)) {
			response.sendError(10, "Invalid Contact Format");
			System.out.println("Error");
			return;
		}
		
		System.out.println("Here3");
		
		customerObj.setCustomer_contact(contact);
		
		int customerID = customerObj.getCustomer_id();
		
		String sql = "update customers set contact_no = ? where customer_id = ?";
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, contact);
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

	private boolean validateContact(String contact) {
		
		try {
			Long.parseLong(contact);
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
		
	

}

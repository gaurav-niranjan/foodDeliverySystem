package com.foodDelivery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.Customer;

/**
 * Servlet implementation class GetCustomerDetails
 */
@WebServlet("/GetCustomerDetails")
public class GetCustomerDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		String email = request.getParameter("email");
		
		LinkedHashMap<String,String> result = new LinkedHashMap<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select * from customers where email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				
				Customer customer = new Customer(rs.getInt("customer_id"),rs.getString("name"),rs.getString("address"),rs.getString("email"), rs.getString("contact_no"));
				getCustomerOrders(con,customer, result);
				session.setAttribute("customerObj", customer);
				session.setAttribute("customerLogins", result);
				System.out.println(result.size());
				response.sendRedirect("customerFound.jsp");
				return;
			}
			else {
				response.sendRedirect("customerNotFound.jsp");
				return;
			}
			
		}
		catch(Exception e) {
			response.sendRedirect("sqlError.jsp");
			e.printStackTrace();
			return;
		}
		
	
	}

	private void getCustomerOrders(Connection con, Customer customer, HashMap<String, String> result) throws SQLException {
		
		PreparedStatement st = con.prepareStatement("select * from orders where customer_id = ?");
		st.setInt(1, customer.getCustomer_id());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			
			result.put(rs.getString("order_id"), rs.getString("order_created_time"));
			
		}
		
		return;
		
		
	}

	

}

package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CustomerActivity
 */
@WebServlet("/CustomerActivity")
public class CustomerActivity extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
		}
		
		LinkedHashMap<String,String> result = new LinkedHashMap<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select customers.name, customers.customer_id, tmp.login_time from\n" + 
														"customers inner join\n" + 
														"(select * from customer_activity) as tmp\n" + 
														"on customers.customer_id = tmp.customer_id\n" + 
														"order by login_time DESC;");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.put(rs.getString("login_time"), "Customer id: " + rs.getInt("customer_id") + ", Name: " + rs.getString("name"));
				System.out.println("1 record inserted!");
			}
			
			session.setAttribute("customerActivity", result);
			response.sendRedirect("customerActivity.jsp");
			return;
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
	}

	
}

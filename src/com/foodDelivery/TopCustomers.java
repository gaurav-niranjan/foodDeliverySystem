package com.foodDelivery;

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
 * Servlet implementation class TopCustomers
 */
@WebServlet("/TopCustomers")
public class TopCustomers extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		LinkedHashMap<String,Integer> result = new LinkedHashMap<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select tmp.num, customers.email, customers.name from\n" + 
														"customers inner join\n" + 
														"(select  count(*) as num, customer_id\n" + 
														"from orders\n" + 
														"group by customer_id\n" + 
														"order by count(*) DESC\n" + 
														"limit 3) as tmp\n" + 
														"on customers.customer_id = tmp.customer_id;");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.put(rs.getString("name") + " , " + rs.getString("email"), rs.getInt("num"));
			}
			
			session.setAttribute("topCustomers", result);
			response.sendRedirect("topCustomers.jsp");
			return;
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

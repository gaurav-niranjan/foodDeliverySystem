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
 * Servlet implementation class RecentOrders
 */
@WebServlet("/RecentOrders")
public class RecentOrders extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		LinkedHashMap<String,String> result = new LinkedHashMap<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select customers.name, customers.email, tmp.order_id, tmp.order_created_time from\n" + 
														"customers inner join\n" + 
														"(select * from orders) as tmp\n" + 
														"on customers.customer_id = tmp.customer_id\n" + 
														"order by tmp.order_created_time DESC\n" + 
														"limit 5;");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.put(rs.getString("name") + " , " + rs.getString("email") + " , OrderID: " + rs.getString("order_id"), rs.getString("order_created_time"));
			}
			
			session.setAttribute("recentOrders", result);
			response.sendRedirect("recentOrders.jsp");
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

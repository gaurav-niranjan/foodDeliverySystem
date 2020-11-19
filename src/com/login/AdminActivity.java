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
 * Servlet implementation class AdminActivity
 */
@WebServlet("/AdminActivity")
public class AdminActivity extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
		}
		
		LinkedHashMap<String,String> result = new LinkedHashMap<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select admin.username, admin.admin_id, tmp.login_time from\n" + 
														"admin inner join \n" + 
														"(select * from admin_activity) as tmp\n" + 
														"on admin.admin_id = tmp.admin_id\n" + 
														"order by tmp.login_time DESC;");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				result.put(rs.getString("login_time"), "Admin id: " + rs.getInt("admin_id") + ", username: " + rs.getString("username"));
				System.out.println("1 record inserted!");
			}
			
			session.setAttribute("adminActivity", result);
			response.sendRedirect("adminActivity.jsp");
			return;
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
		
	}

	
	

}

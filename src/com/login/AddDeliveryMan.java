package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddDeliveryMan
 */
@WebServlet("/AddDeliveryMan")
public class AddDeliveryMan extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		
		String sql = "insert into delivery_man(name, contact_num) values (?,?);";
		
		if(!validateContact(contact)) {
			response.sendRedirect("invalidContact.jsp");
			return;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setString(2, contact);
			
			if(!uniqueContact(con,contact)) {
				response.sendRedirect("contactExists.jsp");
				return;
			}
			int i = st.executeUpdate();
			System.out.println(i + " records inserted");
			con.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
		response.sendRedirect("deliveryManSuccess.jsp");
		
		
	}
	
	private boolean uniqueContact(Connection con, String contact) throws SQLException {
		
		PreparedStatement st = con.prepareStatement("select * from delivery_man where contact_num = ?");
		st.setString(1, contact);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			//Contact number already exists
			return false;
		}
		return true;
	}

	private boolean validateContact(String conatct_no) {
		
		try {
			Long.parseLong(conatct_no);
		}
		catch(NumberFormatException e) {
			return false;
		}
		return true;
		
	}

}

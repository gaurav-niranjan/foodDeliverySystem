package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		LoginDao dao = new LoginDao();
		
		try {
			Customer customerObj = dao.check(email, pass);
			if(customerObj!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("usermail", email);
				session.setAttribute("customerObj", customerObj);
				logCustomerActivity(customerObj);
				response.sendRedirect("createOrderPage");
				return;
			}
			else
				response.sendRedirect("loginFailed.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
	}

	private void logCustomerActivity(Customer customerObj) throws ClassNotFoundException, SQLException {
		
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("insert into customer_activity(customer_id) values(?)");
			st.setInt(1, customerObj.getCustomer_id());
			int i = st.executeUpdate();
			System.out.println(i + " customer activity logged.");
			return;
			
	}

}

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


@WebServlet("/Register")
public class Register extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String Username = request.getParameter("Username");
		String Address = request.getParameter("Address");
		String conatct_no = request.getParameter("contact");
		
		String sql = "insert into customers(name, address, email, pswd, contact_no) values (? , ? , ? , md5(?), ?)";
		
		if(!validatePassword(pass)) {
			response.sendRedirect("invalidPassword.jsp");
			return;
		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			if(!validateEmail(email,con)) {
				response.sendRedirect("invalidEmail.jsp");
				return;
			}
			if(!validateContact(conatct_no)) {
				response.sendError(10, "Contact number invalid");
				return;
			}
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, Username);
			st.setString(2, Address);
			st.setString(3, email);
			st.setString(4, pass);
			st.setString(5, conatct_no);
			int i = st.executeUpdate();
			System.out.println(i + " records inserted");
			con.close();
			
		}
		catch(Exception e ) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
		response.sendRedirect("registrationSuccess.jsp");
		
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

	private boolean validateEmail(String email, Connection con) throws SQLException {
		
		PreparedStatement st = con.prepareStatement("select * from customers where email = ?");
		st.setString(1, email);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			//Email already exists
			return false;
		}
		return true;
	}

	private boolean validatePassword(String pass) {
		
			if(pass.length()<6)
				return false;
		return true;
	}

}

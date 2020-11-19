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
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		String sql = "select * from admin where username = ? and pswd = md5(?)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, pass);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				int a_id = rs.getInt("admin_id");
				String a_name = rs.getString("username");
				Admin adminObj = new Admin(a_id,a_name);
				session.setAttribute("adminObj", adminObj);
				logAdminActibity(adminObj,con);
				response.sendRedirect("AdminConsole.jsp");
				con.close();
				return;
			}
			else {
				response.sendRedirect("loginFailed.jsp");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
		}
		
	}

	private void logAdminActibity(Admin adminObj, Connection con) throws SQLException {
		
		PreparedStatement st = con.prepareStatement("insert into admin_activity(admin_id) values (?)");
		st.setInt(1, adminObj.getAdmin_id());
		int i = st.executeUpdate();
		System.out.println(i + " admin activity logged");
		return;
		
		
	}

}

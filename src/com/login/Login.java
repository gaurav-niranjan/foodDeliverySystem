package com.login;

import java.io.IOException;
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
				response.sendRedirect("createOrderPage");
			}
			else
				response.sendRedirect("loginFailed.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
		}
		
	}

}

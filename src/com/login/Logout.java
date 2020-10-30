package com.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("allFoods");
		session.removeAttribute("usermail");
		session.removeAttribute("customerObj");
		session.removeAttribute("foodCart");
		session.removeAttribute("cart");
		session.removeAttribute("objects");
		session.invalidate();
		response.sendRedirect("login.jsp");
		
	}

	
}

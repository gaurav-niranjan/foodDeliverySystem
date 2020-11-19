package com.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.foodDelivery.Food;

/**
 * Servlet implementation class DisableDeliveryManPage
 */
@WebServlet("/DisableDeliveryManPage")
public class DisableDeliveryManPage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		List<Delivery_man> enabledDM = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select * from delivery_man where isActive=true");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("delivery_man_id");
				String contact = rs.getString("contact_num");
				String n = rs.getString("name");
				Delivery_man deliveryMan = new Delivery_man(id, contact, n);
				enabledDM.add(deliveryMan);
			}
			
			
			
			session.setAttribute("enabledDM", enabledDM);
			
			response.sendRedirect("disableDeliveryMan.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
			
		
		
	}

	

}

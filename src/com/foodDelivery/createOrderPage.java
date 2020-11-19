package com.foodDelivery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class createOrderPage
 */
@WebServlet("/createOrderPage")
public class createOrderPage extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj")!=null) {
			response.sendRedirect("AdminConsole.jsp");
			return;
		}
		if(session.getAttribute("usermail")==null || session.getAttribute("customerObj")==null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		System.out.println("inside get of createOrderPage");
		
		List<Food> allFoods = new ArrayList<>();
		
		Cart cart = new Cart();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("select * from food");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("food_id");
				String u = rs.getString("img_url");
				String n = rs.getString("food_name");
				int pr = rs.getInt("price");
				boolean isActive = rs.getBoolean("isActive");
				if(isActive) {
					Food f = new Food(id,u,pr,n);
					allFoods.add(f);
				}

				System.out.println("Food with id "+ id + " found.");
				
			}
			System.out.println("Number of all foods = " + allFoods.size());
			
			
			session.setAttribute("allFoods", allFoods);
			session.setAttribute("foodCart", cart);
			
			System.out.println("Sending redirect");
			response.sendRedirect("order.jsp");
			
			
		}	catch(Exception e) {
				response.sendRedirect("errorPage.jsp");
				e.printStackTrace();
				return;
		}
		
	}

	

}



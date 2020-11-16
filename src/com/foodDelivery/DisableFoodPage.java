package com.foodDelivery;

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

@WebServlet("/DeleteFoodPage")
public class DeleteFoodPage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		List<Food> allFoods = new ArrayList<>();
		
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
				Food f = new Food(id,u,pr,n);
				allFoods.add(f);
				
				System.out.println("Food with id "+ id + " found.");
				
			}
			System.out.println("Number of all foods = " + allFoods.size());
			
			
			session.setAttribute("allFoods", allFoods);
			
			response.sendRedirect("deleteFood.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
	}

	
}

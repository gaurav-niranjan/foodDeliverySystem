package com.foodDelivery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetOrderDetails
 */
@WebServlet("/GetOrderDetails")
public class GetOrderDetails extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("adminObj") == null) {
			response.sendRedirect("home.jsp");
			return;
		}
		
		String orderid = request.getParameter("orderID");
		List<OrderDetails> orderDetails = new ArrayList<>();
		if(!validOrderID(orderid)) {
			response.sendRedirect("invalidOrderID.jsp");
			return;
		}
		
		int orderId = Integer.parseInt(orderid);
		
		
		try {
			Connection con = sqlConnection();
			List<Food> allFoodsinDB = new ArrayList<>();
			getFoodsInDB(con, allFoodsinDB);
			PreparedStatement st = con.prepareStatement("select * from orders where order_id = ?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st.setInt(1, orderId);
			ResultSet rs = st.executeQuery();
			
			if(!rs.next()) {
				//no previous orders made by this customer
				response.sendRedirect("invalidOrderID.jsp");
				return;
			}
			
			rs.beforeFirst();
			
			while(rs.next()) {
				int orderID = rs.getInt(1);
				String createdTime = rs.getString(3);
				int total = rs.getInt(4);
				OrderDetails details = new OrderDetails(orderID, createdTime, total);
				fillOrderDetails(details,allFoodsinDB);
				fillDeliveryMan(details,con);
				orderDetails.add(details);
				
			}
			session.setAttribute("order_details", orderDetails);
			response.sendRedirect("orderDetails.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			//sql erros...redirect
			response.sendRedirect("sqlError.jsp");
			
			e.printStackTrace();
			return;
		}
		
		
	}
	
	private void fillDeliveryMan(OrderDetails details, Connection con) throws SQLException {
		PreparedStatement st = con.prepareStatement("select * from delivery_details where order_id = ?");
		st.setInt(1, details.getOrderID());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			details.setDeliveryMan(rs.getInt("delivery_man_id"), rs.getString("name"), rs.getString("contact_num"));
		}
		
	}
	
	private boolean validOrderID(String orderID) {
		try {
			Integer.parseInt(orderID);
		}
		catch(NumberFormatException e) {
			return false;
		}
		
		return true;
	}

	private Connection sqlConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
		return con;
	}
	
	private void getFoodsInDB(Connection con, List<Food> allFoodsinDB) throws SQLException {
		
		
		PreparedStatement st = con.prepareStatement("select * from food");
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("food_id");
			String u = rs.getString("img_url");
			String n = rs.getString("food_name");
			int pr = rs.getInt("price");
			Food f = new Food(id,u,pr,n);
			allFoodsinDB.add(f);
		}
		
	}
	
	private OrderDetails fillOrderDetails(OrderDetails details, List<Food> allFoodsinDB) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = sqlConnection();
		String query = "SELECT * FROM order_details WHERE order_id=?";
		PreparedStatement st = con.prepareStatement(query);
		
		st.setInt(1, details.getOrderID());
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			int foodId = rs.getInt(2);
			int quantity = rs.getInt(4);
			Food food = getFoodFromId(allFoodsinDB,foodId);
			details.addFoodItem(food, quantity);
		}
		return null;
	}
	
	private Food getFoodFromId(List<Food> allFoodsinDB, int foodId) {
		// TODO Auto-generated method stub
		for(Food food : allFoodsinDB)
		{
			if(food.getId() == foodId)
				return food;
		}
		return null;
	}
	
	

	

}

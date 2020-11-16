package com.foodDelivery;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.Customer;

@WebServlet("/orderPlaceDao")
public class orderPlaceDao extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		HttpSession session = request.getSession();
		if(session.getAttribute("usermail") == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		Cart cart = (Cart) session.getAttribute("foodCart");
		Customer customerObj = (Customer) session.getAttribute("customerObj");
		if(cart.getCart().size() == 0) {
			response.sendRedirect("EmptyCart.jsp"); //cart is empty
			return;
		}
		int customerID = customerObj.getCustomer_id();
		int total_price = cart.calculateTotalPrice();
		int orderId = createOrderInDb(customerID,total_price);
		try {
			insertOrderDetails(cart, orderId);
		}
		catch(ClassNotFoundException | SQLException e) {
			response.sendRedirect("sqlError.jsp");
			return;
		}
		
		System.out.println(orderId);
		
		response.sendRedirect("OrderSuccess.jsp");
		
		
		
		
	}
	
	private void insertOrderDetails(Cart cart, int orderId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		HashMap<Food,Integer> foodCart = cart.getCart();
		Iterator it = foodCart.entrySet().iterator();
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Food food = (Food)pair.getKey();
			int foodId = food.getId();
			int quantity = (Integer)pair.getValue();
			int price = food.getPrice();
			saveOrderDetails(orderId,foodId,price,quantity,con);
		
		}
			
		
		
		
	}

	private void saveOrderDetails(int orderId, int foodId,int price, int quantity,Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
		PreparedStatement st = con.prepareStatement("insert into order_details values(?,?,?,?)");
		st.setInt(1, orderId);
		st.setInt(2, foodId);
		st.setInt(3, price);
		st.setInt(4, quantity);
		int rowsInserted = st.executeUpdate();
		if(rowsInserted != 1) {
			throw new SQLException();
		}
	}

	private int createOrderInDb(int customerID, int totalPrice) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
			PreparedStatement st = con.prepareStatement("insert into orders(customer_id,total_price) values(?,?)  ",Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, customerID);
			st.setInt(2, totalPrice);
			int rowsInserted = st.executeUpdate();
			if(rowsInserted!=1)
			{
				throw new SQLException();
			}
			ResultSet rs = st.getGeneratedKeys();
			if(!rs.next()) {
				throw new SQLException();
			}
			int orderId = rs.getInt(1);
			return orderId;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	

}

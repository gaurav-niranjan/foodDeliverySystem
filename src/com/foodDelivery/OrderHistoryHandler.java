package com.foodDelivery;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.Customer;


@WebServlet("/OrderHistoryHandler")
public class OrderHistoryHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Customer customerObj = (Customer) session.getAttribute("customerObj");
		//List<Food> allFoods = (List<Food>) session.getAttribute("allFoods");
		int customerID = customerObj.getCustomer_id();
		List<OrderDetails> orderDetails = new ArrayList<>();
		
		try {
			Connection con = sqlConnection();
			List<Food> allFoodsinDB = new ArrayList<>();
			getFoodsInDB(con, allFoodsinDB);
			PreparedStatement st = con.prepareStatement("select * from orders where customer_id = ?",ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			st.setInt(1, customerID);
			ResultSet rs = st.executeQuery();
			System.out.println("First query executed");
			if(!rs.next()) {
				//no previous orders made by this customer
				response.sendRedirect("noOrders.jsp");
				return;
			}
			System.out.println("Before while loop");
			rs.beforeFirst();
			
			while(rs.next()) {
				int orderID = rs.getInt(1);
				String createdTime = rs.getString(3);
				int total = rs.getInt(4);
				OrderDetails details = new OrderDetails(orderID, createdTime, total);
				fillOrderDetails(details,allFoodsinDB);
				orderDetails.add(details);
				System.out.println("Inside while loop");
			}
			session.setAttribute("order_history", orderDetails);
			response.sendRedirect("orderHistory.jsp");
			
		} catch (ClassNotFoundException | SQLException e) {
			//sql erros...redirect
			response.sendRedirect("sqlError.jsp");
			
			e.printStackTrace();
			return;
		}
		
		
		
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
		System.out.println("fill ORder details method");
		st.setInt(1, details.getOrderID());
		System.out.println("Order ID =  " +details.getOrderID());
		ResultSet rs = st.executeQuery();
		System.out.println("Second query executed");
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

	private Connection sqlConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodDelivery","root","");
		return con;
	}


}

package com.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


	public class LoginDao {
		
		String sql = "select * from customers where email = ? and pswd = md5(?)";
		String url = "jdbc:mysql://localhost:3306/foodDelivery";
		String username = "root";
		String password = "";
		
		public Customer check(String email, String pass) throws ClassNotFoundException {
			
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,username,password);
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1, email);
				st.setString(2, pass);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					int c_id = rs.getInt("customer_id");
					String c_name = rs.getString("name");
					String c_address = rs.getString("address");
					String c_email = rs.getString("email");
					Customer customerObj = new Customer(c_id,c_name,c_address,c_email);
					return customerObj;
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				
			}
			
			
			
			
			return null;
			
		}
	}



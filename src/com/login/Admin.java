package com.login;

public class Admin {
	private int admin_id;
	private String username;
	
	
	public Admin(int admin_id, String username) {
		super();
		this.admin_id = admin_id;
		this.username = username;
	}


	public int getAdmin_id() {
		return admin_id;
	}


	public String getUsername() {
		return username;
	}
	
}

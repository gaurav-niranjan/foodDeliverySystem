package com.login;

public class Customer {
	
	public int customer_id;
	public String customer_name;
	public String customer_address;
	public String customer_email;
	
	
	public Customer(int customer_id, String customer_name, String customer_address, String customer_email) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_address = customer_address;
		this.customer_email = customer_email;
	}


	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getCustomer_address() {
		return customer_address;
	}


	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}


	public String getCustomer_email() {
		return customer_email;
	}


	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}
	
	
	
	
	
}

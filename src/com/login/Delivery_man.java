package com.login;

public class Delivery_man {
	int delivery_man_id;
	String contact_num;
	String name;
	
	
	
	public Delivery_man(int delivery_man_id, String contact_num, String name) {
		super();
		this.delivery_man_id = delivery_man_id;
		this.contact_num = contact_num;
		this.name = name;
	}
	
	
	public int getDelivery_man_id() {
		return delivery_man_id;
	}
	public void setDelivery_man_id(int delivery_man_id) {
		this.delivery_man_id = delivery_man_id;
	}
	public String getContact_num() {
		return contact_num;
	}
	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

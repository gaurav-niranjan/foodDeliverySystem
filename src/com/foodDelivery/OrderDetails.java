package com.foodDelivery;

import java.util.HashMap;

import com.login.Delivery_man;

public class OrderDetails {
	public int orderID;
	Cart cart;
	String created_time;
	int totalPrice;
	Delivery_man deliveryMan;
	
	
	public OrderDetails(int id, String createdTime, int total) {
		this.orderID = id;
		this.created_time = createdTime;
		this.totalPrice = total;
		this.cart = new Cart();
		
	}
	
	public void addFoodItem(Food food, int quantity)
	{
		cart.addFoodWithQuantity(food, quantity);
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setDeliveryMan(int id, String name, String contact_num) {
		deliveryMan = new Delivery_man(id, contact_num, name);
	}

	public Delivery_man getDeliveryMan() {
		return deliveryMan;
	}
	
	
	
	
}

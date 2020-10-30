package com.foodDelivery;

public class Food {
	public int id;
	public String url;
	public int price;
	public String name;
	
	
	public Food(int id, String url, int price, String name) {
		super();
		this.id = id;
		this.url = url;
		this.price = price;
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	
}

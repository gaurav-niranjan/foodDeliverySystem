package com.foodDelivery;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cart {
	
	public HashMap<Food,Integer> cart = new HashMap<>();
	
	
	public Cart() {
		super();
		
	}
	
	public void addFood(Food f) {
		// TODO Auto-generated method stub
		if(cart.get(f)==null)
		{
			
			cart.put(f, 1);
		}
		else
		{
			int existingQunatity = cart.get(f);
			cart.put(f, existingQunatity+1);
		}
		
		
	}
	
	public void subFood(Food f) {
		if(cart.get(f)!=null) {
			int existingQuantity = cart.get(f);
			
			if(existingQuantity - 1 == 0) {
				cart.remove(f);
			}
			else {
				cart.put(f, existingQuantity - 1);
			}
		}
		
	}

	
	public void printCart() {
		Iterator it = cart.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Food food = (Food)pair.getKey();
			System.out.println(food.getName() + "=" + (Integer)pair.getValue());
		}
	}

	public HashMap<Food, Integer> getCart() {
		return cart;
	}

	public int calculateTotalPrice() {
		// TODO Auto-generated method stub
		int totalPrice = 0;
		Iterator it = cart.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Food food = (Food)pair.getKey();
			int quantity = (Integer)pair.getValue();
			totalPrice += quantity * food.getPrice();
		}
		
		return totalPrice;
	}
	
	public void addFoodWithQuantity(Food f, int quantity)
	{
		cart.put(f, quantity);
	}

}

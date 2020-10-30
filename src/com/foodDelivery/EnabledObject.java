package com.foodDelivery;

public class EnabledObject {
	public Food f;
	public int isEnabled;
	
	public EnabledObject(Food f, int isEnabled) {
		super();
		this.f = f;
		this.isEnabled = isEnabled;
	}
	
	public Food getF() {
		return f;
	}
	public void setF(Food f) {
		this.f = f;
	}
	public int getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(int isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	
}

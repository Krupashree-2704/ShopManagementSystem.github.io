package com.qsp.shop.model;

public class Product {
	//based on d/b var make here variables=>(names of var should be same)
	private int p_id;
	private String p_name;
	private int p_price;
	private int p_quantity;
	private boolean p_availability;
	//getter-setter shcut->alt+S+R
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_quantity() {
		return p_quantity;
	}
	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}
	public boolean isP_availability() {
		return p_availability;
	}
	public void setP_availability(boolean p_availability) {
		this.p_availability = p_availability;
	}
	
}

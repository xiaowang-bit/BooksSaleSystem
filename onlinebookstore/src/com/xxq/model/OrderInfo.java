package com.xxq.model;

public class OrderInfo {
	private String id;
	private String orderId;
	private int  num;
	private float price;
	private int status;
	private String user_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public OrderInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderInfo(String id, String orderId, int num, float price, int status, String user_id) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.num = num;
		this.price = price;
		this.status = status;
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Orderinfo [id=" + id + ", orderId=" + orderId + ", num=" + num + ", price=" + price + ", status="
				+ status + ", user_id=" + user_id + "]";
	}
	
}

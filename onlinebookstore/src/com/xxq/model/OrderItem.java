package com.xxq.model;

public class OrderItem {
	private String id;
	private int num;
	private float price;
	private String orderInfo_id;
	private String book_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getOrderInfo_id() {
		return orderInfo_id;
	}
	public void setOrderInfo_id(String orderInfo_id) {
		this.orderInfo_id = orderInfo_id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(String id, int num, float price, String orderInfo_id, String book_id) {
		super();
		this.id = id;
		this.num = num;
		this.price = price;
		this.orderInfo_id = orderInfo_id;
		this.book_id = book_id;
	}
	@Override
	public String toString() {
		return "Orderitem [id=" + id + ", num=" + num + ", price=" + price + ", orderInfo_id=" + orderInfo_id
				+ ", book_id=" + book_id + "]";
	}
	
}

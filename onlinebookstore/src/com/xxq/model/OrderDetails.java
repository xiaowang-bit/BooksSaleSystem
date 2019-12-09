package com.xxq.model;

public class OrderDetails {
	private Book book=null;
	private OrderItem orderItem=null;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public OrderItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}
	public OrderDetails() {
		super();
	}
	public OrderDetails(Book book, OrderItem orderItem) {
		super();
		this.book = book;
		this.orderItem = orderItem;
	}
	@Override
	public String toString() {
		return "OrderDetails [book=" + book + ", orderItem=" + orderItem + "]";
	}
	
}

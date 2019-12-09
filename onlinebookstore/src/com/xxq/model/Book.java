package com.xxq.model;

import com.mchange.v2.encounter.StrongEqualityEncounterCounter;

public class Book {
	private String id;
	private String bookname;
	private String author;
	private float price;
	private String imageName;
	private String description;
	private String category_id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String id, String bookname, String author, float price, String imageName, String description,
			String category_id) {
		super();
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		this.imageName = imageName;
		this.description = description;
		this.category_id = category_id;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookname=" + bookname + ", author=" + author + ", price=" + price + ", imageName="
				+ imageName + ", description=" + description + ", category_id=" + category_id + "]";
	}
	
}

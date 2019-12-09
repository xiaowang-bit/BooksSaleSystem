package com.xxq.model;

public class User {
	private String id;
	private String username;
	private String password;
	private String cellphone;
	private String mobilephone;
	private String address;
	private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String id, String username, String password, String cellphone, String mobilephone, String address,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.cellphone = cellphone;
		this.mobilephone = mobilephone;
		this.address = address;
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", cellphone=" + cellphone
				+ ", mobilephone=" + mobilephone + ", address=" + address + ", email=" + email + "]";
	}
	
}

package com.example.entity;

public class AccDetails {


	private int id;
	private String user_name;
	private long phone_no;
	private String address;
	private double balance;
	private String type;
	private int age;

	public AccDetails(String user_name,long phone_no, String address, double opening_balance, String type, int age) {
		super();
	//	this.id = id;
		this.user_name = user_name;
		this.phone_no = phone_no;
		this.address = address;
		this.balance = opening_balance;
		this.type=type;
		this.age=age;
	}
	
	public AccDetails() {
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUser_name() {
		return user_name;
	}


	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	public long getPhone_no() {
		return phone_no;
	}


	public void setPhone_no(long phone_no) {
		this.phone_no = phone_no;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "AccDetails [id :" + id + ", user_name : " + user_name + ", phone_no : " + phone_no +", address : "+address+", balance : " +balance+", type : "+type+", age : "+age+"]";
}

}
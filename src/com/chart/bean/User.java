package com.chart.bean;

import java.io.Serializable;

public class User  implements Serializable{

	private String email;
	private String password;
	
	private String nickname;
	private String phone;
	public User() {
		// TODO Auto-generated constructor stub
	}
	public User(String email, String password, String nickname) {
		super();
		this.email = email;
		this.password = password;
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

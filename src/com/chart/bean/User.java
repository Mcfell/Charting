package com.chart.bean;

import java.io.Serializable;

public class User  implements Serializable{

	private String email;
	private String password;
	private String nickname;
	private String phone;
	private String rank;
	private String winNum;
	private String loseNum;
	private String level;
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
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getWinNum() {
		return winNum;
	}
	public void setWinNum(String winNum) {
		this.winNum = winNum;
	}
	public String getLoseNum() {
		return loseNum;
	}
	public void setLoseNum(String loseNum) {
		this.loseNum = loseNum;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password
				+ ", nickname=" + nickname + ", phone=" + phone + ", rank="
				+ rank + ", winNum=" + winNum + ", loseNum=" + loseNum
				+ ", level=" + level + "]";
	}
	
}

package com.chart.bean;

public abstract class BasicMessage {
	private int t;				//type
	private String to;			//sendToElemy
	private int time;			//time
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}

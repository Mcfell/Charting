package com.chart.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class C2BMessage extends BasicMessage{
	private int t;				//type
	private String to;			//sendToElemy
	private Boal a;				//sentToAll
	private String c;			//chart
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
	public Boal getA() {
		return a;
	}
	public void setA(Boal a) {
		this.a = a;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public C2BMessage(int t, String to, Boal a, String c, int time) {
		super();
		this.t = t;
		this.to = to;
		this.a = a;
		this.c = c;
		this.time = time;
	}
	@Override
	public String toString() {
		return "Message [t=" + t + ", to=" + to + ", a=" + a + ", c=" + c
				+ ", time=" + time + "]";
	}
	
}

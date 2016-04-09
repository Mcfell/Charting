package com.chart.bean;

import javax.websocket.Session;

public class BatSession {
	  private String batNum;
	  private Session session;
	  private Boolean isBat;
	public String getBatNum() {
		return batNum;
	}
	public void setBatNum(String batNum) {
		this.batNum = batNum;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Boolean getIsBat() {
		return isBat;
	}
	public void setIsBat(Boolean isBat) {
		this.isBat = isBat;
	}
	  
}

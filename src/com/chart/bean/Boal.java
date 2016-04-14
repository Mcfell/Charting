package com.chart.bean;

public class Boal {
	private float x;
	private float y;
	private int vx;
	private int vy;
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getVx() {
		return vx;
	}
	public void setVx(int vx) {
		this.vx = vx;
	}
	public int getVy() {
		return vy;
	}
	public void setVy(int vy) {
		this.vy = vy;
	}
	public Boal(float x, float y, int vx, int vy) {
		super();
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}
	
}

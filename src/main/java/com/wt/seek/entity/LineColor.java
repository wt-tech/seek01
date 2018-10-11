package com.wt.seek.entity;

/**
 * 生成二维码需要的参数,
 * 和后台数据库表无关
 * @author Daryl
 */
public class LineColor {
	
	private String r;
	private String g;
	private String b;
	
	
	public String getR() {
		return r;
	}
	public void setR(String r) {
		this.r = r;
	}
	public String getG() {
		return g;
	}
	public void setG(String g) {
		this.g = g;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	@Override
	public String toString() {
		return "LineColor [r=" + r + ", g=" + g + ", b=" + b + "]";
	}
	
	
	
}

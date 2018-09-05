package com.wt.seek.entity;

import java.util.Date;

public class Mark {
	private int id;
	private Seek seek;
	private Customer customer;
	private Date markTime;// 收藏日期

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Seek getSeek() {
		return seek;
	}

	public void setSeek(Seek seek) {
		this.seek = seek;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getMarkTime() {
		return markTime;
	}

	public void setMarkTime(Date markTime) {
		this.markTime = markTime;
	}

}

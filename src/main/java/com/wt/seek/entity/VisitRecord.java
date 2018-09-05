package com.wt.seek.entity;

import java.util.Date;

public class VisitRecord {
	private int id;
	private Customer customer;
	private Date visitTime;// 收藏日期

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

}

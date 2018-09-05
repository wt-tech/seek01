package com.wt.seek.entity;

import java.util.Date;

public class BrowseHistory {
	private int id;
	private Seek seek;
	private Customer customer;
	private Date lastBrowseTime;// 最后一次浏览时间

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

	public Date getLastBrowseTime() {
		return lastBrowseTime;
	}

	public void setLastBrowseTime(Date lastBrowseTime) {
		this.lastBrowseTime = lastBrowseTime;
	}

}

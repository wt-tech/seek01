package com.wt.seek.entity;

import java.util.Date;

public class TopComent {
	private int id;
	private Seek seek;
	private Customer customer;
	private String content;// 评论内容
	private Date comentTime;// 评论时间

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getComentTime() {
		return comentTime;
	}

	public void setComentTime(Date comentTime) {
		this.comentTime = comentTime;
	}

}

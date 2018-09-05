package com.wt.seek.entity;

import java.util.Date;

public class Coment {
	private int id;
	private Seek seek;
	private TopComent topComent;
	private int topComentId;
	private int comentId;
	private Coment coment;
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

	public int getTopComentId() {
		return topComentId;
	}

	public void setTopComentId(int topComentId) {
		this.topComentId = topComentId;
	}

	public int getComentId() {
		return comentId;
	}

	public void setComentId(int comentId) {
		this.comentId = comentId;
	}

	public TopComent getTopComent() {
		return topComent;
	}

	public void setTopComent(TopComent topComent) {
		this.topComent = topComent;
	}

	public Coment getComent() {
		return coment;
	}

	public void setComent(Coment coment) {
		this.coment = coment;
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

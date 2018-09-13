package com.wt.seek.entity;

import java.util.Date;
import java.util.List;

public class TopComent {
	private int id;
	private int seekId;
	private Seek seek;
	private Customer customer;
	private String content;// 评论内容
	private Date comentTime;// 评论时间
	private List<Coment> coment;// 子评论

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeekId() {
		return seekId;
	}

	public void setSeekId(int seekId) {
		this.seekId = seekId;
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

	public List<Coment> getComent() {
		return coment;
	}

	public void setComent(List<Coment> coment) {
		this.coment = coment;
	}

}

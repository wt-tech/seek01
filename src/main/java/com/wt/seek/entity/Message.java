package com.wt.seek.entity;

public class Message {
	private int id;
	private Customer customer;
	private int newMessage;// 是否有新消息提醒

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

	public int getNewMessage() {
		return newMessage;
	}

	public void setNewMessage(int newMessage) {
		this.newMessage = newMessage;
	}

}

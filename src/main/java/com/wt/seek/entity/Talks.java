package com.wt.seek.entity;

import java.util.Date;

public class Talks {

	private Integer replyId;	
	private String	replyContent;
	private String	replyCustomerName;
	private String	comentCustomerName;
	private Date 	replyTime;
	

	public Integer getReplyId() {
		return replyId;
	}
	public void setReplyId(Integer replyId) {
		this.replyId = replyId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyCustomerName() {
		return replyCustomerName;
	}
	public void setReplyCustomerName(String replyCustomerName) {
		this.replyCustomerName = replyCustomerName;
	}
	public String getComentCustomerName() {
		return comentCustomerName;
	}
	public void setComentCustomerName(String comentCustomerName) {
		this.comentCustomerName = comentCustomerName;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
}

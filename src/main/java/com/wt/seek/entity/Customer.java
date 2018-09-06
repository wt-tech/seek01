package com.wt.seek.entity;

import java.util.Date;

public class Customer {
	private int id;
	private String openid;// 用户
	private String nickname;// 昵称
	private String gender;// 性别
	private String avatarurl;// 头像地址
	private Date firstVisitTime;// 首次访问时间
	private String realname;//真实姓名
	private String tel;//联系方式

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatarurl() {
		return avatarurl;
	}

	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}

	public Date getFirstVisitTime() {
		return firstVisitTime;
	}

	public void setFirstVisitTime(Date firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
 
}

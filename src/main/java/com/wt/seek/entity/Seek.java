package com.wt.seek.entity;

import java.util.Date;

public class Seek {
	private int id;
	private Customer customer;// 用户
	private String sequence;// 编号
	private String seekType;// 类别
	private String missName;// 姓名
	private String gender;// 性别
	private Date birthdate;// 出生日期
	private Date missDate;// 失踪日期
	private Address address;// 地址
	private String missDetailPlace;// 详细地址
	private String feature;// 相貌特征
	private String plot;// 失踪经过
	private String seekimgs;//图片
	private String seekSubtype;// 失踪类别
	private String contactName;// 联系人姓名
	private String relationship;// 与失踪人关系
	private String contactTel;// 联系人手机
	private String contactWechat;// 联系人微信
	private String contactQQ;// 联系人QQ
	private String contactAddress;// 联系人住址
	private String extraTel;// 备用手机号码
	private String title;// 标题
	private Date pubdate;// 发布时间
	private String otherInformation;//其它信息
	private String seekStatus;//其它信息

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

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSeekType() {
		return seekType;
	}

	public void setSeekType(String seekType) {
		this.seekType = seekType;
	}

	public String getMissName() {
		return missName;
	}

	public void setMissName(String missName) {
		this.missName = missName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getMissDate() {
		return missDate;
	}

	public void setMissDate(Date missDate) {
		this.missDate = missDate;
	}
    
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMissDetailPlace() {
		return missDetailPlace;
	}

	public void setMissDetailPlace(String missDetailPlace) {
		this.missDetailPlace = missDetailPlace;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getSeekimgs() {
		return seekimgs;
	}

	public void setSeekimgs(String seekimgs) {
		this.seekimgs = seekimgs;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getSeekSubtype() {
		return seekSubtype;
	}

	public void setSeekSubtype(String seekSubtype) {
		this.seekSubtype = seekSubtype;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactWechat() {
		return contactWechat;
	}

	public void setContactWechat(String contactWechat) {
		this.contactWechat = contactWechat;
	}

	public String getContactQQ() {
		return contactQQ;
	}

	public void setContactQQ(String contactQQ) {
		this.contactQQ = contactQQ;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getExtraTel() {
		return extraTel;
	}

	public void setExtraTel(String extraTel) {
		this.extraTel = extraTel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public String getOtherInformation() {
		return otherInformation;
	}

	public void setOtherInformation(String otherInformation) {
		this.otherInformation = otherInformation;
	}

	public String getSeekStatus() {
		return seekStatus;
	}

	public void setSeekStatus(String seekStatus) {
		this.seekStatus = seekStatus;
	}
	
}

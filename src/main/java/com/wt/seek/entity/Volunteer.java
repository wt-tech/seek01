package com.wt.seek.entity;

public class Volunteer {
	private int id;
	private String sequence;// 志愿者编号
	private Customer customer;//
	private String customerName;// 姓名
	private String negativIdentityUrl;// 反面照
	private String positiveIdentityUrl;// 正面照
	private String identityNO;// 身份证号
	private String address;// 住址
	private String tel;// 手机号
	private int volResult;//判断是否通过

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNegativIdentityUrl() {
		return negativIdentityUrl;
	}

	public void setNegativIdentityUrl(String negativIdentityUrl) {
		this.negativIdentityUrl = negativIdentityUrl;
	}

	public String getPositiveIdentityUrl() {
		return positiveIdentityUrl;
	}

	public void setPositiveIdentityUrl(String positiveIdentityUrl) {
		this.positiveIdentityUrl = positiveIdentityUrl;
	}

	public String getIdentityNO() {
		return identityNO;
	}

	public void setIdentityNO(String identityNO) {
		this.identityNO = identityNO;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getVolResult() {
		return volResult;
	}

	public void setVolResult(int volResult) {
		this.volResult = volResult;
	}

}

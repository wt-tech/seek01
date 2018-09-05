package com.wt.seek.entity;

public class Authentication {
	private int id;
	private Customer customer;// 用户
	private String customerName;// 姓名
	private String identityNO;// 身份证号
	private String negativIdentityUrl;// 反面照
	private String positiveIdentityUrl;// 正面照
	private String address;// 住址
	private String tel;// 手机号
	private String authResult;// 认证结果

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdentityNO() {
		return identityNO;
	}

	public void setIdentityNO(String identityNO) {
		this.identityNO = identityNO;
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

	public String getAuthResult() {
		return authResult;
	}

	public void setAuthResult(String authResult) {
		this.authResult = authResult;
	}

}

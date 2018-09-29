package com.wt.seek.entity;

public class Volunteer {
	private int id;
	private String sequence;// 志愿者编号
	private Customer customer;//
	private String customerName;// 姓名
	private String negativeIdentityUrl;// 反面照
	private String positiveIdentityUrl;// 正面照
	private String identityNO;// 身份证号
	private String address;// 住址
	private VolunteerArea volunteerarea;
	private String tel;// 手机号
	private String volResult;//判断是否通过

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

	public String getNegativeIdentityUrl() {
		return negativeIdentityUrl;
	}

	public void setNegativeIdentityUrl(String negativeIdentityUrl) {
		this.negativeIdentityUrl = negativeIdentityUrl;
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

	public String getVolResult() {
		return volResult;
	}

	public void setVolResult(String volResult) {
		this.volResult = volResult;
	}

	public VolunteerArea getVolunteerarea() {
		return volunteerarea;
	}

	public void setVolunteerarea(VolunteerArea volunteerarea) {
		this.volunteerarea = volunteerarea;
	}

}

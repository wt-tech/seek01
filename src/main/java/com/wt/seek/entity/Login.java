package com.wt.seek.entity;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.wt.seek.inface.Get;
import com.wt.seek.inface.Update;

public class Login {

	private Integer id; // id
	@NotBlank(message="{login.usercode}",groups= {Get.class,Update.class})
	private String userCode; // 用户编码
	private String userName; // 用户名称
	@NotBlank(message="{login.password}",groups= {Get.class,Update.class})
	private String userPassword; // 用户密码
	private List<Role> roles;
	
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Login() {
		super();
	}

	public Login(Integer id, String userCode, String userName, String userPassword) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", userCode=" + userCode + ", userName=" + userName + ", userPassword="
				+ userPassword + ", roles=" + roles + "]";
	}

	
}

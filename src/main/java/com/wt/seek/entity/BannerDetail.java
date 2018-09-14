package com.wt.seek.entity;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 轮播图的详情页
 * @author Daryl
 */
public class BannerDetail {
	
	@Min(value=1,message="{bannerDetail.id}")
	private Integer id;
	
	@Min(value=1,message="{bannerDetail.bannerId}")
	private Integer bannerId;
	
	@NotEmpty(message="{bannerDetail.content}")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	
}

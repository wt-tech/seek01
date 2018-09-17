package com.wt.seek.entity;

public class Banner {

	private int id;
	private String imgName;
	private String url;
	private String uploadTime;
	private boolean onUse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		System.err.println(url);
		this.url = url;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public boolean isOnUse() {
		return onUse;
	}

	public void setOnUse(boolean onUse) {
		this.onUse = onUse;
	}

	public Banner() {
		
	}

}

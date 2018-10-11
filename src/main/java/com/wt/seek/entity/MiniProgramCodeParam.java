package com.wt.seek.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.wt.seek.inface.Get;

/**
 * 生成二维码需要的参数,和后台数据库表无关,说明:<br/>
 * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/qr-code/getWXACodeUnlimit.html
 * @author Daryl
 */
public class MiniProgramCodeParam {
	
	@NotBlank(message="{code.access_token}",groups= {Get.class})
	private String access_token;
	private String scene;
	@NotBlank(message="{code.path}",groups= {Get.class})
	private String path;
	private String width;
	private Boolean auto_color;
	private Boolean is_hyaline;
	private LineColor line_color;
	
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getScene() {
		return scene;
	}
	public void setScene(String scene) {
		this.scene = scene;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public Boolean getAuto_color() {
		return auto_color;
	}
	public void setAuto_color(Boolean auto_color) {
		this.auto_color = auto_color;
	}
	public Boolean getIs_hyaline() {
		return is_hyaline;
	}
	public void setIs_hyaline(Boolean is_hyaline) {
		this.is_hyaline = is_hyaline;
	}
	public LineColor getLine_color() {
		return line_color;
	}
	public void setLine_color(LineColor line_color) {
		this.line_color = line_color;
	}
	
}

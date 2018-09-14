package com.wt.seek.service.back;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.BannerDetail;

public interface IBannerDetailService {
	
	boolean saveBannerDetail(BannerDetail bannerDetail);
	
	boolean removeBannerDetail(Integer id);
	
	boolean updateBannerDetail(BannerDetail bannerDetail);
	
	BannerDetail getById(Integer id);
	
	BannerDetail getByBannerId(Integer bannerId);
	
	/**
	 * 这里用store纯粹是不想让spring进行事务管理
	 * @param img
	 */
	String[] storeDetailImgs(String absoluteStaticsPath,MultipartFile[] img);
}

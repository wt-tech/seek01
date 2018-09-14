package com.wt.seek.service.back;

import com.wt.seek.entity.BannerDetail;

public interface IBannerDetailService {
	
	boolean saveBannerDetail(BannerDetail bannerDetail);
	
	boolean removeBannerDetail(Integer id);
	
	boolean updateBannerDetail(BannerDetail bannerDetail);
	
	BannerDetail getById(Integer id);
	
	BannerDetail getByBannerId(Integer bannerId);
	
}

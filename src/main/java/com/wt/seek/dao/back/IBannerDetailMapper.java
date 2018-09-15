package com.wt.seek.dao.back;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.BannerDetail;

public interface IBannerDetailMapper {
	
	boolean saveBannerDetail(@Param("bannerDetail")BannerDetail bannerDetail);
	
	boolean removeBannerDetail(@Param("id")Integer id);
	
	boolean updateBannerDetail(@Param("bannerDetail")BannerDetail bannerDetail);
	
	BannerDetail getById(@Param("id")Integer id);
	
	BannerDetail getByBannerId(@Param("bannerId")Integer bannerId);
}

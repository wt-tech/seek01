package com.wt.seek.dao.back;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.Banner;

public interface IBannersMapper {

	List<Banner> listBanner();

	Integer updateBanner(Banner banner) throws Exception;

	Integer saveBanner(Banner banner) throws Exception;

	Integer removeBanner(@Param("id") int id) throws Exception;

	Banner getBanner(@Param("id") int id);
	
	Integer countBanner();
}

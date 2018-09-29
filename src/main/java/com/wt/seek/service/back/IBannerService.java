package com.wt.seek.service.back;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Banner;

public interface IBannerService {
	
	List<Banner> listBanner();

	boolean updateBanner(Banner banner,MultipartFile file, String staticsPath) throws Exception;

	boolean saveBanner(MultipartFile[] file,String staticsPath,String imgName) throws Exception;

	boolean removeBanner(int id) throws Exception;
	
	Banner getBanner(int id);
	
	Integer countBanner();
}

package com.wt.seek.servimpl.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.back.IBannerDetailMapper;
import com.wt.seek.entity.BannerDetail;
import com.wt.seek.service.back.IBannerDetailService;
import com.wt.seek.tool.BusinessUtils;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ImageUtils;

@Service
public class BannerDetailServImpl implements IBannerDetailService{

	@Autowired
	private IBannerDetailMapper detailMapper;
	
	
	@Override
	public boolean saveBannerDetail(BannerDetail bannerDetail) {
		if(null != getByBannerId(bannerDetail.getBannerId())) {
			BusinessUtils.throwNewBusinessException("该轮播图下已经有详情页,不可重复添加!");
		}
		return detailMapper.saveBannerDetail(bannerDetail);
	}

	@Override
	public boolean removeBannerDetail(Integer id) {
		return detailMapper.removeBannerDetail(id);
	}

	@Override
	public boolean updateBannerDetail(BannerDetail bannerDetail) {
		return detailMapper.updateBannerDetail(bannerDetail);
	}

	@Override
	public BannerDetail getById(Integer id) {
		return detailMapper.getById(id);
	}

	@Override
	public BannerDetail getByBannerId(Integer bannerId) {
		return detailMapper.getByBannerId(bannerId);
	}

	@Override
	public String[] storeDetailImgs(String absoluteStaticsPath,MultipartFile[] imgs) {
		if(imgs == null)
			return null;
		String[] urls = new String[imgs.length];
		int i = 0;
		for(MultipartFile img : imgs) {
			urls[i++] = storeDetailImg(absoluteStaticsPath,img);
		}
		return urls;
	}

	private String storeDetailImg(String absoluteStaticsPath,MultipartFile img) {
		String url = null;
		if(img!=null && !img.isEmpty()) {
			String suffix = ImageUtils.getImageTypeWithDot(img);
			Integer id = Integer.valueOf((System.currentTimeMillis()+"").substring(4));
			String absolutePath = ImageUtils.generateAbsoluteImgPath(absoluteStaticsPath, Constants.DETAIL_IMG, id, suffix);
			if(ImageUtils.saveImage(img, absolutePath))
				url = ImageUtils.genrateVirtualImgPath(Constants.DETAIL_IMG, id, suffix);
		}
		return url;
	}
}

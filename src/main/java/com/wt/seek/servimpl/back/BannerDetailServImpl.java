package com.wt.seek.servimpl.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.back.IBannerDetailMapper;
import com.wt.seek.entity.BannerDetail;
import com.wt.seek.service.back.IBannerDetailService;
import com.wt.seek.tool.BusinessUtils;

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

}

package com.wt.seek.servimpl.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.back.IBannersMapper;
import com.wt.seek.entity.Banner;
import com.wt.seek.exception.BusinessException;
import com.wt.seek.service.back.IBannerService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ImageUtils;

@Service()
public class BannerServiceImpl implements IBannerService {

	@Autowired
	private IBannersMapper bannerMapper;

	@Override
	public List<Banner> listBanner() {
		// TODO Auto-generated method stub
		return bannerMapper.listBanner();
	}

	@Override
	public boolean updateBanner(Banner banner, MultipartFile file, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (null != file && !file.isEmpty()) {
			// 获取文件名
			String suffix = ImageUtils.getImageTypeWithDot(file);
			// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
			String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.BANNER_IMG, banner.getId(), suffix);
			// 上传图片
			flag = ImageUtils.saveImage(file, absolutePath);
			// 生成网络访问的路径
			String url = ImageUtils.genrateVirtualImgPath(Constants.BANNER_IMG, banner.getId(), suffix);
			if (flag) {
				banner.setUrl(url);
				flag = bannerMapper.updateBanner(banner)>0;
			}
		} else {
			flag = bannerMapper.updateBanner(banner) > 0;
		}
		return flag;
	}

	@Override
	public boolean saveBanner(MultipartFile[] file, String staticsPath,String imgName) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (null != file && file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				MultipartFile attach = file[i];
				if (!attach.isEmpty()) {
					Banner banner = new Banner();
					banner.setImgName(imgName);
					banner.setOnUse(true);
					flag = bannerMapper.saveBanner(banner)>0;
					if (flag) {
						// 获取文件名
						String suffix = ImageUtils.getImageTypeWithDot(attach);
						// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
						String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.BANNER_IMG, banner.getId(),
								suffix);
						// 上传图片
						flag = ImageUtils.saveImage(attach, absolutePath);
						// 生成网络访问的路径
						String url = ImageUtils.genrateVirtualImgPath(Constants.BANNER_IMG, banner.getId(), suffix);
						banner.setUrl(url);
						flag = updateBanner(banner);
						if(!flag) {
							throw new BusinessException("轮播图上传失败");
						}
						
					}else {//保存失败
						throw new BusinessException("轮播图上传失败");
					}
				}
			}
		}
		return flag;
	}

	@Override
	public boolean removeBanner(int id) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = bannerMapper.removeBanner(id);
		if (num > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Banner getBanner(int id) {
		// TODO Auto-generated method stub
		return bannerMapper.getBanner(id);
	}

	@Override
	public Integer countBanner() {
		// TODO Auto-generated method stub
		return bannerMapper.countBanner();
	}

	@Override
	public boolean updateBanner(Banner banner) throws Exception {
		return bannerMapper.updateBanner(banner)>0;
	}

}

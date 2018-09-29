package com.wt.seek.servimpl.my;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.my.IAuthenticationMapper;
import com.wt.seek.entity.Authentication;
import com.wt.seek.service.my.IAuthenticationService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ImageUtils;

@Service()
public class AuthenticationServiceImpl implements IAuthenticationService {

	@Autowired
	private IAuthenticationMapper authenticationMapper;

	@Override
	public List<Authentication> listAuthentication(Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return authenticationMapper.listAuthentication(currentPageNo, pageSize);
	}

	@Override
	public Integer saveAuthentication(Authentication authentication) throws Exception {
		// TODO Auto-generated method stub
		// 保存除了图片之外的其它信息
		authenticationMapper.saveAuthentication(authentication);
		return authentication.getId();
	}

	@Override
	public Integer countAuthentication() {
		// TODO Auto-generated method stub
		return authenticationMapper.countAuthentication();
	}

	@Override
	public Authentication getAuthentication(int customerId) throws Exception {
		// TODO Auto-generated method stub
		return authenticationMapper.getAuthentication(customerId);
	}

	@Override
	public Authentication getBackAuthentication(int id) throws Exception {
		// TODO Auto-generated method stub
		return authenticationMapper.getBackAuthentication(id);
	}

	@Override
	public boolean updateAuthentication(Authentication authentication, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		MultipartFile file = null;
		if (null != negativIdentityUrl)
			file = negativIdentityUrl;
		if (null != positiveIdentityUrl)
			file = positiveIdentityUrl;
		// 把两张图片放到一个数组里
		// MultipartFile[] file = { negativIdentityUrl, positiveIdentityUrl };
		if (authentication.getId() > 0) { // 保存成功
			if (null != file) {
				int id=(int)new Date().getTime();
				// for (int i = 0; i < file.length; i++) {
				// 存储图片
				String suffix = ImageUtils.getImageTypeWithDot(file);
				// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
				String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.AUTHENTICATION,
						id, suffix);
				// 上传图片
				flag = ImageUtils.saveImage(file, absolutePath);
				String url = ImageUtils.genrateVirtualImgPath(Constants.AUTHENTICATION, id, suffix);
				if (flag) {// 图片存储成功
					if (null != negativIdentityUrl)
						authentication.setNegativeIdentityUrl(url);
					if (null != positiveIdentityUrl)
						authentication.setPositiveIdentityUrl(url);
					// 更新认证的图片
				}
			}
		}
		flag = authenticationMapper.updateAuthentication(authentication) > 0;
		return flag;
	}
}

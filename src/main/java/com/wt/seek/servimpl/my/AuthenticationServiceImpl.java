package com.wt.seek.servimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.my.IAuthenticationMapper;
import com.wt.seek.entity.Authentication;
import com.wt.seek.service.my.IAuthenticationService;
import com.wt.seek.tool.AuthenticationImage;
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
	public boolean saveAuthentication(Authentication authentication, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		// 保存除了图片之外的其它信息
		int authenticationId = authenticationMapper.saveAuthentication(authentication);
		boolean flag = AuthenticationImage.authenticationImage(authenticationId, authentication, negativIdentityUrl,
				positiveIdentityUrl, staticsPath, authenticationMapper);
		return flag;

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
	public boolean updateAuthentication(Authentication authentication, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = AuthenticationImage.authenticationImage(authentication.getId(), authentication, negativIdentityUrl,
				positiveIdentityUrl, staticsPath, authenticationMapper);
		return flag;
	}

}

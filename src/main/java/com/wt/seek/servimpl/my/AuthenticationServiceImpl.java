package com.wt.seek.servimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.my.IAuthenticationMapper;
import com.wt.seek.entity.Authentication;
import com.wt.seek.service.my.IAuthenticationService;

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
	public boolean saveAuthentication(Authentication Authentication) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = 0;
			num = authenticationMapper.saveAuthentication(Authentication);
		if (num > 0) {
			flag = true;
		}
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
	public Integer updateAuthentication(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

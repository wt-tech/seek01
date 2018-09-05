package com.wt.seek.servimpl.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.index.ICustomerMapper;
import com.wt.seek.entity.Customer;
import com.wt.seek.service.index.ICustomerServ;

@Service()
public class CustomerServImpl implements ICustomerServ{

	@Autowired()
	private ICustomerMapper customerMapper;
	
	
	@Override
	public Customer getCustomerByOpenId(String openId) {
		return customerMapper.getIdByOpenId(openId);
	}

	@Override
	public boolean saveCustomer(Customer customer) {
		return customerMapper.saveCustomer(customer);
	}


	@Override
	public Customer getCustomerById(Integer id) {
		return customerMapper.getCustomerById(id);
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		return customerMapper.updateCustomer(customer);
	}


}

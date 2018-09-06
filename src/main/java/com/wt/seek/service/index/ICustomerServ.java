package com.wt.seek.service.index;

import com.wt.seek.entity.Customer;

public interface ICustomerServ {

	Customer getCustomerByOpenId(String openId);
	
	boolean saveCustomer(Customer customer);
	
	Customer getCustomerById(Integer id);
	
	boolean updateCustomer(Customer customer);
}

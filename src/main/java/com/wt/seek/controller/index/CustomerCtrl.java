package com.wt.seek.controller.index;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wt.seek.entity.Authentication;
import com.wt.seek.entity.Customer;
import com.wt.seek.entity.VisitRecord;
import com.wt.seek.service.index.ICode2OpenIdServ;
import com.wt.seek.service.index.ICustomerServ;
import com.wt.seek.service.index.IVisitRecordServ;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.MapUtils;

@RestController()
public class CustomerCtrl {

	@Autowired()
	private ICustomerServ customerServImpl;
	
	@Autowired()
	private ICode2OpenIdServ code2OpenIdServImpl;
	
	@Autowired()
	private IVisitRecordServ visitRecordServImpl;
	
	@RequestMapping(value="authorization/{code}")
	public Map<String,Object> getCustomerId(@PathVariable("code") String code,HttpServletRequest request) throws Exception {
		
		Map<String,Object> map = MapUtils.getHashMapInstance();
		//1.获取openID
		String openId = code2OpenIdServImpl.getOpenID(code);
		if(null == openId) {//说明code不合法. 一般来说是模拟器...
			map.put("customerid", null);
		}
		//2根据openID查询是否存在该用户
		Customer user = customerServImpl.getCustomerByOpenId(openId);
		if(null == user) {//说明该用户是首次访问 插入...
			user = new Customer();
			user.setOpenid(openId);
			customerServImpl.saveCustomer(user);
		}
		//3保存访问记录
		VisitRecord record = new VisitRecord();
		record.setCustomer(user);
		visitRecordServImpl.saveVisitRecord(record);
		
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("customerId", user.getId());
		return map;
	}
	
	@RequestMapping(value="customer",method = RequestMethod.PUT)
	public Map<String,Object> updateCustomer(/*@ModelAttribute("customer") */@RequestParam Customer customer){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		/*if(customer == null || customer.getId() < 0) {
			map.put(Constants.SYS_MESSAGE, "未传入用户id");
			return map;
		}*/
		if(customerServImpl.updateCustomer(customer)) {
			map.put(Constants.STATUS, Constants.SUCCESS);
		}
		return map;
	}

//	@ModelAttribute
//	public void prepareCustomer(@RequestParam(value="id",required=false)
//			Integer id,Map<String,Object> map){
//		if(null != id) {
//			Customer customer = customerServImpl.getCustomerById(id);
//			map.put("customer",customer);
//		}
//	}
	
	@RequestMapping(value = "authentication",method = RequestMethod.POST)
	public Map<String,Object> saveVisitRecord(Authentication authentication){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		System.out.println(authentication);
		return map;
	}
	
	@RequestMapping(value = "visitrecord",method = RequestMethod.POST)
	public Map<String,Object> saveVisitRecord(@RequestBody VisitRecord record){
		Map<String,Object> map = MapUtils.getHashMapInstance();
		map.put(Constants.STATUS, Constants.FAIL);
		if(visitRecordServImpl.saveVisitRecord(record))
			map.put(Constants.STATUS, Constants.SUCCESS);
		return map;
	}
	
	@RequestMapping(value = "getcustomerbyid",method = RequestMethod.GET)
	public Customer getCustomerById(@RequestParam("id") Integer id){
		return customerServImpl.getCustomerById(id);
	}
}


package com.wt.seek.dao.index;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wt.seek.entity.Customer;

public interface ICustomerMapper {

	
	@Select("SELECT * FROM customer WHERE openid= #{openId}")
	Customer getIdByOpenId(@Param("openId")String openId);
	
	@Insert("INSERT INTO customer ( openid,first_vist_time )" + 
			"VALUES" + 
			"	(" + 
			"	#{customer.openid}," + 
			"	NOW()" + 
			"	);" + 
			"")
	@Options(useGeneratedKeys = true,keyProperty = "customer.id")
	boolean saveCustomer(@Param("customer") Customer customer);
	
	@Update("UPDATE customer " + 
			"SET " + 
			"	nickname = #{customer.nickname}," + 
			"	gender = #{customer.gender}," + 
			"	avatarurl = #{customer.avatarurl}," + 
			"	first_vist_time = #{customer.firstVisitTime}," + 
			"	realname = #{customer.realname}," + 
			"	tel = #{customer.tel} " + 
			"WHERE" + 
			"	id = #{customer.id}")
	boolean updateCustomer(@Param("customer") Customer customer);
	
	@Select("SELECT * FROM customer WHERE id = #{id};")
	Customer getCustomerById(@Param("id") Integer id);
}

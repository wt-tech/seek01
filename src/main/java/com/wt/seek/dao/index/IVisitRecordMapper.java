package com.wt.seek.dao.index;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.wt.seek.entity.VisitRecord;

public interface IVisitRecordMapper {
	
	@Insert("insert into visit_record(customer_id,visit_time) values(#{record.customer.id},NOW());")
	boolean saveVisitRecord(@Param("record") VisitRecord record);
}

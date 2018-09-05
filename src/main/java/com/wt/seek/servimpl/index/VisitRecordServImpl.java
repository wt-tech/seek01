package com.wt.seek.servimpl.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.index.IVisitRecordMapper;
import com.wt.seek.entity.VisitRecord;
import com.wt.seek.service.index.IVisitRecordServ;

@Service()
public class VisitRecordServImpl implements IVisitRecordServ{
	
	@Autowired() private IVisitRecordMapper visitRecordMapper;
	
	@Override
	public boolean saveVisitRecord(VisitRecord record) {
		return visitRecordMapper.saveVisitRecord(record);
	}

}

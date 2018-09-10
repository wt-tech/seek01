package com.wt.seek.servimpl.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.index.IMarkMapper;
import com.wt.seek.entity.Mark;
import com.wt.seek.service.index.IMarkService;

@Service()
public class MarkServiceImpl implements IMarkService {

	@Autowired
	private IMarkMapper markMapper;

	@Override
	public List<Mark> listMark(Integer customerId, Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return markMapper.listMark(customerId, currentPageNo, pageSize);
	}

	@Override
	public boolean saveMark(Mark mark) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = markMapper.saveMark(mark);
		if (num > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteMark(int customerId,int seekId) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = markMapper.deleteMark(customerId,seekId);
		if (num > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Integer countMark() {
		// TODO Auto-generated method stub
		return markMapper.countMark();
	}

	@Override
	public Mark getMark(int customerId, int seekId) throws Exception {
		// TODO Auto-generated method stub
		return markMapper.getMark(customerId, seekId);
	}

}

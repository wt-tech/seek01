package com.wt.seek.servimpl.my;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wt.seek.dao.my.IBrowseHistoryMapper;
import com.wt.seek.entity.BrowseHistory;
import com.wt.seek.service.my.IBrowseHistoryService;

@Service()
public class BrowseHistoryServiceImpl implements IBrowseHistoryService {

	@Autowired
	private IBrowseHistoryMapper browsehistoryMapper;

	@Override
	public List<BrowseHistory> listBrowseHistory(Integer customerId, Integer currentPageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return browsehistoryMapper.listBrowseHistory(customerId, currentPageNo, pageSize);
	}

	@Override
	public boolean saveBrowseHistory(BrowseHistory browseHistory) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = 0;
		// 通过传递过来的id查询是否有浏览记录
		int browsehistoryId = browsehistoryMapper.getBrowseHistory(browseHistory.getSeek().getId());
		if (browsehistoryId > 0) {
			// 如果id大于0也就是有记录，直接修改最后一次浏览时间
			num = browsehistoryMapper.updateBrowseHistory(browsehistoryId);
		} else {
			// 否则添加一条浏览记录
			num = browsehistoryMapper.saveBrowseHistory(browseHistory);
		}
		if (num > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteBrowseHistory(int id) throws Exception {
		// TODO Auto-generated method stub
		boolean flag = false;
		int num = browsehistoryMapper.deleteBrowseHistory(id);
		if (num > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Integer countBrowseHistory() {
		// TODO Auto-generated method stub
		return browsehistoryMapper.countBrowseHistory();
	}

}

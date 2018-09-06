package com.wt.seek.tool;

public class PageUtil {
	public Integer Page(Integer totalCount, Integer currentPageNo) throws Exception {
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 总数量（表）
		// 总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// 控制首页和尾页
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {

			currentPageNo = totalPageCount+1;

		}
		return currentPageNo;
	}

}

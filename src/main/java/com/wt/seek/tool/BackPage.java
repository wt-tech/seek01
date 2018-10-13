package com.wt.seek.tool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wt.seek.entity.Seek;

public class BackPage {
	/**
	 * 对查询出来的志愿者区域进行合并成一个集合
	 * 
	 * @param listSeek
	 * @param currentPageNo
	 * @param totalcount
	 * @param pagesize
	 * @return
	 */
	public static List<Seek> MergeSeek(List<List<Seek>> listSeek) {
		List<Seek> result = new ArrayList<Seek>();
		for (int i = 0; i < listSeek.size(); i++) {
			result.addAll(listSeek.get(i));
		}
		return result;
	}

	/**
	 * 实现分页功能
	 * 
	 * @param result
	 * @param currentPageNo
	 * @param totalcount
	 * @param pagesize
	 * @return
	 */
	public static Map<String, Object> page(List<Seek> result, Integer currentPageNo, Integer totalcount,
			Integer pagesize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Seek> listSeeks = null;
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (m == 0) {
			if (totalcount < pagesize) {
				listSeeks = result.subList((currentPageNo - 1) * pagesize, totalcount);
			} else {
				listSeeks = result.subList((currentPageNo - 1) * pagesize, pagesize * (currentPageNo));
			}
			map.put("seeks", listSeeks);
			return map;
		} else {
			if (currentPageNo == pagecount) {
				listSeeks = result.subList((currentPageNo - 1) * pagesize, totalcount);
				map.put("seeks", listSeeks);
				return map;
			} else {
				listSeeks = result.subList((currentPageNo - 1) * pagesize, pagesize * (currentPageNo));
				map.put("seeks", listSeeks);
				return map;
			}
		}
	}

	public static Map<String, Object> commonPage(List<Object> result, Integer currentPageNo, Integer totalcount,
			Integer pagesize, String key) {
		Map<String, Object> map = new HashMap<String, Object>();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (m == 0) {
			List<Object> subList = result.subList((currentPageNo - 1) * pagesize, pagesize * (currentPageNo));
			map.put(key, subList);
			return map;
		} else {
			if (currentPageNo == pagecount) {
				List<Object> subList = result.subList((currentPageNo - 1) * pagesize, totalcount);
				map.put(key, subList);
				return map;
			} else {
				List<Object> subList = result.subList((currentPageNo - 1) * pagesize, pagesize * (currentPageNo));
				map.put(key, subList);
				return map;
			}
		}
	}
}

package com.wt.seek.controller.my;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.entity.Authentication;
import com.wt.seek.service.my.IAuthenticationService;
import com.wt.seek.tool.Constants;
import com.wt.seek.tool.ContextUtil;
import com.wt.seek.tool.MapUtils;
import com.wt.seek.tool.PageUtil;

@RestController("")
@RequestMapping("/authentication")
public class AuthenticationCtrl {
	@Autowired
	private IAuthenticationService authenticationService;

	private Logger logger = LogManager.getLogger();

	@RequestMapping("/listauthentication")
	public Map<String, Object> listAuthentication(@RequestParam("currentPageNo") Integer currentPageNo)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		// 总数量（表）
		int totalCount = authenticationService.countAuthentication();
		Integer currentPageNos = new PageUtil().Page(totalCount, currentPageNo);
		List<Authentication> authentications = authenticationService.listAuthentication(currentPageNos,
				Constants.pageSize);
		map.put(Constants.STATUS, Constants.SUCCESS);
		map.put("Authentications", authentications);
		map.put("totalCount", totalCount);
		map.put("pageSize", Constants.pageSize);
		return map;
	}

	/**
	 * 插入认证记录
	 * 
	 * @param seek
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAuthentication")
	public Map<String, Object> saveAuthentication(HttpServletRequest request,
			@RequestBody() Authentication authentication,
			@RequestParam(value = "negativIdentityUrl", required = false) MultipartFile negativIdentityUrl,
			@RequestParam(value = "positiveIdentityUrl", required = false) MultipartFile positiveIdentityUrl)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = authenticationService.saveAuthentication(authentication, negativIdentityUrl, positiveIdentityUrl,
				staticsPath);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}

	/**
	 * 查询每个用户的认证记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAuthentication")
	public Authentication getAuthentication(@RequestParam("customerId") Integer customerId) throws Exception {
		return authenticationService.getAuthentication(customerId);

	}

	/**
	 * 修改用户的认证
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateAuthentication")
	public Map<String, Object> updateAuthentication(HttpServletRequest request,
			@RequestBody() Authentication authentication,
			@RequestParam(value = "negativIdentityUrl", required = false) MultipartFile negativIdentityUrl,
			@RequestParam(value = "positiveIdentityUrl", required = false) MultipartFile positiveIdentityUrl)
			throws Exception {
		Map<String, Object> map = MapUtils.getHashMapInstance();
		String staticsPath = ContextUtil.getStaticResourceAbsolutePath(request);
		boolean flag = authenticationService.updateAuthentication(authentication, negativIdentityUrl, positiveIdentityUrl,
				staticsPath);
		map.put(Constants.STATUS, flag ? Constants.SUCCESS : Constants.FAIL);
		return map;
	}

}

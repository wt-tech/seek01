package com.wt.seek.tool;

import org.springframework.web.multipart.MultipartFile;

import com.wt.seek.dao.my.IAuthenticationMapper;
import com.wt.seek.entity.Authentication;

public class AuthenticationImage {
	public static boolean authenticationImage(int id, Authentication authentication, MultipartFile negativIdentityUrl,
			MultipartFile positiveIdentityUrl, String staticsPath, IAuthenticationMapper authenticationMapper)
			throws Exception {
		boolean flag = false;
		// 把两张图片放到一个数组里
		MultipartFile[] file = { negativIdentityUrl, positiveIdentityUrl };
		if (id > 0) { // 保存成功
			if (null != file && file.length > 0) {
				for (int i = 0; i < file.length; i++) {
					// 存储图片
					String suffix = ImageUtils.getImageTypeWithDot(file[i]);
					// 根据传递的公共路径（前半部分）+表名+id+文件名生成存储路径
					String absolutePath = ImageUtils.generateAbsoluteImgPath(staticsPath, Constants.AUTHENTICATION, id,
							suffix);
					// 上传图片
					flag = flag && ImageUtils.saveImage(file[i], absolutePath);
					String url = ImageUtils.genrateVirtualImgPath(Constants.AUTHENTICATION, id, suffix);
					if (flag) {// 图片存储成功
						authentication.setId(id);
						// 如果file的值为negativIdentityUrl，则把url赋值给negativIdentity
						if (file[i] == negativIdentityUrl) {
							authentication.setNegativIdentityUrl(url);
						} else {
							authentication.setPositiveIdentityUrl(url);
						}
						// 更新认证的图片
						flag = flag && authenticationMapper.updateAuthentication(authentication) > 0;
					}
					if (flag)
						continue;
					else
						return flag;
				}
			}
		}

		return flag;
	}
}

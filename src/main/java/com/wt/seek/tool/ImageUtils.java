package com.wt.seek.tool;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {

	public static String getImageTypeWithDot(MultipartFile image) {
		if (null == image || image.isEmpty())
			return null;
		String originImgName = image.getOriginalFilename();
		if (!originImgName.contains(Constants.DOT))
			return null;
		return originImgName.substring(originImgName.lastIndexOf(Constants.DOT));
	}

	/**
	 * 生成图片的绝对路径<br/>
	 * eg: E:/img/start.jpeg<br/>
	 * 该方法的返回值传递给saveImage<br/>
	 * 作为其第二个参数.
	 * 生成格式:ContextUtil.getVirtualHostRealPath()+Constants.ContextPath<br/>
	 * +表名+记录主键+ImageUtils.getImageTypeWithDot()
	 * 
	 * @return
	 */
	public static String generateAbsoluteImgPath(String realStaticsPath, String tableName, int id, String suffix) {
		String absolutePath = realStaticsPath + Constants.ContextPath + File.separator + tableName + File.separator + id
				+ suffix;
		return absolutePath;
	}

	/**
	 * 生成图片的网络访问路径<br/>
	 * eg:<br/>
	 * https://www.qghls.com/statics/market/commodity/pants-1.jpg
	 */
	public static String genrateVirtualImgPath(String tableName, int id, String suffix) {
		String virtualPath = Constants.imgServerDomain + Constants.ContextPath + "/" + tableName + "/" + id + suffix;
		return virtualPath;
	}

	public static boolean saveImage(MultipartFile image, String absoluteRealPath) {

		File file = new File(absoluteRealPath);
		if (file.isDirectory())
			return false;
		try {
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			image.transferTo(file);

		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

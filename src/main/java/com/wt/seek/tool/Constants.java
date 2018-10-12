package com.wt.seek.tool;

public class Constants {
	
	//session相关
	public final static String USER_SESSION = "userSession";
	public final static String USER_PERMISSIONS = "userPermissions";
	public final static String USER_MENUS = "userMenus";
	
	//状态相关
	public final static String SYS_MESSAGE = "message";
	public final static String SUCCESS = "success";
	public final static String STATUS = "status";
	public final static String FAIL = "fail";
	public final static int HTTP_STATUS_302 = 302;
	
	
	//异常同一处理相关
	public final static String EXCEPTION_CLASS = "exception";
	public final static String ERRORS = "errors";
	public final static String TIPS = "tips";
	public final static String DATA_ACCESS_DESCRIPTION = "数据处理异常";
	public final static String EXCEPTION_DESCRIPTION = "未知错误";
	public final static String BUSINESS_DESCRIPTION = "业务逻辑错误";
	public final static String ARGUMENT_ILLEGAL = "参数不合法";
	
	//权限相关
	public final static String PERMISSIONS_TO_ADD = "等待添加至权限表";
	public final static String VOLUNTEER_APPROVED = "审核通过";
	
	
	//申请的腾讯地图的KEY
	public final static String KEY1 = "D6SBZ-XVRCQ-46E5A-GWHL4-4V7P2-A5FOS";
	public final static String KEY2 = "ABRBZ-6M4LO-UP3WX-SIMTN-OTAK5-O4FVL";
	public final static String KEY3 = "76MBZ-N4HL3-ZOX3X-YEWOR-YKTOQ-HNFIP";
	public final static String KEY4 = "2WVBZ-ZF2HQ-Z6X5L-GFCRI-JI4P7-THFTW";
	public final static String KEY5 = "J6VBZ-DBCRD-7YV4R-HQ7H5-ZEYN5-MJFSS";
	//腾讯地图提供的逆地址解析协议接口
	public final static String BASERarpURL = "http://apis.map.qq.com/ws/geocoder/v1/";
	public final static int KEYNUMBERS = 5;
	
	//小程序APPID
	private final static String APPID = "wx28e3c37b96cf1939";
	//小程序SECRET
	private final static String SECRET = "f5d09d752f39521508ee34cec8d2abb8";
	
	//小程序官方提供的code转openID接口
	public final static String CODE2OPENIDURL = "https://api.weixin.qq.com/sns/jscode2session"
			+ "?appid="+APPID
			+ "&secret="+SECRET
			+ "&grant_type=authorization_code"
			+ "&js_code=";//参数js_code每次从前台传入
	
	public final static int pageSize = 3;
	public final static int pageSizes = 6;
	public final static int pageSizess = 12;
	public static final String DOT = ".";
	
	//小程序官方提供的 获取小程序Access_token
	public final static  String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token"
			+ "?grant_type=client_credential"
			+ "&appid="+APPID
			+ "&secret="+SECRET;
	
	//小程序官方提供的 获取小程序码URL
	public static String MINI_PROGRAM_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit"
			+ "?access_token="; 
	
	
	//服务端存放相应表中图片的文件夹名称
	public static final String SEEK_IMG = "seek-img";
	public static final String BANNER_IMG = "banner-img";

	public static final String AUTHENTICATION = "authentication-img";
	
	public static final String VOLUNTEER = "volunteer-img";
	public static final String DETAIL_IMG = "banner-detail-img";
	
	//存放小程序码的路径
	public static final String MINIQRCODE = "mini-program-code";
	public static final String MINIQRCODESUFFIX = ".jpeg";

	
	//DOCBASEPATH指Tomcat的server.xml配置文件中host下的context的docBase属性
	public static final String IMGBASEPATH = "statics";
	//ContextPath指当前Web应用的名称
	public static final String ContextPath = "seek";
	
	//等部署到服务器上后,再修改
	//public static final String imgServerDomain = "https://www.qghls.com/statics/";
	public static final String imgServerDomain = "http://192.168.0.109:8080/statics/";
    //public static final String imgServerDomain = "http://192.168.0.177:8888/statics/";

	public static final double REPEATPUBLISHTHRESHOLD = 80; 
	public static final String NOT_SOLVED = "'未结案'";
	public static final String HAS_SOLVED = "'已结案'";
	
	
	//相互匹配阈值
	public static final double MATCHS = 40; 
	
	public static final int DAYS_30 = 30;
	
	public static final int BELOW_ZERO_3 = -3;
	public static final double ONE_THIRD = 1.0/3;
	
	
}

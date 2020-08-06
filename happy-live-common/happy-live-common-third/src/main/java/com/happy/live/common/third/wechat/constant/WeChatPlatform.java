package com.happy.live.common.third.wechat.constant;


/**
 * 类:WeChatPlatform.java <tb>
 * 描述：微信平台  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2017年12月19日 下午6:45:51 <tb>
 */
public interface WeChatPlatform {
	
	/** js网页授权获取code跳转链接地址  **/
	public static final String USER_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=123#wechat_redirect";
	
	/** oAuth2机制 获取acccessToken  **/
	public static final String OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/** oAuth2机制 刷新accessToken	 **/
	public static final String OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	/**oAuth2机制 拉取用户信息(需scope为 snsapi_userinfo) **/
	public static final String OAUTH2_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**  检验授权凭证（access_token）是否有效   **/
	public static final String OAUTH2_AUTH_TOKEN = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	
	/**  获取AccessToken **/
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/** 获取用户基本信息（包括UnionID机制） **/
	public static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/** 获取用户列表  **/
	public static final String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	/**  设置用户备注名   **/
	public static final String UPDATE_REMARK_URL = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	
	/** 移动用户分组    **/
	public static final String UPDATE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	
	/**  批量移动用户分组  **/
	public static final String UPDATE_BATCH_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
	
	/**  批量移动用户分组 **/
	public static final String DELETE_GROUPS_URL = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
		
	/** 客服發送消息   **/
	public static final String CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	/** 发送模板消息   **/
	public static final String TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

	/** 发送小程序模板消息   **/
	public static final String TEMPLATE_SEND_MINI = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";

	/** 发送小程序订阅消息   **/
	public static final String SUBSCRIBE_SEND_MINI = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=ACCESS_TOKEN";


	/** 用于生成二维码的 ticket **/
	public static final String CREATE_QRCODE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
	
	/**  通过 ticket 生成二维码   **/
	public static final String SHOW_QRCODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
	
	/** jsapi_ticket 公众号用于调用微信JS接口的临时票据   **/
	public static final String GETTICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    /** jsapi_ticket 公众号用于调用微信JS接口的临时票据   **/
    public static final String GETMINI_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

	/**  菜单中的点击按钮   **/
	public static final String MENU_TYPE_CLICK = "1";
	
	/**  菜单中的跳转url按钮  **/
	public static final String MENU_TYPE_VIEW = "2";
	
}

package com.happy.live.common.third.wechat;

/**
 * 类: Configure <br>  
 * 描述: 公众号基础配置信息 <br>
 * 作者: cuixinfu@51play.com <br>
 */
public class Configure {
	/** 网站应用appid   **/
    private static String appid = "wxe4176c71d926f223";
    /**  网站应用 AppSecret    **/
    private static String appSecret = "0939f755c164c9bc127eb9ebdec37aba";
    /** 服务号appid   **/
    private static String fwAppID = "";
    /**  服务号AppSecret    **/
    private static String fwAppSecret = "";

	/** 测试号APPID  csAppId  **/
	private static String dyAppID = "";
	/** 测试号appsecret   csAppSecret **/
	private static String dyAppSecret = "";
	
	/** webapp应用程序AppID **/
	private static String webAppID = "";
	/** webapp应用程序AppSecret **/
	private static String webAppSecret = "e";

    /** 小程序AppID **/
    private static String miniAppID = "";
    /** 小程序AppSecret **/
    private static String miniAppSecret = "";

	/** 微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到） **/
	private static String mchID = "";
	/**  商户号 ：API密钥   **/
	private static String mchApiKey = "";

	/** 微信授权回调URL **/
	private static String redirectUrl = "";

	public static String getAppid() {
		return appid;
	}

	public static void setAppid(String appid) {
		Configure.appid = appid;
	}

	public static String getAppSecret() {
		return appSecret;
	}

	public static void setAppSecret(String appSecret) {
		Configure.appSecret = appSecret;
	}

	public static String getFwAppID() {
		return fwAppID;
	}

	public static void setFwAppID(String fwAppID) {
		Configure.fwAppID = fwAppID;
	}

	public static String getFwAppSecret() {
		return fwAppSecret;
	}

	public static void setFwAppSecret(String fwAppSecret) {
		Configure.fwAppSecret = fwAppSecret;
	}

	public static String getDyAppID() {
		return dyAppID;
	}

	public static void setDyAppID(String dyAppID) {
		Configure.dyAppID = dyAppID;
	}

	public static String getDyAppSecret() {
		return dyAppSecret;
	}

	public static void setDyAppSecret(String dyAppSecret) {
		Configure.dyAppSecret = dyAppSecret;
	}

	public static String getWebAppID() {
		return webAppID;
	}

	public static void setWebAppID(String webAppID) {
		Configure.webAppID = webAppID;
	}

	public static String getWebAppSecret() {
		return webAppSecret;
	}

	public static void setWebAppSecret(String webAppSecret) {
		Configure.webAppSecret = webAppSecret;
	}

	public static String getMiniAppID() {
		return miniAppID;
	}

	public static void setMiniAppID(String miniAppID) {
		Configure.miniAppID = miniAppID;
	}

	public static String getMiniAppSecret() {
		return miniAppSecret;
	}

	public static void setMiniAppSecret(String miniAppSecret) {
		Configure.miniAppSecret = miniAppSecret;
	}

	public static String getMchID() {
		return mchID;
	}

	public static void setMchID(String mchID) {
		Configure.mchID = mchID;
	}

	public static String getMchApiKey() {
		return mchApiKey;
	}

	public static void setMchApiKey(String mchApiKey) {
		Configure.mchApiKey = mchApiKey;
	}

	public static String getRedirectUrl() {
		return redirectUrl;
	}

	public static void setRedirectUrl(String redirectUrl) {
		Configure.redirectUrl = redirectUrl;
	}

}

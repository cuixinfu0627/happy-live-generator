package com.happy.live.common.third.wechat;

import com.happy.live.common.third.wechat.bean.OAuthInfo;
import com.happy.live.common.third.wechat.constant.WeChatPlatform;
import com.happy.live.common.third.HttpHandler;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 类: WeixinHandler <br>  
 * 描述: 微信工具类 <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年11月28日 上午11:51:20 <br>
 */
public class WeixinHandler {

	/** 日志类处理类  **/
	private static Logger logger = LoggerFactory.getLogger(WeixinHandler.class);

	/** 全局缓存 订阅号 accesstoken有效期7200秒  **/
	public static String dyAccesstoken = null;

	/** 全局缓存 服务号 accesstoken有效期7200秒  **/
	public static String fwAccesstoken = null;

	/** 全局缓存 小程序 accesstoken有效期7200秒  **/
	public static String miniAccesstoken = null;

	/** 与接口配置信息中的Token要一致  **/
	private static String token = "aidou";

	/**全局缓存jsapi_ticket 有效期7200秒 ，jsapi_ticket是公众号用于调用微信JS接口的临时票据
	 * 由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，
	 * 开发者必须在自己的服务全局缓存jsapi_ticket **/
	public static String jsapiTicket = null;

	/**网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同 **/
	public static String oauth2Token = null;

	/**用户刷新access_token 由于access_token拥有较短的有效期，当access_token超时后，可以使用refresh_token进行刷新，
	 refresh_token拥有较长的有效期（7天、30天、60天、90天），当refresh_token失效的后，需要用户重新授权。**/
	public static String refreshToken = null;

	/** dyAccesstoken超时时间  **/
	public static Long lastGetDyAccessTokenTime = null;

	/** fwAccesstoken超时时间  **/
	public static Long lastGetFwAccessTokenTime = null;

	/** miniAccesstoken超时时间  **/
	public static Long lastGetMiniAccessTokenTime = null;

	/**	 JsapiTicket超时时间    **/
	public static Long lastGetJsapiTicketTime = null;

	/** oauth2Token超时时间  **/
	public static Long lastGetOauth2TokenTime = null;

	/** 全局缓存 测试1 accesstoken有效期7200秒  **/
	public static String oneAccesstoken = null;

	/**  oneAccesstoken超时时间   **/
	public static Long lastGetOneAccessTokenTime = null;

	/** ===========================================获取AccessToken==================================================== **/
	/**
	 * 此方法描述的是：订阅号获取access_token（用户获取用户基本信息的accesstoken 与授权的token不一样）
	 * @return String
	 */
	public static String getDyAccessToken() {
		logger.info("one___:lastGetDyAccessTokenTime:"+lastGetDyAccessTokenTime+"=dyAccesstoken:"+dyAccesstoken);
		if(StringUtils.isBlank(dyAccesstoken) || lastGetDyAccessTokenTime == null || (System.currentTimeMillis()/1000)-(lastGetDyAccessTokenTime.longValue()/1000) >= 5000){
			lastGetDyAccessTokenTime = new Long(System.currentTimeMillis());
			String url = WeChatPlatform.ACCESS_TOKEN_URL.replace("APPID", Configure.getDyAppID()).replace("APPSECRET", Configure.getDyAppSecret());
			JSONObject jsonObject = HttpHandler.doGetStr(url);
			logger.info("get wechat dyAccesstoken result :" + jsonObject);
			if(jsonObject != null){
				dyAccesstoken  = (String)jsonObject.get("access_token");
				if(dyAccesstoken == null){
					logger.error("get wechat dyAccesstoken error :" + jsonObject);
				}
			}
			logger.info("two___:lastGetDyAccessTokenTime:"+lastGetDyAccessTokenTime+"=dyAccesstoken:"+dyAccesstoken);
			return dyAccesstoken;

		}else{
			logger.info("three___:lastGetDyAccessTokenTime:"+lastGetDyAccessTokenTime+"=dyAccesstoken:"+dyAccesstoken);
			return dyAccesstoken;
		}
	}

	/**
	 * 此方法描述的是：   服务号accessToken
	 * @return
	 * String
	 */
	public static String getFwAccessToken() {
		if(StringUtils.isBlank(fwAccesstoken) || lastGetFwAccessTokenTime == null || (System.currentTimeMillis()/1000)-(lastGetFwAccessTokenTime.longValue()/1000) >= 7000){
			lastGetFwAccessTokenTime = new Long(System.currentTimeMillis());
			String url = WeChatPlatform.ACCESS_TOKEN_URL.replace("APPID", Configure.getFwAppID()).replace("APPSECRET", Configure.getFwAppSecret());
			JSONObject jsonObject = HttpHandler.doGetStr(url);
			logger.info("get wechat fwAccesstoken result :" + jsonObject);
			if(jsonObject != null){
				fwAccesstoken  = (String)jsonObject.get("access_token");
				if(fwAccesstoken == null){
					logger.error("get wechat fwAccesstoken error :" + jsonObject);
				}
			}
			return fwAccesstoken;
		}else{
			return fwAccesstoken;
		}
	}

	/**
	 * 此方法描述的是：   小程序accessToken
	 * @return
	 * String
	 */
	public static String getMiniAccessToken() {
		if(StringUtils.isBlank(miniAccesstoken) || lastGetMiniAccessTokenTime == null || (System.currentTimeMillis()/1000)-(lastGetMiniAccessTokenTime.longValue()/1000) >= 7000){
			lastGetMiniAccessTokenTime = new Long(System.currentTimeMillis());
			String url = WeChatPlatform.ACCESS_TOKEN_URL.replace("APPID", Configure.getMiniAppID()).replace("APPSECRET", Configure.getMiniAppSecret());
			JSONObject jsonObject = HttpHandler.doGetStr(url);
			logger.info("get wechat miniAccesstoken result :" + jsonObject);
			if(jsonObject != null){
				miniAccesstoken  = (String)jsonObject.get("access_token");
				if(miniAccesstoken == null){
					logger.error("get wechat miniAccesstoken error :" + jsonObject);
				}
			}
			return miniAccesstoken;
		}else{
			return miniAccesstoken;
		}
	}

	/** ===========================================授权==================================================== **/
	/**
	 * 此方法描述的是：网页授权  默认授权 获取accesstoken
	 * @param code
	 * @return Map
	 *
	 */
	public static Map getSnsapiBaseOpenid(String code) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String openid = null;
		lastGetOauth2TokenTime = new Long(System.currentTimeMillis());
		String url = WeChatPlatform.OAUTH2_ACCESS_TOKEN.replace("APPID", Configure.getFwAppID()).replace("SECRET", Configure.getFwAppSecret()).replace("CODE", code);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		logger.info("getSnsapiBaseOpenid_jsonObject:"+jsonObject);
		if(jsonObject != null && jsonObject.get("errcode")==null){
			oauth2Token  = (String)jsonObject.get("access_token");
			openid  = (String)jsonObject.get("openid");			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
			String scope  = (String)jsonObject.get("scope");			//用户授权的作用域，使用逗号（,）分隔
			refreshToken  = (String)jsonObject.get("refresh_token");   //刷新accesstoken用的
			Integer expiresIn  = (Integer)jsonObject.get("expires_in");  // access_token接口调用凭证超时时间，单位（秒）
			resultMap.put("openid", openid);
			resultMap.put("scope",scope);
			resultMap.put("oauth2Token",oauth2Token);
		}else{
			return null;
		}
		return resultMap;
	}

	/**
	 * 此方法描述的是：  获取用户openid
	 * @param code
	 * @return String
	 */
	public static String getUserOpenid(String code) {
		Map<String, String> resultMap = new HashMap<>();
		String openid = null;
		lastGetOauth2TokenTime = new Long(System.currentTimeMillis());
		String url = WeChatPlatform.OAUTH2_ACCESS_TOKEN.replace("APPID", Configure.getFwAppID()).replace("SECRET", Configure.getFwAppSecret()).replace("CODE", code);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		if(jsonObject != null){
			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
			openid  = (String)jsonObject.get("openid");
		}
		return openid;
	}

	/**
	 * @描述: 网站应用获取用户信息
	 * @创建人: cuixinfu@51play.com
	 * @创建时间: 2018/11/15 17:58
	 */
	public static Map getOAuth2AccessToken(String code) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String openid = null;
		lastGetOauth2TokenTime = new Long(System.currentTimeMillis());
		String url = WeChatPlatform.OAUTH2_ACCESS_TOKEN.replace("APPID", Configure.getAppid()).replace("SECRET", Configure.getAppSecret()).replace("CODE", code);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		if(jsonObject != null){
			oauth2Token  = (String)jsonObject.get("access_token");
			openid  = (String)jsonObject.get("openid");			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
			String scope  = (String)jsonObject.get("scope");			//用户授权的作用域，使用逗号（,）分隔
			refreshToken  = (String)jsonObject.get("refresh_token");   //刷新accesstoken用的
			Integer expiresIn  = (Integer)jsonObject.get("expires_in");  // access_token接口调用凭证超时时间，单位（秒）
//			boolean checkToken = checkOAuth2AccessToken(oauth2Token, openid);
//			if(!checkToken){	//token无效 刷新token
//				refreshAccessToken(refreshToken);
//			}
			resultMap.put("openid", openid);
			resultMap.put("scope",scope);
			resultMap.put("oauth2Token",oauth2Token);
		}
		Map auth2UserInfo = getOAuth2UserInfo(openid);
		logger.info("oauth userinfo:"+auth2UserInfo);
		return auth2UserInfo;
	}

	/**
	 * @描述: 服务号获取用户信息
	 * @创建人: cuixinfu@51play.com
	 * @创建时间: 2018/11/15 17:58
	 */
	public static Map getFWOAuth2AccessToken(String code) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String openid = null;
		lastGetOauth2TokenTime = new Long(System.currentTimeMillis());
		String url = WeChatPlatform.OAUTH2_ACCESS_TOKEN.replace("APPID", Configure.getFwAppID()).replace("SECRET", Configure.getFwAppSecret()).replace("CODE", code);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		if(jsonObject != null){
			oauth2Token  = (String)jsonObject.get("access_token");
			openid  = (String)jsonObject.get("openid");			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
			String scope  = (String)jsonObject.get("scope");			//用户授权的作用域，使用逗号（,）分隔
			refreshToken  = (String)jsonObject.get("refresh_token");   //刷新accesstoken用的
			Integer expiresIn  = (Integer)jsonObject.get("expires_in");  // access_token接口调用凭证超时时间，单位（秒）
//			boolean checkToken = checkOAuth2AccessToken(oauth2Token, openid);
//			if(!checkToken){	//token无效 刷新token
//				refreshAccessToken(refreshToken);
//			}
			resultMap.put("openid", openid);
			resultMap.put("scope",scope);
			resultMap.put("oauth2Token",oauth2Token);
		}
		Map auth2UserInfo = getOAuth2UserInfo(openid);
		logger.info("oauth userinfo:"+auth2UserInfo);
		return auth2UserInfo;
	}

	/**
	 * @描述: 移动app获取用户信息
	 * @创建人: cuixinfu@51play.com
	 * @创建时间: 2018/11/15 17:58
	 */
	public static Map getWebOAuth2AccessToken(String code) {
		Map<String, String> resultMap = new HashMap<String, String>();
		String openid = null;
		lastGetOauth2TokenTime = new Long(System.currentTimeMillis());
		String url = WeChatPlatform.OAUTH2_ACCESS_TOKEN.replace("APPID", Configure.getWebAppID()).replace("SECRET", Configure.getWebAppSecret()).replace("CODE", code);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		if(jsonObject != null){
			try {
				oauth2Token  = (String)jsonObject.get("access_token");
				openid  = (String)jsonObject.get("openid");			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
				String scope  = (String)jsonObject.get("scope");			//用户授权的作用域，使用逗号（,）分隔
				refreshToken  = (String)jsonObject.get("refresh_token");   //刷新accesstoken用的
				Integer expiresIn  = (Integer)jsonObject.get("expires_in");  // access_token接口调用凭证超时时间，单位（秒）
//				boolean checkToken = checkOAuth2AccessToken(oauth2Token, openid);
//				if(!checkToken){	//token无效 刷新token
//					refreshAccessToken(refreshToken);
//				}
				resultMap.put("openid", openid);
				resultMap.put("scope",scope);
				resultMap.put("oauth2Token",oauth2Token);
			} catch (Exception e) {
				logger.debug("微信webapp授权登录出错 %s", e.getMessage());
				resultMap.put("result", "1");
				return resultMap;
			}
		}
		Map auth2UserInfo = getOAuth2UserInfo(openid);
		auth2UserInfo.put("result", "0");
		logger.info("oauth userinfo:"+auth2UserInfo);
		return auth2UserInfo;
	}

	/** ===========================================小程序获取用户信息==================================================== **/
	/**
	 * @描述: 小程序获取用户信息
	 * @创建人: cuixinfu@51play.com
	 * @创建时间: 2018/11/15 17:58
	 */
	public static Map getMiniAppLoginInfo(String code) {
		Map<String, String> resultMap = new HashMap<>();
		String url = WeChatPlatform.GETMINI_LOGIN_URL.replace("APPID", Configure.getMiniAppID()).replace("SECRET", Configure.getMiniAppSecret()).replace("JSCODE", code);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		logger.info("微信小程序登录结果: {}", jsonObject);
		if(jsonObject != null){
			try {
				Integer errcode  = (Integer)jsonObject.get("errcode");  // 错误码
				String errmsg  = (String)jsonObject.get("errmsg");  // 错误信息
				String openid  = (String)jsonObject.get("openid");			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
				String unionid  = (String)jsonObject.get("unionid");   //用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回
				String session_key  = (String)jsonObject.get("session_key");			//会话密钥

				resultMap.put("errcode", errcode+"");
				resultMap.put("errmsg", errmsg);
				resultMap.put("openid", openid);
				resultMap.put("unionid", unionid);
				resultMap.put("session_key",session_key);
				return resultMap;
			} catch (Exception e) {
				logger.debug("微信小程序登录出错 %s", e.getMessage());
				resultMap.put("result", "1");
				return resultMap;
			}
		}
		return resultMap;
	}


	/**
	 * 此方法描述的是： 验证 accessToken是否有效
	 * @param oauth2Token
	 * @param openid
	 * @return boolean
	 */
	public static boolean checkOAuth2AccessToken(String oauth2Token,String openid){
		boolean isUse = true;
		String url = WeChatPlatform.OAUTH2_AUTH_TOKEN.replace("ACCESS_TOKEN", oauth2Token).replace("OPENID", openid);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		if(jsonObject != null){
			Integer errcode  = (Integer)jsonObject.get("errcode");
			String errmsg  = (String)jsonObject.get("errmsg");
			logger.info("errcode:"+errcode+"/"+"errmsg:"+errmsg);
			if(errcode!=0){
				isUse = false;
			}
		}
		return isUse;
	}
	/**
	 * 此方法描述的是： 刷新OAuthaccesstoken
	 * @param refAccessToken
	 * @return String
	 */
	public static String refreshAccessToken(String refAccessToken){
		String accessToken = null ;
		Map<String, String> resultMap = new HashMap<String, String>();
		String url = WeChatPlatform.OAUTH2_REFRESH_TOKEN.replace("APPID", Configure.getFwAppID()).replace("REFRESH_TOKEN", refAccessToken);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		if(jsonObject != null){
			oauth2Token  = (String)jsonObject.get("access_token");
			String openid  = (String)jsonObject.get("openid");			//用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
			logger.info("openid:"+openid);
			String scope  = (String)jsonObject.get("scope");			//用户授权的作用域，使用逗号（,）分隔
			refreshToken  = (String)jsonObject.get("refresh_token");   //刷新accesstoken用的
			Integer expiresIn  = (Integer)jsonObject.get("expires_in");  // access_token接口调用凭证超时时间，单位（秒）
			resultMap.put("openid", openid);
			resultMap.put("scope",scope);
			resultMap.put("oauth2Token",oauth2Token);
			accessToken = oauth2Token;
		}
		return accessToken;
	}

	/**
	 * 此方法描述的是：
	 *  第一步：用户同意授权，获取code(引导关注者打开如下页面：)
	 *  获取 code、state
	 * @return
	 * String
	 */
	public static String getStartURLToGetCode() {
		String takenUrl = WeChatPlatform.USER_AUTHORIZE;
		takenUrl= takenUrl.replace("APPID", Configure.getAppid());
		takenUrl= takenUrl.replace("REDIRECT_URI", Configure.getRedirectUrl());
		//FIXME ： snsapi_userinfo
		takenUrl= takenUrl.replace("SCOPE", "snsapi_userinfo");
		return takenUrl;
	}
	/**
	 * 获取access_token、openid
	 * 第二步：通过code获取access_token
	 * @param code url = "https://api.weixin.qq.com/sns/oauth2/access_token
	 *   ?appid=APPID
	 *   &secret=SECRET
	 *   &code=CODE
	 *   &grant_type=authorization_code"
	 * */
	public static OAuthInfo getAccess_token(String code){
		String authUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code ";
		authUrl= authUrl.replace("APPID", Configure.getDyAppID());
		authUrl = authUrl.replace("SECRET", Configure.getDyAppSecret());
		authUrl = authUrl.replace("CODE", code);
		String jsonString;
		OAuthInfo auth = null;
		try {
			jsonString = HttpHandler.httpPost(authUrl, null, null);
			logger.info("jsonString: " + jsonString);
			auth = (OAuthInfo) JSONObject.toBean(JSONObject.fromObject(jsonString), OAuthInfo.class);
		} catch (Exception e) {
			logger.debug("通过code获取access_token出错 %s", e.getMessage());
			e.printStackTrace();
		}
		return auth;
	}

	/** ===========================================网页授权获取用户基本信息==================================================== **/
	/**
	 * 此方法描述的是：网页授权获取用户基本信息
	 * @param openid
	 * @return JSONObject
	 */
	public static JSONObject getOAuth2UserInfo(String openid){
		String url = WeChatPlatform.OAUTH2_USER_INFO.replace("ACCESS_TOKEN", oauth2Token).replace("OPENID", openid);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		return jsonObject;
	}
	/**
	 * 此方法描述的是：网页授权获取用户基本信息
	 * @param openid
	 * @param oauth2Token 全局ACCESS_TOKEN
	 * @return JSONObject
	 */
	public static JSONObject getOAuth2UserInfo(String openid,String oauth2Token){
		if(StringUtils.isEmpty(openid) || StringUtils.isEmpty(oauth2Token) ){
			logger.error("openid or oauth2Token is empty, oauth2Token " + oauth2Token + ",openid = " + openid);
			return null;
		}
		String url = WeChatPlatform.OAUTH2_USER_INFO.replace("ACCESS_TOKEN", oauth2Token).replace("OPENID", openid);
		JSONObject jsonObject = HttpHandler.doGetStr(url);
		return jsonObject;
	}
	public static boolean verifyWeixinNotify(Map<String, Object> map) {
		SortedMap<String, Object> parameterMap = new TreeMap<String, Object>();
		String sign = (String) map.get("sign");
		for (Object keyValue : map.keySet()) {
			if(!keyValue.toString().equals("sign")){
				parameterMap.put(keyValue.toString(), map.get(keyValue));
			}
		}
		String createSign = Signature.getSign( parameterMap);
		if(createSign.equals(sign)){
			return true;
		}else{
			return false;
		}

	}

	/** ===========================================微信签名==================================================== **/
	/**
	 * @return
	 * @Description:jsapi_ticket 公众号用于调用微信JS接口的临时票据
	 * 生成签名之前必须先了解一下jsapi_ticket，jsapi_ticket是公众号用于调用微信JS接口的临时票据。正常情况下，jsapi_ticket的有效期为7200秒，通过access_token来获取。由于获取jsapi_ticket的api调用次数非常有限，频繁刷新jsapi_ticket会导致api调用受限，影响自身业务，开发者必须在自己的服务全局缓存jsapi_ticket 。
	 * @author wangbing
	 *@Since:2015年11月20日
	 */
	public static String getJsapiTicket(){
		if(StringUtils.isBlank(jsapiTicket) || lastGetJsapiTicketTime == null || (System.currentTimeMillis()/1000)-(lastGetJsapiTicketTime.longValue()/1000) >= 7000){
			lastGetJsapiTicketTime = new Long(System.currentTimeMillis());
			String dyAccessToken2 = WeixinHandler.getDyAccessToken();
			logger.info("dyAccessToken2",dyAccessToken2);
			String url = WeChatPlatform.GETTICKET_URL.replace("ACCESS_TOKEN",WeixinHandler.getDyAccessToken());
			logger.info("url========={}",url);
			JSONObject jsonObject = HttpHandler.doGetStr(url);
			if(jsonObject != null){
				jsapiTicket  = (String)jsonObject.get("ticket");
			}
			return jsapiTicket;
		}else{
			return jsapiTicket;
		}

	}

	/**
	 * 此方法描述的是：   验证签名
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return boolean
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典序排序 Arrays.sort(arr);
		Signature.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = Signature.byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = null;
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}

	/**
	 * 此方法描述的是：微信语义理解接口
	 * @param query
	 * @param uid
	 * @param appid
	 * @param accessToken
	 * @return JSONObject
	 */
	public static  JSONObject getSemanticSearch(String query ,String uid ,String appid ,String accessToken){
		String result = null;
		String url = WeChatPlatform.CREATE_QRCODE_URL.replace("ACCESS_TOKEN",accessToken);
		Map<String, Object> post = new HashMap<String, Object>();
		Map<String, Map> sceneMap = new HashMap<String, Map>();
		Map<String, Object> strMap = new HashMap<String, Object>();
		//strMap.put("scene_str", sceneStr);
		//strMap.put("scene_id", sceneId);
		sceneMap.put("scene", strMap);
		post.put("action_name", "QR_LIMIT_STR_SCENE");
		post.put("action_info", sceneMap);
		String json = JSONObject.fromObject(post).toString();
		logger.info(json);
		JSONObject jsonObject = HttpHandler.doPostStr(url, json);
		if(jsonObject!=null){
			result = jsonObject.getString("ticket");
		}
		return jsonObject;
	}

	/**
	 * @Description  发送公众号模板消息
	 * @Since 2016年11月7日下午1:58:31
	 */
	public static String sendTemplateMessage(String url,String template_id,String touser,String accessToken,HashMap<String,Object> template,HashMap<String,Object> miniprogram){
		String postUrl = WeChatPlatform.TEMPLATE_SEND.replace("ACCESS_TOKEN",accessToken);
		Map<String, Object> post = new HashMap<>();
		post.put("touser", touser);
		post.put("template_id", template_id);
		post.put("url", url);
		post.put("data", template);
		post.put("miniprogram", miniprogram);
		String json = JSONObject.fromObject(post).toString();
		System.out.println(json);
		JSONObject jsonObject = HttpHandler.doPostStr(postUrl, json);
		return jsonObject.toString();
	}

	/**
	 * @Description  发送小程序模板消息
	 * @Since 2016年11月7日下午1:58:31
	 */
	public static String sendMiniTemplateMessage(String touser,String template_id,String url,String formId,HashMap<String,Object> miniData,String emphasisKeyword,String accessToken){
		String postUrl = WeChatPlatform.TEMPLATE_SEND_MINI.replace("ACCESS_TOKEN",accessToken);
		Map<String, Object> post = new HashMap<>();
		post.put("touser", touser);
		post.put("template_id", template_id);
		post.put("page", url);
		post.put("form_id", formId);
		post.put("data", miniData);
		post.put("emphasis_keyword", emphasisKeyword);
		String json = JSONObject.fromObject(post).toString();
		System.out.println(json);
		JSONObject jsonObject = HttpHandler.doPostStr(postUrl, json);
		return jsonObject.toString() ;
	}
	/**
	 * @Description  发送小程序模板消息
	 * @Since 2016年11月7日下午1:58:31
	 */
	public static String sendMiniSubscribeMessage(String touser,String template_id,String page,HashMap<String,Object> miniData,String accessToken){
		String postUrl = WeChatPlatform.SUBSCRIBE_SEND_MINI.replace("ACCESS_TOKEN",accessToken);
		Map<String, Object> post = new HashMap<>();
		post.put("touser", touser);
		post.put("template_id", template_id);
		post.put("page", page);
		post.put("data", miniData);
		String json = JSONObject.fromObject(post).toString();
		JSONObject jsonObject = HttpHandler.doPostStr(postUrl, json);
		return jsonObject.toString();
	}
    
}

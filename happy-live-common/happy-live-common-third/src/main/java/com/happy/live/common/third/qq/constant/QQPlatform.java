package com.happy.live.common.third.qq.constant;

/**
 * 类名:QQPlatform <tb>
 * 描述:qq获取信息地址  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/6/24 18:20 <tb>
 */
public class QQPlatform {
    /**  获取 access_token **/
    public static final String GET_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=APPID&client_secret=APPKEY&code=CODE&redirect_uri=REDIRECT_URI";

    /**  获取 openid **/
    public static final String GET_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=ACCESSTOKEN";

    /**  获取 unionid **/
    public static final String GET_UNIONID_URL = "https://graph.qq.com/oauth2.0/me?access_token=ACCESSTOKEN&unionid=1";

    /** 获取用户基本信息（包括UnionID机制） **/
    public static final String GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?access_token=ACCESSTOKEN&oauth_consumer_key=APPID&openid=OPENID";


}

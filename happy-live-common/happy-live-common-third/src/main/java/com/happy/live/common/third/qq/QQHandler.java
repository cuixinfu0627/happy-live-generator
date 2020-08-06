package com.happy.live.common.third.qq;

import com.happy.live.common.base.JSONHandler;
import com.happy.live.common.base.StringHandler;
import com.happy.live.common.base.collection.MapHandler;
import com.happy.live.common.third.HttpHandler;
import com.happy.live.common.third.qq.constant.QQPlatform;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名:QQHandler <tb>
 * 描述:QQ第三方工具类  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/6/24 17:15 <tb>
 */
public class QQHandler {
    /** 日志类处理类  **/
    private static Logger logger = LoggerFactory.getLogger(QQHandler.class);

    /**
     * 获取AccessToken
     * @return access_token=222AD9D7C4AB567B3A5A17EAF0077F28&expires_in=7776000&refresh_token=FE57B5CA9EF7ECB27BAA37F907332ADE
     * @throws RuntimeException
     */
    public static String getAccessTokenByCode(String appid,String appkey,String redirecUri,String code) throws Exception {
        logger.info("[server]receive:[%s]", "getAccessTokenByCode");
        String userInfoUrl = QQPlatform.GET_ACCESS_TOKEN.replace("APPID",appid).replace("APPKEY",appkey)
                .replace("REDIRECT_URI",redirecUri).replace("CODE",code);

        String content = HttpHandler.doGetStr2(userInfoUrl);

        logger.info("[server]return:[%s](T:%s) ResultMsg(retCode:SUCCESS) [%s]","getAccessTokenByCode", content);
        if (StringHandler.isEmpty(content)) {
            throw new Exception("获取QQ端的获取AccessToken异常");
        }
        String rgexAccess_token = "access_token=(.*?)&";
        String accessToken = getSubUtilSimple(content, rgexAccess_token);
        return accessToken;
    }

    /**通过accessToken获取用户信息
     * @param appId
     * @param accessToken
     */
    public static Map<String,String> getUserInfoByAccessToken(String appId, String accessToken) throws Exception {
        Map<String, String> resultMap = getUnionIdAndOpenId(accessToken);
        if (StringHandler.isEmpty(resultMap)){
            logger.info("[server]receive:[%s]", "getUserInfoByAccessToken");
            throw new Exception("获取QQ端的getUserInfoByAccessToken异常");
        }
        String openId = resultMap.get("openid");
        String unionId = resultMap.get("unionid");
        if (StringHandler.isEmpty(unionId) || StringHandler.isEmpty(openId)){
            logger.info("[server]receive:[%s]", "getUserInfoByAccessToken");
            throw new Exception("获取QQ端的getUserInfoByAccessToken异常");
        }
        Map<String, String> userInfo = getUserInfo(appId, accessToken, openId);
        userInfo.put("openid",openId);
        userInfo.put("unionid",unionId);
        return userInfo;
    }

    /**通过accessToken获取openid
     * @param accessToken
     */
    public static String getOpenidByAccessToken(String accessToken) throws Exception {
        logger.info("[server]receive:[%s]", "QQgetOpenid");
        //拼装获取QQ用户信息的URL
        String userInfoUrl = QQPlatform.GET_OPENID_URL.replace("ACCESSTOKEN",accessToken);
        //get方式请求获取用户信息
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} ); 这种的，需要跟上面一样解析
        String content = HttpHandler.doGetStr2(userInfoUrl);
        logger.info("[server]return:[%s](T:%s) ResultMsg(retCode:SUCCESS) [%s]","QQgetOpenid", content);
        if (StringHandler.isEmpty(content)) {
            throw new Exception("获取QQ端的token异常");
        }
        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(content);
        String openid = "";
        if(m.find()){
            openid = m.group(1);
        }
        return openid;
    }

    /**
     * 获取认证信息
     * @param accessToken
     * @return {"client_id":"100490958","openid":"83366F60D5248050822EEADC97B2475C","unionid":"UID_CCD7F804790436F593F94B57F2BC89EF"}
     * @throws RuntimeException
     */
    public static Map<String, String> getUnionIdAndOpenId(String accessToken) throws Exception {
        Map<String,String> result = new HashMap<>();
        logger.info("[server]receive:[%s]", "getUnionIdAndOpenId");
        //拼装获取QQ用户信息的URL
        String userInfoUrl = QQPlatform.GET_UNIONID_URL.replace("ACCESSTOKEN",accessToken);
        String content = HttpHandler.doGetStr2(userInfoUrl);
        logger.info("[server]return:[%s](T:%s) ResultMsg(retCode:SUCCESS) [%s]","QQgetOpenid", content);
        if (StringHandler.isEmpty(content)) {
            logger.error("[server]receive:[%s] error", "getUnionIdAndOpenId");
            return null;
        }
        Matcher open = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(content);
        Matcher union = Pattern.compile("\"unionid\"\\s*:\\s*\"(\\w+)\"").matcher(content);
        String openid = "";
        String unionid = "";
        if(open.find()){
            openid = open.group(1);
        }
        if(union.find()){
            unionid = union.group(1);
        }
        result.put("openid",openid);
        result.put("unionid",unionid);
        return result;
    }

    /**
     * 方法: getUserInfo <br>
     * 描述: 获取用户信息 <br>
     * 作者: hailong@xiu8.com<br>
     * 时间: 2016年3月19日 下午4:49:47
     * @param accessToken
     * @param openId
     * @return  nickname 用户在QQ空间的昵称;
     * 			figureurl_qq_1 40×40像素的QQ头像URL;
     * 			figureurl_qq_2 100×100像素的QQ头像URL;
     * 			gender 性别。 如果获取不到则默认返回"男";
     */
    public static Map<String, String> getUserInfo(String appId, String accessToken, String openId) throws Exception {
        logger.info("[server]receive:[%s]", "QQUserInfo");
        //拼装获取QQ用户信息的URL
        String userInfoUrl = QQPlatform.GET_USER_INFO_URL.replace("ACCESSTOKEN",accessToken).replace("APPID",appId).replace("OPENID",openId);
        //get方式请求获取用户信息
        JSONObject jsonObject = HttpHandler.doGetStr(userInfoUrl);
        if (StringHandler.isEmpty(jsonObject)) {
            logger.error("[server]receive:[%s] error", "QQUserInfo");
            return null;
        }
        logger.info("[server]return:[%s](T:%s) ResultMsg(retCode:SUCCESS) [%s]", "getUserInfo", JSONHandler.getJsonStr(jsonObject));
        Map<String, Object> infoMap = JSONHandler.getObjectByJsonStr(String.valueOf(jsonObject), Map.class);
        //判断获取用户信息是否成功 ret=0标示正确返回
        if (null == infoMap.get("ret") || 0 != StringHandler.getInt(infoMap.get("ret"), -1)) {
            logger.error("[server]receive:[%s] error", "QQUserInfo");
            return null;
        }
        return MapHandler.obj2StrNotNull(infoMap);
    }

    /**
     * 返回单个字符串，若匹配到多个的话就返回第一个，方法与getSubUtil一样
     * @param soap 文本
     * @param rgex 正则
      */
    public static String getSubUtilSimple(String soap,String rgex){
        Pattern pattern = Pattern.compile(rgex);// 匹配的模式
        Matcher m = pattern.matcher(soap);
        while(m.find()){
            return m.group(1);
        }
        return "";
    }

    public static void main(String[] args) {
        String str2 = "access_token=222AD9D7C4AB567B3A5A17EAF0077F28&expires_in=7776000&refresh_token=FE57B5CA9EF7ECB27BAA37F907332ADE";
        String str = "abc3443abcfgjhgabcgfjabc";
        String rgex = "access_token=(.*?)&";
        System.out.println(getSubUtilSimple(str2, rgex));
    }

}

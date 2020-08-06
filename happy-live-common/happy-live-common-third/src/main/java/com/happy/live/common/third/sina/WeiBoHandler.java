package com.happy.live.common.third.sina;

import com.happy.live.common.base.JSONHandler;
import com.happy.live.common.base.StringHandler;
import com.happy.live.common.base.collection.MapHandler;
import com.happy.live.common.third.HttpHandler;
import com.happy.live.common.third.sina.constant.WeiBoPlatform;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 类名:WeiBoHandler <tb>
 * 描述:新浪微博第三方接入助手  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/6/24 17:15 <tb>
 */
public class WeiBoHandler {
    /** 请求第三方接口超时时间 */
    private static int TIMEOUT = 1500;
    /** 日志类处理类  **/
    private static Logger logger = LoggerFactory.getLogger(WeiBoHandler.class);

    /**
     * 获取AccessToken
     */
    private Map<String, String> getAccessToken(String code) throws Exception {
        logger.info("[server]receive:[%s]", "weibo getAccessToken");
        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code");
        sb.append("&client_id=" + "appId");
        sb.append("&client_secret=" + "appSecret");
        sb.append("&redirect_uri=" + "authPath");
        sb.append("&code=" + code);
        JSONObject json = HttpHandler.doPostStr(WeiBoPlatform.ACCESS_TOKEN_URL,sb.toString());
        if (StringHandler.isEmpty(json)) {
            throw new Exception("获取weiboAccessToken");
        }
        logger.info("[server]return:[%s](T:%s) ResultMsg(retCode:SUCCESS) [%s]","getAccessToken", JSONHandler.getJsonStr(json));
        Map<String, Object> infoMap = JSONHandler.getObjectByJsonStr(String.valueOf(json), Map.class);
        //判断获取用户信息是否成功 ret=0标示正确返回
        Integer errorCode = (Integer)infoMap.get("error_code");
        String error = (String)infoMap.get("error");
        String errorMsg = (String)infoMap.get("error_description");
        if(errorCode != null && errorCode != 0) {
            return null;
        }
        String accessToken = (String)infoMap.get("access_token");
        String uiid = (String)infoMap.get("uid"); // 这个uid就是微博用户的唯一用户ID，可以通过这个id直接访问到用户微博主页
        int expires = (Integer)infoMap.get("expires_in"); // 有效期，单位秒
        /**
         * 返回数据
         *  {
         *      "access_token": "ACCESS_TOKEN",
         *      "expires_in": 1234,
         *      "remind_in":"798114",
         *      "uid":"12341234"
         *  }
         */
        return MapHandler.obj2StrNotNull(infoMap);

    }

    /**
     * 获取用户信息
     * @param access_token
     * @param uid 查询的用户ID
     */
    private Map<String, String> getUserInfo(String access_token,String uid) throws Exception {
        logger.info("[server]receive:[%s]", "weibo getUserInfo");
        StringBuilder sb = new StringBuilder();
        sb.append("?access_token=" + access_token);
        sb.append("&uid=" + uid);
        String url = "";
        JSONObject json = HttpHandler.doGetStr(url);
        if (StringHandler.isEmpty(json)) {
            throw new Exception("获取 getUserInfo");
        }
        logger.info("[server]return:[%s](T:%s) ResultMsg(retCode:SUCCESS) [%s]","getUserInfo", JSONHandler.getJsonStr(json));
        Map<String, Object> infoMap = JSONHandler.getObjectByJsonStr(String.valueOf(json), Map.class);

        Integer errorCode = (Integer)infoMap.get("error_code");
        String error = (String)infoMap.get("error");
        String  errorMsg = (String)infoMap.get("error_description");
        if(errorCode != null && errorCode != 0) {
            return null;
        }

        String nickname = (String)infoMap.get("screen_name");
        String avatar = (String)infoMap.get("avatar_large");
        String gender = (String)infoMap.get("gender");

        //返回参数：查看http://open.weibo.com/wiki/2/users/show
        return MapHandler.obj2StrNotNull(infoMap);
    }

}

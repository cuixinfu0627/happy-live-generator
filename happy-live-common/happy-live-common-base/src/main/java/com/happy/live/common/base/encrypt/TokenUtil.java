package com.happy.live.common.base.encrypt;


import java.util.HashMap;
import java.util.Map;

/**
 * 通行证token工具类
 */
public abstract class TokenUtil {

    /**
     * 通过用户id生成可识别用户的token 同一id多次生成是一致的
     * @param userId 用户id
     * @return token
     */
    public static String getToken(long userId){
        Map<String,String> params = new HashMap<>() ;
        params.put("userId","" + userId) ;
        return SignUtil.getSign(params) ;
    }

}

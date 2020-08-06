package com.happy.live.common.base.encrypt;


import cn.t.util.common.RandomUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名:PasswordUtil <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/11/18 11:25 <tb>
 */
public abstract class PasswordUtil {
    /**
     * 通过一次md5密码获取正式密码
     * @param password 原始密码一次md5的加密
     * @return 增加扰码后的密码和扰码 key 为密码 value 为扰码
     */
    public static Pair<String,String> getPassword(String password){
        Map<String,String> params = new HashMap<>() ;
        params.put("password",password) ;
        String salt = RandomUtil.randomString(8).toLowerCase() ;
        params.put("salt","" + salt) ;
        return new Pair<String,String>(SignUtil.getSign(params),salt) ;
    }

    /**
     * 校验新密码与旧密码是否一致
     * @param password 一次md5密码
     * @param salt 已有扰码
     * @param oldPassword 已有密码
     * @return 新密码是否与旧密码一致
     */
    public static boolean verifyPassword(String password,String salt,String oldPassword){
        Map<String,String> params = new HashMap<>() ;
        params.put("password",password) ;
        params.put("salt","" + salt) ;
        return oldPassword.equals(SignUtil.getSign(params)) ;
    }

    public static void main(String[] args) {
        Pair<String,String> password = getPassword("0e4ac07dc63a238484ed8c92bfef30d3") ;
        System.out.println(password.getKey());
        System.out.println(password.getValue());
        System.out.println(verifyPassword("0e4ac07dc63a238484ed8c92bfef30d3","bmhrssmo","27a398ff0c82674ab613e85525061481")) ;
    }
}

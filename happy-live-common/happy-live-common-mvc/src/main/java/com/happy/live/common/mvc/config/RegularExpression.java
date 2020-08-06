package com.happy.live.common.mvc.config;

/**
 * @name: RegularExpression <tb>
 * @title: 请输入类描述  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/3/26 10:29 <tb>
 */
public class RegularExpression {
    public static final String PHONE_OR_EMAIL = "^(([1]([3-9])[0-9]{9})|(([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+))$" ;
    public static final String PHONE_OR_EMAIL_OR_USERNAME = "^(([1]([3-9])[0-9]{9})|(([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+))|([a-zA-Z0-9]{6,20})$" ;
    public static final String PHONE = "^[1]([3-9])[0-9]{9}$" ;
    public static final String PWD = "[0-9a-fA-F]{32}";
    public static final String EMAIL = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$" ;
    public static final String CODE = "^[0-9]{6}$" ;
    public static final String IDCARD = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$" ;
}


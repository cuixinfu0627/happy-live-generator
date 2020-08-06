package com.happy.live.common.mvc.config;

public class LiveRedisKeys {

    public static final String LOGIN_ERROR_COUNT =                    concat("login","error","count") ;
    public static final String LOGIN_ADMIN_ERROR_COUNT =              concat("login","admin","error","count") ;
    public static final String LOGIN_ERROR_MAX_COUNT =                concat("login","error","max","count") ;
    public static final String LOGIN_ADMIN_ERROR_MAX_COUNT =          concat("login","admin","error","max","count") ;

    public static final String LOGIN_PC_TICKET_USER =                 concat("login","pc","ticket","user") ;
    public static final String LOGIN_PC_TICKET_USER_ADMIN =           concat("login","pc","ticket","user","admin") ;

    public static final String LOGIN_APP_TICKET_USER =                concat("login","app","ticket","user") ;
    public static final String LOGIN_APP_TICKET_USER_ADMIN =          concat("login","app","ticket","user","admin") ;

    public static final String LOGIN_USER_TICKET =                    concat("login","user","ticket") ;
    public static final String LOGIN_USER_ADMIN_TICKET =              concat("login","user","admin","ticket") ;

    public static final String USER_CACHE_BY_ID =                     concat("user","cache","by","id") ;
    public static final String USER_ADMIN_CACHE_BY_ID =               concat("user","admin","cache","by","id") ;

    public static final String BIND_EMAIL_CODE =                      concat("bind","email","code") ;

    public static final String BIND_EMAIL_LINK =                      concat("bind","email","link") ;

    public static final String SEND_CODE =                            concat("send","code") ;
    public static final String SEND_RATE =                            concat("send","rate") ;
    public static final String SEND_COUNT =                           concat("send","count") ;
    public static final String CHECK_CODE_ERROR_COUNT =               concat("check","count","error","count") ;

    public static final String CHANGE_PHONE_NUMBER =                  concat("change","phone","number") ;
    public static final String CHANGE_USER_EMAIL =                    concat("change","user","email") ;
    public static final String CHANGE_USER_PWD =                      concat("change","user","pwd") ;

    public static final String WECHAT_LOGIN_INFO =                    concat("wechat","login","info") ;

    public static final String WECHAT_USER_INFO =                     concat("wechat","user","info") ;

    public static final String QQ_LOGIN_INFO =                        concat("qq","login","info") ;

    public static final String QQ_USER_INFO =                         concat("qq","user","info") ;

    public static final String NOTIFY_WEB_SOCKET =                    concat("notify","webSocket") ;

    public static final String NOTIFY_PHONE_MESSAGE =                 concat("notify","phoneMessage") ;

    public static final String NOTIFY_PHONE_PUSH =                    concat("notify","phonePush") ;

    public static final String NOTIFY_EMAIL =                         concat("notify","email") ;

    public static final String NOTIFY_WE_CHAT =                       concat("notify","weChat") ;

    public static String concat(String ... keys){
        StringBuilder stringBuilder = new StringBuilder() ;
        for(String key : keys){
            stringBuilder.append(key).append(":") ;
        }
        return stringBuilder.substring(0,stringBuilder.lastIndexOf(":")) ;
    }




}

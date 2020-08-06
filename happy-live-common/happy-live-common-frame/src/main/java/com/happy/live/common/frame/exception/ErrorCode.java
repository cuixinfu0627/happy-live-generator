package com.happy.live.common.frame.exception;


import java.util.HashMap;
import java.util.Map;

public enum ErrorCode {

    /**
     * 400
     */
    BAD_PARAM_EXCEPTION(400, "请求参数不合法"),

    /**
     * 404
     */
    SOURCE_NOT_FOUND_EXCEPTION(404, "资源不存在"),

    /**
     * 请求方法不支持
     * */
    REQUEST_METHOD_NOT_SUPPORT(405, "请求方法不支持"),

    /**
     * 服务器内部异常
     */
    SERVER_INTERNAL_EXCEPTION(500, "服务器内部异常"),

    /******************************************* 数据操作错误异常 1001 ~ 1099 ******************************************/
    INSERT_NOT_NULL(1001,"插入对象为空"),
    BATCH_INSERT_NOT_NULL(1002,"批量插入集合为空"),
    UPDATE_NOT_NULL(1003,"更新对象为空"),
    BATCH_UPDATE_NOT_NULL(1004,"批量更新集合为空"),
    DELETE_NOT_NULL(1005,"删除id为空"),
    GET_ID_NOT_NULL(1006,"查询数据id为空"),
    GET_PARAM_NOT_NULL(1007,"条件查询参数为空"),

    /******************************************* 文件上传错误  2001 ~ 2099 ******************************************/
    FILE_UPLOAD_NULL(2001,"上传文件为空"),
    FILE_UPLOAD_FORMAT_ERROR(2002,"文件格式不支持"),
    FILE_UPLOAD_SIZE_ERROR(2003,"上传文件过大"),
    MESSAGE_PARAM_NULL(2004,"发送系统消息参数为空"),


    /******************************************* 用户 100101 ~ 100199 ******************************************/
    /**************** 表单 ******************/
    FORM_USER_USERNAME_MISSING(100101, "用户名未输入"),
    FORM_USER_PASSWORD_MISSING(100102, "密码未输入"),
    FORM_USER_CODE_MISSING(100103, "验证码未输入"),

    FORM_USER_USERNAME_ERROR(100104, "用户名错误"),
    FORM_USER_PASSWORD_ERROR(100105, "密码错误"),
    FORM_USER_USERNAME_OR_PASSWORD_ERROR(100106, "用户名或密码错误"),
    FORM_USER_CODE_ERROR(100107, "验证码错误"),

    CHECK_USER_NOT_LOGIN(100108, "用户未登录"),
    CHECK_USER_NO_AUTHORITY(100109, "无权限"),


    FORM_USER_USERNAME_ALREADY_EXIST(100110, "用户名已存在") ,

    REGISTER_USER_PHONE_MISSING(100111, "手机号未输入"),
    REGISTER_USER_PHONE_EXIST(100112, "手机号已注册"),
    REGISTER_USER_PHONE_SEND_ERROR(100113, "手机号验证码输入错误次数过多"),
    REGISTER_USER_PHONE_MAX_COUNT_ERROR(100114, "手机号发送验证码次数过多"),
    REGISTER_PHONE_SEND_RATE_ERROR (100115,"手机号验证码发送频率过快！"),
    SEND_MESSAGE_ERROR(100116,"短信发送失败！"),
    CODE_INVALID(100117,"验证码失效"),
    CODE_ERROR(100118,"验证码错误！"),

    EMIL_MISSING(100119,"邮箱账号未输入"),
    EMIL_FORMAT_ERROR(100120,"邮箱账号格式错误"),
    EMIL_EXIST_ERROR(100120,"邮箱账号已绑定"),
    EMIL_SEND_CODE_ERROR(100121,"邮箱发送邮件错误"),

    EMAIL_BIND_ERROR(100122,"绑定邮箱错误"),

    EMAIL_BIND_CODE_TIMEOUT(100123,"绑定邮箱信息已过期,请重新发送!"),

    /** 微信相关状态 **/
    FORM_USER_UNIONID_MISSING(100124,"微信授权用户唯一标识为空!"),
    WECHAT_LOGIN_CALLBACK_URL_ERROR(100125,"微信回调登录地址为空!"),
    WECHAT_OAUTH_VERIFY_ERROR(100126,"微信授权校验错误!"),
    WECHAT_ALREADY_BINDUSER(100127,"该微信已经绑定了账号,请使用别的微信!"),
    WECHAT_OAUTH_VERIFY_EXPIRE(100128,"微信授权校验过期,请重新登录!"),

    BIND_KEY_ERROR(100130,"绑定信息已过期"),

    EMAIL_NOT_BIND(100131,"未绑定邮箱"),

    FORM_USER_OLD_PWD_ERROR(100131,"旧密码错误"),

    FROM_USER_ACCOUNT_NOT_EXIST(100132,"此账号不存在！"),

    FORM_USER_PASSWORD_PATTERN_ERROR(100133, "密码格式错误！"),
    FORM_USER_USERNAME_PATTERN_ERROR(100134, "用户名格式错误！"),
    FORM_USER_PHONE_PATTERN_ERROR(100135, "手机号格式错误！"),
    FORM_USER_EMAIL_PATTERN_ERROR(100136, "邮箱格式错误！"),
    FORM_CODE_PATTERN_ERROR(100137, "验证码格式错误！"),
    FROM_USER_LOGIN_ERROR_COUNT_ERROR(100138, "登录错误次数过多，24小时后再试！"),
    PHONE_SEND_ERROR(100139, "手机发送错误！"),
    EMAIL_SEND_ERROR(100140, "邮箱发送错误！"),
    FROM_USER_NAME_MISSING(100141,"姓名未输入"),
    FROM_USER_IDCARD_MISSING(100142,"身份证未输入"),
    FORM_IDCARD_PATTERN_ERROR(100143,"身份证格式错误"),
    IDCARD_AUTH_ERROR(100144,"实名认证失败"),
    IDCARD_AUTH_EXIST(100145,"此账号已实名认证"),
    IDCARD_AUTH_IDCARD_EXIST(100146,"此身份号已被其他账号使用"),

    /** 微信授权相关*/
    WECHAT_LOGIN_SUCCEED(100150,"微信用户登录成功"),
    WECHAT_LOGIN_EXIST(100151,"该微信没有注册账号"),

    ;

    /******************************************* 角色 ******************************************/
    /**************** 表单 ******************/






    private Integer code;

    private String msg;

    private static final Object lock = new Object() ;
    private static Map<String,ErrorCode> errorCodeMap ;
    private static Map<Integer,ErrorCode> intErrorCodeMap ;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCode getErrorCodeByName(String name) {
        if(errorCodeMap == null){
            synchronized (lock){
                if(errorCodeMap == null){
                    errorCodeMap = new HashMap<>() ;
                    for (ErrorCode errorCode : values()) {
                        errorCodeMap.put(errorCode.name(),errorCode) ;
                    }
                }
            }
        }
        return errorCodeMap.get(name);
    }

    public static ErrorCode getErrorCodeByCode(int code) {
        if(intErrorCodeMap == null){
            synchronized (lock){
                if(intErrorCodeMap == null){
                    intErrorCodeMap = new HashMap<>() ;
                    for (ErrorCode errorCode : values()) {
                        intErrorCodeMap.put(errorCode.code,errorCode) ;
                    }
                }
            }
        }
        return intErrorCodeMap.get(code);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}

package com.happy.live.common.frame.entity;
/**
 * 
 * 200	操作成功
401	签名不正确
402	用户不存在
403	手机号不存在
404	密码不正确
405	第三方授权失败
406	好友不存在
407	日期格式不正确
408	手机号已使用
409	验证码获取过于频繁，请稍后再试
410 验证码不正确
411 修改头像失败
412 模板消息发送失败
 */
public  class BaseVO {

	public static final int CODE_200 = 200;
	
	public static final int CODE_401 = 401;
	
	public static final int CODE_402 = 402;
	
	public static final int CODE_403 = 403;
	
	public static final int CODE_404 = 404;
	
	public static final int CODE_405 = 405;
	
	public static final int CODE_406 = 406;
	
	public static final int CODE_407 = 407;
	
	public static final int CODE_408 = 408;
	
	public static final int CODE_409 = 409;
	
	public static final int CODE_410 = 410;
	
	public static final int CODE_411 = 411;
	
	public static final int CODE_412 = 412;
	
	private int code = CODE_200 ;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

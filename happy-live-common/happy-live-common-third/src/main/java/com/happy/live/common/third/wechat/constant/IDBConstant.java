package com.happy.live.common.third.wechat.constant;

/**
 * 类名: IDBConstant <br>  
 * 描述: 微信数据库字典值 <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年12月4日 下午4:04:52 <br>
 */
public interface IDBConstant {
	/** 空父字典键 **/
	public static final int NULL_PARENT_KEY = -1;
	/** 性别 **/
	public static final String GENDER = "GENDER";
	/** 男 **/
	public static final int GENDER_MALE = 1;
	/** 女 **/
	public static final int GENDER_FEMALE = 2;
	/** 状态 **/
	public static final String STATUS = "STATUS";
	/** 有效 **/
	public static final int STATUS_VALID = 1; 
	/** 无效 **/
	public static final int STATUS_INVALID = 2; 
	/** 消息接收者（所有人） **/
	public static final int MESSAGE_RECEIVE_ID_ALL = -1;
	/** 消息接收者（系统） **/
	public static final int MESSAGE_RECEIVE_ID_SYSTEM = -2;
	/** 终端类型IOS **/
	public static final String TERMINAL_TYPE_IOS = "1";
	/** 终端类型Android **/
	public static final String TERMINAL_TYPE_ANDROID = "2";
	/** 微信请求成功的返回码 **/
	public static final String RESULT_CODE_SUCCESS = "1"; 
	/** 关注类型 关注 **/
	public static final int SUBSCRIBE_STATUS_YES = 1;
	/** 关注类型  取消关注  **/
	public static final int SUBSCRIBE_STATUS_NO = 2;
	
}


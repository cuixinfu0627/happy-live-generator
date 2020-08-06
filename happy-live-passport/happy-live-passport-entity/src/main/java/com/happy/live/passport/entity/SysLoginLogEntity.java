package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 后台登录日志表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-03-31 14:14:15
 */
@Data
@TableName("sys_login_log")
public class SysLoginLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户名称
	 */
	private String nickname;
	/**
	 * 登录设备：1、PC登录,2,App登录
	 */
	private Integer type;
	/**
	 * 登录设备：1、登录,2,注销
	 */
	private Integer operationType;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 地区
	 */
	private String area;
	/**
	 * 登录IP地址
	 */
	private String ip;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后一次更新时间
	 */
	private Date updateTime;

}

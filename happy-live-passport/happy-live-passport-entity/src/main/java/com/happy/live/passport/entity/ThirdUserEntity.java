package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 第三方账号
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 10:18:34
 */
@Data
@TableName("sys_third_user")
public class ThirdUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 第三方来源:1微信，2腾讯QQ，3新浪微博
	 */
	private Integer origin;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 性别：1男，2女
	 */
	private Integer gender;
	/**
	 * 国家
	 */
	private String country;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 微信标识
	 */
	private String unionId;
	/**
	 * 公众号openid
	 */
	private String openId;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 绑定时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**扩展字段**/
	private SysUserEntity sysUser;

}

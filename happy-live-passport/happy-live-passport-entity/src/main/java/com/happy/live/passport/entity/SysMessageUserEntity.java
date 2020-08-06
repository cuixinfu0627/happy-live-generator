package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户消息表
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 17:45:41
 */
@Data
@TableName("sys_message_user")
public class SysMessageUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 消息id
	 */
	private Long messageId;
	/**
	 * 消息大类型冗余字段
	 */
	private Integer messageType;
	/**
	 * 接收人类型
	 */
	private Integer toType;
	/**
	 * 接收人用户id
	 */
	private Long toUserId;
	/**
	 * 接收人昵称
	 */
	private String toNickName;
	/**
	 * 读取时间
	 */
	private Date readTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 状态
	 */
	private Integer status;

}

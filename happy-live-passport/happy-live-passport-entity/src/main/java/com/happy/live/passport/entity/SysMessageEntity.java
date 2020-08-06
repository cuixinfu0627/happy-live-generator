package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 消息表
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 17:45:41
 */
@Data
@TableName("sys_message")
public class SysMessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 发送端用户类型
	 */
	private Integer fromType;
	/**
	 * 发送用户id
	 */
	private Long fromUserId;
	/**
	 * 发送人昵称
	 */
	private String fromNickName;
	/**
	 * 发送用户组: 1所有人，2,其他用户
	 */
	private Integer groupType;
	/**
	 * 消息大类型，0.系统通知，其它属于业务消息  1.系统消息，2.服务提醒，3.交易提醒
	 */
	private Integer type;
	/**
	 * 消息类型
	 */
	private Integer messageType;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 相关数据id
	 */
	private Long referenceId;
	/**
	 * 删除状态
	 */
	private Integer deleted;
	/**
	 * 通知流水号
	 */
	private String notifyCode;
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

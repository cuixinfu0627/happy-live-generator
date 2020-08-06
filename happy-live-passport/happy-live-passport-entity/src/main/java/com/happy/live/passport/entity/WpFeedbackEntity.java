package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 反馈意见
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@Data
@TableName("wp_feedback")
public class WpFeedbackEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 问题单号
	 */
	private String number;
	/**
	 * 服务类型：1账号问题，2项目问题,3交易问题，4建议反馈
	 */
	private Byte type;
	/**
	 * 问题对象id
	 */
	private Long targetId;
	/**
	 * 目标对象
	 */
	private String targetObj;
	/**
	 * 提交人
	 */
	private Long userId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 联系手机号
	 */
	private String tel;
	/**
	 * l联系邮箱
	 */
	private String email;
	/**
	 * 反馈图片
	 */
	private String image;
	/**
	 * 回复内容
	 */
	private String reply;
	/**
	 * 处理人id
	 */
	private Integer replyUserId;
	/**
	 * 用户评价:1满意，2不满意,3待评价
	 */
	private Byte appraise;
	/**
	 * 评价时间
	 */
	private Date appraiseTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 回复时间
	 */
	private Date updateTime;
	/**
	 * 状态:1待处理，2处理中，3已处理
	 */
	private Byte status;

}

package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 反馈意见类型
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@Data
@TableName("wp_feedback_type")
public class WpFeedbackTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 服务类型：1账号问题，2项目问题,3交易问题，4建议反馈
	 */
	private Byte type;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 排序
	 */
	private Long sort;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后一次更新时间
	 */
	private Date updateTime;
	/**
	 * 状态是否是其他类型:1是，0不是
	 */
	private Byte status;

}

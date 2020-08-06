package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户圈子
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-07-02 14:11:20
 */
@Data
@TableName("wp_user_circle")
public class WpUserCircleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 圈子id
	 */
	private Long circleId;
	/**
	 * 是否是圈主：0不是，1是
	 */
	private Byte owner;
	/**
	 * 关注时间
	 */
	private Date createTime;

}

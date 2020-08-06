package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 壁纸圈子
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-07-02 14:11:20
 */
@Data
@TableName("wp_circle")
public class WpCircleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 圈字创建人:圈主
	 */
	private Long userId;
	/**
	 * 圈子logo
	 */
	private String logo;
	/**
	 * 圈子关键词
	 */
	private String keyword;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 创建时间
	 */
	private Date creatData;

}

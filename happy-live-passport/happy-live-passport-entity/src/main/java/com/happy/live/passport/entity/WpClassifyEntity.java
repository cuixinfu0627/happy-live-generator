package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:01
 */
@Data
@TableName("wp_classify")
public class WpClassifyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 分类:一级分类0 二级为一级的id
	 */
	private Long parentId;
	/**
	 * 名称
	 */
	private String classifyName;
	/**
	 * 图标
	 */
	private String classifyPic;
	/**
	 * 序号
	 */
	private Integer orderNo;

	/**
	 * 父分类名称
	 */
	@TableField(exist=false)
	private String parentName;

}

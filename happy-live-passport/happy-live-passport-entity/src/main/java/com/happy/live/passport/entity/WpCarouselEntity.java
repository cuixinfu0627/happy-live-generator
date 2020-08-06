package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 轮播图
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-12 18:40:07
 */
@Data
@TableName("wp_carousel")
public class WpCarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 主题id
	 */
	private Long themeId;
	/**
	 * 一级分类
	 */
	private Long classifyA;
	/**
	 * 二级分类
	 */
	private Long classifyB;
	/**
	 * 图片
	 */
	private String picUrl;
	/**
	 * 图片
	 */
	private String clickLink;
	/**
	 * 序号
	 */
	private Integer orderNo;

}

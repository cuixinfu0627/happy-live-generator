package com.happy.live.passport.entity;

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
@TableName("wp_theme")
public class WpThemeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 主题名称
	 */
	private String name;
	/**
	 * 显示颜色
	 */
	private String color;
	/**
	 * 图片地址
	 */
	private String picUrl;
	/**
	 * 点击量
	 */
	private Integer clickNum;
	/**
	 * 创建时间
	 */
	private Date createtime;

}

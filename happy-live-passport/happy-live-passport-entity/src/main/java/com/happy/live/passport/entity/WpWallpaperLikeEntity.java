package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 壁纸点赞
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@Data
@TableName("wp_wallpaper_like")
public class WpWallpaperLikeEntity implements Serializable {
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
	 * 壁纸ID
	 */
	private Long wallpaperId;
	/**
	 * 点赞时间
	 */
	private Date createTime;

}

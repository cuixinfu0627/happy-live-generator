package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 壁纸评论
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@Data
@TableName("wp_wallpaper_comment")
public class WpWallpaperCommentEntity implements Serializable {
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
	 * 评论内容
	 */
	private String comment;
	/**
	 * 评论时间
	 */
	private Date commentTime;
	/**
	 * 回复内容
	 */
	private String reply;
	/**
	 * 回复时间
	 */
	private Date replyTime;

}

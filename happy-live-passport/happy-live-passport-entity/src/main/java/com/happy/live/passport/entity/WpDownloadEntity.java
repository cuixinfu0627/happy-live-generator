package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户下载壁纸
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-06-16 14:49:40
 */
@Data
@TableName("wp_download")
public class WpDownloadEntity implements Serializable {
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
	 * 下载时间
	 */
	private Date creatTime;

}

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
@TableName("wp_wallpaper")
public class WpWallpaperEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Long id;
	/**
	 * 上传人id
	 */
	private Long userId;
	/**
	 * 一级分类
	 */
	private Long classifyA;
	/**
	 * 二级分类
	 */
	private Long classifyB;
	/**
	 * 图片地址
	 */
	private String picUrl;
	/**
	 * 是否上架 0未审核1上架2下架3审核未通过
	 */
	private Byte isShelve;
	/**
	 * 是否推荐 1推荐0不推荐
	 */
	private String isRecommend;
	/**
	 * 上架时间
	 */
	private Date shelvedate;
	/**
	 * 上传时间
	 */
	private Date creatdate;
	/**
	 * 点击量
	 */
	private Integer clickNum;
	/**
	 * 收藏量
	 */
	private Integer collectionNum;
	/**
	 * 下载量
	 */
	private Integer downloadNum;
	/**
	 * 标签
	 */
	private String label;
	/**
	 * 分辨率类型 1.18:9 2.16:9
	 */
	private Byte ratioLx;
	/**
	 * keyword 关键词
	 */
	private String keyword;

	/**
	 * 是否置顶 1置顶0不置顶
	 */
	private Byte top;

	/**
	 * 圈子ID
	 */
	private Long circleId;
}

package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
@Data
@TableName("document")
public class DocumentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Integer id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 分类
	 */
	private String catagoryId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 关键词
	 */
	private String keyword;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 图片URL
	 */
	private String img;
	/**
	 * 预览图片
	 */
	private String imageFile;
	/**
	 * 排序字段
	 */
	private String sortname;
	/**
	 * 点击次数
	 */
	private Integer clickcount;
	/**
	 * 是否置顶
	 */
	private Integer showtop;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 发布时间
	 */
	private Date publishTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 状态（0:未发布 1:发布）
	 */
	private Integer state;
	/**
	 * 备注
	 */
	private String remark;

}

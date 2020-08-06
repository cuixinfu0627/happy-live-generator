package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 上传附件
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 11:22:53
 */
@Data
@TableName("sys_upload_file")
public class UploadFileEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Long id;
	/**
	 * 文件名
	 */
	private String name;
	/**
	 * 源文件名
	 */
	private String originalName;
	/**
	 * 文件URL
	 */
	private String url;
	/**
	 * 文件类型
	 */
	private String type;
	/**
	 * 文件大小
	 */
	private Long size;
	/**
	 * 状态：1 封面，0不是
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}

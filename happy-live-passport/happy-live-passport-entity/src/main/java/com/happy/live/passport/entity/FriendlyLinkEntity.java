package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 友情链接表
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
@Data
@TableName("friendly_link")
public class FriendlyLinkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * URL
	 */
	private String url;
	/**
	 * logo
	 */
	private String logo;
	/**
	 * 顺序
	 */
	private Integer orderNum;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}

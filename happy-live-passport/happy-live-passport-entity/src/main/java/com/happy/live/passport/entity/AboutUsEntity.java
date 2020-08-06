package com.happy.live.passport.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 关于我们
 * 
 * @author live
 * @email 870459550@qq.com
 * @date 2020-04-02 09:20:40
 */
@Data
@TableName("about_us")
public class AboutUsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	@TableId
	private Integer id;
	/**
	 * 公司介绍
	 */
	private String introduce;
	/**
	 * 企业文化
	 */
	private String culture;
	/**
	 * 客户价值
	 */
	private String value;
	/**
	 * 公司风采
	 */
	private String appearance;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 联系人
	 */
	private String linkman;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 座机
	 */
	private String telephone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}

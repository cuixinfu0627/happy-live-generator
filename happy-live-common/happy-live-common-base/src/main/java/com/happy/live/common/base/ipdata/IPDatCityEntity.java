package com.happy.live.common.base.ipdata;
/**
 * 此类描述的是: IP工具类
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:31:22
 */
public class IPDatCityEntity {

	/**
	 * 国家
	 */
	public String country;
	
	/**
	 * 省  去掉 省 市 长于三个字截取前两个字
	 */
	public String province;
	
	/**
	 * 城市
	 */
	public String city;
	
	/**
	 * 区
	 */
	public String district;
	
	/**
	 * 服务商 
	 * 中海宽带 长城宽带 歌华 卫通 联通 赛尔宽带 方正宽带 有线通 油田宽带 
	 * 世纪互联 网宿 广电 盈联宽带 蓝波宽带 视通宽带 电信通 网通 长丰宽带 
	 * 铁通 华数宽带 电信 教育网 E家宽 移动 视讯宽带 航数宽网 移通
	 */
	public String isp;

	@Override
	public String toString() {
		return "国家:"+country+" 省:"+province+" 城市:"+city+" 地区:"+district+" 服务商:"+isp;
	}
	
}

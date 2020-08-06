package com.happy.live.common.base.ipdata;
/**
 * 此类描述的是:一条IP范围记录，不仅包括国家和区域，也包括起始IP和结束IP *
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:31:22
 */
public class IPDatEntry {
	
	public String beginIp;
	public String endIp;
	public String country;
	public String area;

	public IPDatEntry() {
		beginIp = endIp = country = area = "";
	}

	public String toString() {
		return this.area + "  " + this.country + "IP范围:" + this.beginIp + "-" + this.endIp;
	}
}
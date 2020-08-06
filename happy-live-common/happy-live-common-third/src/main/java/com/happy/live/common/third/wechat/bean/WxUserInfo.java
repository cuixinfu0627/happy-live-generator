package com.happy.live.common.third.wechat.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**类: WxUserInfo <br>  
 * 描述: 微信用户实体  <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间:2017年11月22日 下午4:27:08 <br>  
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WxUserInfo {
    private Integer userid;

    private String openid;

    private String unionid;

    private String nickname;

    private Integer sex;

    private String city;

    private String province;

    private String country;

    private String remark;

    private Integer groupid;

    private String language;

    private String headimgurl;

    private String tagidList;

    private Integer serviceType;

    private Integer status;

    private Date subscribeTime;

    private Date unsubscribeTime;

    private Date addedTime;
    
    private String moblie;
    
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getTagidList() {
        return tagidList;
    }

    public void setTagidList(String tagidList) {
        this.tagidList = tagidList == null ? null : tagidList.trim();
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getUnsubscribeTime() {
        return unsubscribeTime;
    }

    public void setUnsubscribeTime(Date unsubscribeTime) {
        this.unsubscribeTime = unsubscribeTime;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	@Override
	public String toString() {
		return "WxUserInfo [userid=" + userid + ", openid=" + openid + ", unionid=" + unionid + ", nickname=" + nickname
				+ ", sex=" + sex + ", city=" + city + ", province=" + province + ", country=" + country + ", remark="
				+ remark + ", groupid=" + groupid + ", language=" + language + ", headimgurl=" + headimgurl
				+ ", tagidList=" + tagidList + ", serviceType=" + serviceType + ", status=" + status
				+ ", subscribeTime=" + subscribeTime + ", unsubscribeTime=" + unsubscribeTime + ", addedTime="
				+ addedTime + ",moblie = " + moblie+ "]";
	}

	
}

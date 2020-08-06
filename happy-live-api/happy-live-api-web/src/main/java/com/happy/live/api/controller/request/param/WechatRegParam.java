package com.happy.live.api.controller.request.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @name: WechatLoginParam <tb>
 * @title: 小程序注册参数  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/4 14:07 <tb>
 */
public class WechatRegParam implements Serializable {
    private static final long serialVersionUID = 1L;
    // 用户的标识
    @JsonIgnore
    private String openid;
    // 用户的昵称(真实，sqlserver不能存储4字节，只能存字节)
    @ApiModelProperty(value = "表情", notes = "", required = true)
    private String nickName;
    // 用户头像，最后一个数值代表正方形头像大小
    @JsonIgnore
    private String avatarUrl;
    // 国家
    @JsonIgnore
    private String country;
    // 省
    @JsonIgnore
    private String province;
    // 城市
    @JsonIgnore
    private String city;
    // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    @JsonIgnore
    private String gender;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

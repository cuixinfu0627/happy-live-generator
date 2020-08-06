package com.happy.live.api.controller.request.param;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * @name: WechatLoginParam <tb>
 * @title: 小程序登录参数  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/7/4 14:07 <tb>
 */
public class WechatLoginParam {
    // 微信code
    @ApiModelProperty(value = "微信code", notes = "", required = true)
    private String code;
    // 包括敏感数据在内的完整用户信息的加密数据
    @ApiModelProperty(value = "包括敏感数据在内的完整用户信息的加密数据", notes = "", required = true)
    private String encryptedData;
    // 加密算法的初始向量
    @ApiModelProperty(value = "加密算法的初始向量", notes = "", required = true)
    private String iv;
    // 用户的标识
    @JsonIgnore
    private String openid;
    // 用户的昵称(真实，sqlserver不能存储4字节，只能存字节)
    @JsonIgnore
    private String nickName;
    // 用户的昵称(表情不能存储)
    @JsonIgnore
    private byte[] nickName_origin;
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
    // 公众号唯一UNIONID
    @JsonIgnore
    private String union_id;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
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

    public byte[] getNickName_origin() {
        return nickName_origin;
    }

    public void setNickName_origin(byte[] nickName_origin) {
        this.nickName_origin = nickName_origin;
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

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }
}

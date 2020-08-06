package com.happy.live.common.third.qq.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 类名:QQUserInfo <tb>
 * 描述:请输入类描述  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/6/25 12:58 <tb>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QQUserInfo {

    /** 级别 */
    private String ret;
    /** 用户在QQ空间的昵称。 */
    private String nickname;
    /** 性别。 如果获取不到则默认返回"男" */
    private String gender;
    /** qq头像 */
    private String figureurl_qq;
    /** 城市 */
    private String city;
    /** 省份 */
    private String province;
    /** 国家 */
    private String country;
    /** 星座 */
    private String constellation;
    /** 级别 */
    private Integer level;
    /** 登录唯一标识openid */
    private String openid;
    /** unionid */
    private String unionid;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFigureurl_qq() {
        return figureurl_qq;
    }

    public void setFigureurl_qq(String figureurl_qq) {
        this.figureurl_qq = figureurl_qq;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }


}

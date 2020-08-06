package com.happy.live.passport.entity.constant;

/**
 *@描述: 第登录来源
 *@创建人:cuixinfu@51play.com
 *@创建时间:2019/6/18 19:45
 */
public enum LoginLogTypeEnum {

    LOGIN_PC(1,"PC登录"),
    LOGIN_APP(2,"App登录");

    private Integer type;
    private String name;


    LoginLogTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static LoginLogTypeEnum getLoginName(Integer value){
        LoginLogTypeEnum[] thirdOriginTypes = LoginLogTypeEnum.values() ;
        for (LoginLogTypeEnum thirdOriginType : thirdOriginTypes) {
            if(thirdOriginType.getType() == value){
                return thirdOriginType ;
            }
        }
        return null ;
    }

}


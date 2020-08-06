package com.happy.live.passport.entity.constant;

/**
 *@描述: 第登录来源
 *@创建人:cuixinfu@51play.com
 *@创建时间:2019/6/18 19:45
 */
public enum LoginLogOperationTypeEnum {

    LOGIN(1,"登录"),
    LOGINOUT(2,"注销");

    private Integer type;
    private String name;


    LoginLogOperationTypeEnum(Integer type, String name) {
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

    public static LoginLogOperationTypeEnum getLoginLogOperationName(Integer value){
        LoginLogOperationTypeEnum[] thirdOriginTypes = LoginLogOperationTypeEnum.values() ;
        for (LoginLogOperationTypeEnum thirdOriginType : thirdOriginTypes) {
            if(thirdOriginType.getType() == value){
                return thirdOriginType ;
            }
        }
        return null ;
    }

}


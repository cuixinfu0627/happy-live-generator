package com.happy.live.passport.entity.constant;

/**
 *@描述: 第三方登录来源
 *@创建人:cuixinfu@51play.com
 *@创建时间:2019/6/18 19:45
 */
public enum ThirdOriginTypeEnum {

    THIRD_ORIGIN_WECHAT(1,"微信"),
    THIRD_ORIGIN_QQ(2,"QQ"),
    THIRD_ORIGIN_SINA(3,"微博");

    private int value;
    private String name;

    /** 使用中 **/
    public static final Byte DISABLE = 0;
    /** 停用 **/
    public static final Byte ENABLED  = 1;

    ThirdOriginTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ThirdOriginTypeEnum getThirdOriginTypebyvalue(int value){
        ThirdOriginTypeEnum[] thirdOriginTypes = ThirdOriginTypeEnum.values() ;
        for (ThirdOriginTypeEnum thirdOriginType : thirdOriginTypes) {
            if(thirdOriginType.getValue() == value){
                return thirdOriginType ;
            }
        }
        return null ;
    }

}


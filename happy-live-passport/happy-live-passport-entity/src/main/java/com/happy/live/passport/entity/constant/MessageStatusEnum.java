package com.happy.live.passport.entity.constant;

public enum MessageStatusEnum {
    /**
     * 未读
     * */
    UNREAD((byte)1, "未读"),

    /**
     * 已读
     * */
    READ((byte)2, "已读") ;


    private Byte value;
    private String name;

    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    MessageStatusEnum(byte value, String name) {
        this.value = value;
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserStatus{" +
            "value=" + value +
            ", name='" + name + '\'' +
            '}';
    }
}

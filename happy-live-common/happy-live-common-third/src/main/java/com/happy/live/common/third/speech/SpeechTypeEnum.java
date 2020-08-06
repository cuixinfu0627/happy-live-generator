package com.happy.live.common.third.speech;

/**
 * 语音合成类型
 * @author Administrator
 */
public enum SpeechTypeEnum {

    /**
     * 报警
     * */
    ALARM(1, "alarm"),
    /**
     * 故障
     * */
    FAULT(2, "fault"),

    /**
     * 隐患
     * */
    TROUBLE(3, "trouble"),

    /**
     * 隐患
     * */
    DANGER(4, "danger"),
    ;


    public static SpeechTypeEnum getSpeechType (int value) {
        for (SpeechTypeEnum speechTypeEnum: values()) {
            if (speechTypeEnum.value == value) {
                return speechTypeEnum;
            }
        }
        return null;
    }

    private int value;
    private String name;

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    SpeechTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AlarmLevel{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }
}

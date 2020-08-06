package com.happy.live.common.third.weather;

import java.util.HashMap;
import java.util.Map;

public class Weather {
    private String appKey ;

    private String secret ;

    private String apiUrl ;

    public final static  double D = 0.2422;
    public final static Map<String,Integer[]> INCREASE_OFFSETMAP = new HashMap<String, Integer[]>();//+1偏移
    public final static Map<String,Integer[]> DECREASE_OFFSETMAP = new HashMap<String, Integer[]>();//-1偏移

    /**24节气**/
    public enum SolarTermsEnum {
        LICHUN,//--立春
        YUSHUI,//--雨水
        JINGZHE,//--惊蛰
        CHUNFEN,//春分
        QINGMING,//清明
        GUYU,//谷雨
        LIXIA,//立夏
        XIAOMAN,//小满
        MANGZHONG,//芒种
        XIAZHI,//夏至
        XIAOSHU,//小暑
        DASHU,//大暑
        LIQIU,//立秋
        CHUSHU,//处暑
        BAILU,//白露
        QIUFEN,//秋分
        HANLU,//寒露
        SHUANGJIANG,//霜降
        LIDONG,//立冬
        XIAOXUE,//小雪
        DAXUE,//大雪
        DONGZHI,//冬至
        XIAOHAN,//小寒
        DAHAN;//大寒
    }

    static {
        DECREASE_OFFSETMAP.put(SolarTermsEnum.YUSHUI.name(), new Integer[]{2026});//雨水
        INCREASE_OFFSETMAP.put(SolarTermsEnum.CHUNFEN.name(), new Integer[]{2084});//春分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOMAN.name(), new Integer[]{2008});//小满
        INCREASE_OFFSETMAP.put(SolarTermsEnum.MANGZHONG.name(), new Integer[]{1902});//芒种
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAZHI.name(), new Integer[]{1928});//夏至
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOSHU.name(), new Integer[]{1925,2016});//小暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DASHU.name(), new Integer[]{1922});//大暑
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIQIU.name(), new Integer[]{2002});//立秋
        INCREASE_OFFSETMAP.put(SolarTermsEnum.BAILU.name(), new Integer[]{1927});//白露
        INCREASE_OFFSETMAP.put(SolarTermsEnum.QIUFEN.name(), new Integer[]{1942});//秋分
        INCREASE_OFFSETMAP.put(SolarTermsEnum.SHUANGJIANG.name(), new Integer[]{2089});//霜降
        INCREASE_OFFSETMAP.put(SolarTermsEnum.LIDONG.name(), new Integer[]{2089});//立冬
        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOXUE.name(), new Integer[]{1978});//小雪
        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAXUE.name(), new Integer[]{1954});//大雪
        DECREASE_OFFSETMAP.put(SolarTermsEnum.DONGZHI.name(), new Integer[]{1918,2021});//冬至

        INCREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(), new Integer[]{1982});//小寒
        DECREASE_OFFSETMAP.put(SolarTermsEnum.XIAOHAN.name(), new Integer[]{2019});//小寒

        INCREASE_OFFSETMAP.put(SolarTermsEnum.DAHAN.name(), new Integer[]{2082});//大寒
    }

    //定义一个二维数组，第一维数组存储的是20世纪的节气C值，第二维数组存储的是21世纪的节气C值,0到23个，依次代表立春、雨水...大寒节气的C值
    public static final double[][] CENTURY_ARRAY =
            {{4.6295,19.4599,6.3826,21.4155,5.59,20.888,6.318,21.86,6.5,22.2,7.928,23.65,8.35,
                    23.95,8.44,23.822,9.098,24.218,8.218,23.08,7.9,22.6,6.11,20.84}
                    ,{3.87,18.73,5.63,20.646,4.81,20.1,5.52,21.04,5.678,21.37,7.108,22.83,
                    7.5,23.13,7.646,23.042,8.318,23.438,7.438,22.36,7.18,21.94,5.4055,20.12}};


    public Weather(String appKey, String secret, String apiUrl) {
        this.appKey = appKey;
        this.secret = secret;
        this.apiUrl = apiUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}

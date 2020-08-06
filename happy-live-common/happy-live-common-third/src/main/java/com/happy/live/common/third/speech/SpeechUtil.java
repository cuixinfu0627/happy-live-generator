package com.happy.live.common.third.speech;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;

import java.util.HashMap;


/**
 * @program: SpeechUtil
 * @description: 文字转语音工具类
 * @author: Mr.Wang
 * @create: 2018-08-10 15:02
 **/
public class SpeechUtil {

    private static String appId;

    private static String appKey;

    private static String secretKey;

    public SpeechUtil(String appId, String appKey, String secretKey){
        this.appId = appId ;
        this.appKey = appKey ;
        this.secretKey = secretKey ;
    }

    /**
     *
     * @param content  合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
     * @param speed    语速，取值0-9，默认为5中语速
     * @param pit      音调，取值0-9，默认为5中语调
     * @param vol      音量，取值0-15，默认为5中音量
     * @param per      发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
     * @return
     */
    public static byte[] Speech(String content,Integer speed,Integer pit,Integer vol,Integer per) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(appId, appKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", speed);
        options.put("pit", pit);
        options.put("vol", vol);
        options.put("per", per);
        // 调用接口
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        byte[] data = res.getData();

        return data;

    }
    /**
     *
     * @param content  合成的文本，使用UTF-8编码，请注意文本长度必须小于1024字节
     * @return
     */
    public byte[] Speech(String content) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(appId, appKey, secretKey);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        // 调用接口
        TtsResponse res = client.synthesis(content, "zh", 1, options);
        byte[] data = res.getData();

        return data;

    }

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        SpeechUtil.appId = appId;
    }

    public static String getAppKey() {
        return appKey;
    }

    public static void setAppKey(String appKey) {
        SpeechUtil.appKey = appKey;
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secretKey) {
        SpeechUtil.secretKey = secretKey;
    }
}

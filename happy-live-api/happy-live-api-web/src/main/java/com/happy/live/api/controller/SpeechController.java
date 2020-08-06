package com.happy.live.api.controller;

import cn.hutool.json.JSONUtil;
import com.happy.live.api.config.SpeechConfig;
import com.happy.live.common.base.JSONHandler;
import com.happy.live.common.base.JSONResult;
import com.happy.live.common.base.http.HttpClient;
import com.happy.live.common.mvc.view.ResultVo;
import com.happy.live.common.mvc.view.ResultVoWrapper;
import com.happy.live.common.third.speech.SpeechTypeEnum;
import com.happy.live.common.third.speech.SpeechUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @name: SpeechController <tb>
 * @title: 语音合成demo  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/4/13 15:38<tb>
 */
@RestController
@RequestMapping("/speech")
public class SpeechController {
    private static final Logger logger = LoggerFactory.getLogger(SpeechController.class);

    private static final String SPEECH_ACCESS_TOKEN = "baidu_speech_access_token";

    public static final String uploadPath = "E:\\测试文件\\speech" ;

    @Autowired
    SpeechConfig speechConfig ;

    @RequestMapping("/test")
    public ResultVo Test(String keyword) {
        logger.info("SpeechController request revice params = {}" ,keyword);
        return new ResultVoWrapper().buildSuccess(keyword);
    }
    /**
     * 根据alarmId 查询报警信息生成音频语音文件
     *
     * @param speechType 读取内容类型 1.报警  2.故障 3.隐患 4.危险品
     * @param dataId     数据id
     * @param text    合成语音内容
     * @param speed      语速，取值0-9，默认为5中语速
     * @param pit        音调，取值0-9，默认为5中语调
     * @param vol        音量，取值0-15，默认为5中音量
     * @param per        发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
     */
    @RequestMapping("/getMessageAudio")
    public Object getMessageAudio(@RequestParam("type") Integer speechType,
                                @RequestParam("dataId") Long dataId,
                                @RequestParam("content") String text,
                                @RequestParam(value = "speed", defaultValue = "5") Integer speed,
                                @RequestParam(value = "pit", defaultValue = "5") Integer pit,
                                @RequestParam(value = "vol", defaultValue = "5") Integer vol,
                                @RequestParam(value = "per", defaultValue = "0") Integer per,
                                HttpServletResponse response) {
        if (dataId == null || dataId == 0) {
            return JSONResult.faild("数据id为空");
        }
        SpeechTypeEnum speechTypeEnum = SpeechTypeEnum.getSpeechType(speechType);
        if (speechTypeEnum == null) {
            return JSONResult.faild("合成语音类型不存在");
        }
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        SpeechUtil speechUtil = new SpeechUtil(speechConfig.getAppId(),speechConfig.getAppKey(),speechConfig.getSecretKey());
        byte[] data = speechUtil.Speech(text, speed, pit, vol, per);
        String path = uploadPath + File.separator + "a.mp3";
        logger.info("存放文件临时目录 = {}", path);
        OutputStream os = null;
        InputStream is = new ByteArrayInputStream(data);
        try {
            os = new FileOutputStream(path);
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = is.read(buff)) != -1) {
                os.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return JSONResult.success().addValue("mp3_url",path);
    }

    /**
     * 获取百度语音合成 token
     *
     * @return
     */
    @RequestMapping("getAccessToken")
    public Object getAccessToken() {
        //获取redis存放token
        String accessToken = "" ; //valueOperations.get(XfRedisKeys.SPEECH_ACCESS_TOKEN);
        if(StringUtils.isNotBlank(accessToken)){
            return JSONResult.success().addValue("accessToken",accessToken);
        }
        Map<String, String> param = new HashMap<>();
        param.put("client_id", speechConfig.getAppKey());
        param.put("client_secret", speechConfig.getSecretKey());
        long start = System.currentTimeMillis();
        String result = HttpClient.get(speechConfig.getOauthUrl(), param);
        long end = System.currentTimeMillis();
        logger.info("获取token时间 time : {},result:{}", end - start, result);
        if (StringUtils.isBlank(result)) {
            return JSONResult.faild();
        }
        Map<String, Object> map = (Map<String, Object>) JSONHandler.getObjectByJsonStr(result);
        String newAccessToken = (String) map.get("access_token");
        Double expires = (Double) map.get("expires_in");
        if (StringUtils.isBlank(newAccessToken)) {
            return JSONResult.faild();
        }
        //设置token到redis  设置过期时间
        //valueOperations.set(XfRedisKeys.SPEECH_ACCESS_TOKEN,newAccessToken,expires.longValue(), TimeUnit.SECONDS);
        logger.info("百度语音  设置token到redis value : {},expires:{}",newAccessToken,expires.longValue());
        return JSONResult.success().addValue("accessToken",newAccessToken);

    }
}

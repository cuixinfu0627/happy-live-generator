package com.happy.live.api.controller;

import com.happy.live.api.config.WeatherConfig;
import com.happy.live.common.mvc.view.ResultVo;
import com.happy.live.common.mvc.view.ResultVoWrapper;
import com.happy.live.common.third.weather.Weather;
import com.happy.live.common.third.weather.WeatherUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @name: WeatherController <tb>
 * @title: 天气管理  <tb>
 * @author: cuixinfu@51play.com <tb>
 * @date: 2020/4/13 15:38<tb>
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    WeatherConfig weatherConfig ;

    @RequestMapping("/test")
    public ResultVo Test(String keyword) {
        logger.info("WeatherController request revice params = {}" ,keyword);
        return new ResultVoWrapper().buildSuccess(keyword);
    }

    /**
     * 根据cityName 查询天气信息
     * @param cityName  城市名称
     */
    @RequestMapping("/queryWeather")
    public Object queryWeather(HttpServletResponse response,
                             @RequestParam("cityName") String cityName) {
        Weather weathertemp = new Weather(weatherConfig.getAppKey(),weatherConfig.getSecretKey(),weatherConfig.getApiUrl());
        Object weather = WeatherUtils.getWeacher(weathertemp, cityName);
        Object pm= WeatherUtils.getPM(weathertemp, cityName);
        Map<String,Object> result = new HashMap<>() ;
        result.put("weather",weather) ;
        result.put("pm",pm) ;
        return new ResultVoWrapper().buildSuccess(result);
    }
}

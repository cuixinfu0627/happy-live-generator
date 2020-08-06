package com.happy.live.api.config;

import com.happy.live.common.third.wechat.Configure;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 类:WeachtConfig.java <tb>
 * 描述： 初始化微信配置项 <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:20178年11月07日 下午13:01:15 <tb>
 */
@Component
@ConfigurationProperties(prefix = "weixin")
public class WeachtConfig {
    /** 小程序AppID **/
    private  String miniAppID ;
    /** 小程序AppSecret **/
    private  String miniAppSecret ;

    public String getMiniAppID() {
        return miniAppID;
    }
    public void setMiniAppID(String miniAppID) {
        this.miniAppID = miniAppID;
        Configure.setMiniAppID(miniAppID);
    }

    public String getMiniAppSecret() {
        return miniAppSecret;
    }
    public void setMiniAppSecret(String miniAppSecret) {
        this.miniAppSecret = miniAppSecret;
        Configure.setMiniAppSecret(miniAppSecret);
    }
}

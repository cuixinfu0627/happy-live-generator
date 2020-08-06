package com.happy.live.common.third.wechat.message;

import com.happy.live.common.third.HttpHandler;
import com.happy.live.common.third.wechat.constant.WeChatPlatform;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: MessageUtil <br>  
 * 描述: 微信消息处理工具类  <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年12月4日 下午2:35:38 <br>
 */
public class MessageUtil {
	
	/** 日志类处理类  **/
	private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);
	
	/**
	 * 此方法描述的是：   初始化模板消息
	 * @param message
	 * @param color
	 * @return HashMap<String,Object>
	 */
	public static HashMap<String,Object> initTemplateMessage(HashMap<String,Object> message,String color){
		HashMap<String, Object> templateMessage = new HashMap<String, Object>();
		for(String key:message.keySet()){
			HashMap<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("color", color);
			itemMap.put("value", message.get(key));
			templateMessage.put(key, itemMap);
		}
		System.out.println(templateMessage.toString());
		return templateMessage;
	}
	
	/**
	 * 此方法描述的是：发 客服消息 文本消息   
	 * @param content
	 * @param accessToken
	 * @param touser
	 * @return String
	 */
	public static String sendCustomMessage(String content,String accessToken,String touser){
		String result = null;
		String url = WeChatPlatform.CUSTOM_SEND.replace("ACCESS_TOKEN",accessToken);
		Map<String, Object> sceneMap = new HashMap<String, Object>();
		Map<String, String> strMap = new HashMap<String, String>();
		strMap.put("content", content);
		sceneMap.put("touser", touser);
		sceneMap.put("msgtype", "text");
		sceneMap.put("text", strMap); 
		String json = JSONObject.fromObject(sceneMap).toString();
		logger.info(json);
		JSONObject jsonObject = HttpHandler.doPostStr(url, json);
		if(jsonObject!=null){
			result = jsonObject.toString();
			logger.info(result);
		}
		return result;
	}
	
	/**
	 * 此方法描述的是：  发送客服消息-微信卡券消息  
	 * @param card_id
	 * @param accessToken
	 * @param touser
	 * @return String
	 */
    public static String sendCustomWxcardMessage(String card_id,String accessToken,String touser){
        String result = null;
        String url = WeChatPlatform.CUSTOM_SEND.replace("ACCESS_TOKEN",accessToken);
        Map<String, Object> sceneMap = new HashMap<String, Object>();
        Map<String, String> wxcard = new HashMap<String, String>();
        wxcard.put("card_id", card_id);
        sceneMap.put("touser", touser);
        sceneMap.put("msgtype", "wxcard");
        sceneMap.put("wxcard", wxcard); 
        String json = JSONObject.fromObject(sceneMap).toString();
        logger.info(json);
        JSONObject jsonObject = HttpHandler.doPostStr(url, json);
        if(jsonObject!=null){
            result = jsonObject.toString();
            logger.info(result);
        }
        return result;
    }
	/**
	 * 此方法描述的是：  发送模板消息 
	 * @param url
	 * @param template_id
	 * @param touser
	 * @param accessToken
	 * @param template
	 * @return String
	 */
	public static String sendTemplateMessage(String url,String template_id,String touser,String accessToken,HashMap<String,Object> template){
		String result = null;
		String postUrl = WeChatPlatform.TEMPLATE_SEND.replace("ACCESS_TOKEN",accessToken);
		Map<String, Object> post = new HashMap<String, Object>();
		post.put("touser", touser);
		post.put("template_id", template_id);
		post.put("url", url);
		post.put("data", template);
		String json = JSONObject.fromObject(post).toString();
		logger.info(json);
		JSONObject jsonObject = HttpHandler.doPostStr(postUrl, json);
		if(jsonObject!=null){
			result = jsonObject.toString();
			logger.info(result);
		}
		return result;
	}
	
	
}

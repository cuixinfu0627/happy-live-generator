package com.happy.live.common.third.wechat.message;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名: TextMessage <br>  
 * 描述: 微信文本消息处理 <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年12月4日 下午2:36:21 <br>
 */
public class TextMessage extends Message{
	
	/** 文本消息内容  { toparty："部门ID";totag:"部门id列表";text:{content:"消息内容"}} **/
	private Map<String ,Object> text ;    		
	
	public TextMessage() {
	}

	public TextMessage(Map<String, Object> text) {
		super();
		this.text = text;
	}

	public Map<String, Object> getText() {
		return text;
	}

	public void setText(Map<String, Object> text) {
		this.text = text;
	}

	public TextMessage(String touser, String toparty, String totag,
			String msgtype, String agentid, String safe,
			String text) {
		super(touser, msgtype, agentid, safe, totag, toparty);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("content", text);
		
		this.text = map;
	}
	
}

package com.happy.live.common.third.wechat.message;

/**
 * 类名: Message <br>  
 * 描述: 微信消息bean <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年12月4日 下午2:34:01 <br>
 */
public class Message {
	
	/** 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送  **/
	private String touser;  	
	/** 消息类型**/
	private String msgtype;    
	/**企业应用的id，整型。可在应用的设置页面查看  **/
	private String agentid;   
	/** 表示是否是保密消息，0表示否，1表示是，默认0 **/
	private String safe;
	/** 表示是否是保密消息，0表示否，1表示是，默认0  **/
	private String totag;
	/** 表示是否是保密消息，0表示否，1表示是，默认0  **/
	private String toparty;
	
	
	@Override
	public String toString() {
		return "Message [touser=" + touser + ", msgtype=" + msgtype
				+ ", agentid=" + agentid + ", safe=" + safe + ", totag="
				+ totag + ", toparty=" + toparty + "]";
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public Message() {
		super();
	}
	
	

	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getSafe() {
		return safe;
	}
	public void setSafe(String safe) {
		this.safe = safe;
	}

	/**
	 * @param touser
	 * @param msgtype
	 * @param agentid
	 * @param safe
	 * @param totag
	 * @param toparty
	 */
	public Message(String touser, String msgtype, String agentid, String safe,
			String totag, String toparty) {
		super();
		this.touser = touser;
		this.msgtype = msgtype;
		this.agentid = agentid;
		this.safe = safe;
		this.totag = totag;
		this.toparty = toparty;
	}
	
	
}

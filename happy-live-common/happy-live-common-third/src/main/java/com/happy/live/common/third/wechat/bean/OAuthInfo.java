package com.happy.live.common.third.wechat.bean;

    
/**   
 * 类名: OAuthInfo <br>  
 * 描述: OAuthInfo授权信息 <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年12月4日 下午2:33:36 <br>
 */
public class OAuthInfo {
	
	private String accessToken ;
	private int expiresIn ;
	private String refreshToken;
	private String openId ;
	private String scope ;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
	
}

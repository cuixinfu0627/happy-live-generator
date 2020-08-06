package com.happy.live.common.base.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * 此类描述的是: http处理工具
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:40:08
 */
public class HttpHandler {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(HttpHandler.class);
	@SuppressWarnings("deprecation")
	public static String httpPost(String postURL, List<NameValuePair> nameValuePairList, String requestBody) throws Exception {
		try {
			HttpClient httpclient = new HttpClient();
			  
			if(nameValuePairList != null && !nameValuePairList.isEmpty()){
				boolean isFirstFlag = true;
				for(NameValuePair nameValuePair : nameValuePairList){
					postURL = postURL +( isFirstFlag?"?":"&") + nameValuePair.getName() + "=" + nameValuePair.getValue();
					isFirstFlag = false;
				}
			}
			PostMethod postMethod = new PostMethod(postURL);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");  
			postMethod.setRequestHeader( "Content-Type", "text/html;charset=utf-8" );
			postMethod.setRequestBody(requestBody);
			int statusCode = httpclient.executeMethod(postMethod);
			String response = postMethod.getResponseBodyAsString();
			logger.info("statusCode: {}",statusCode);
			//logger.info("response: {}",response);
			postMethod.releaseConnection();
			return response;
		} catch (HttpException e) {
			logger.error("HttpException error!", e);
			throw new HttpException("HttpException error!", e);
		} catch (IOException e) {
			logger.error("IOException error!", e);
			throw new IOException("IOException error!", e);
		}
	}
	
	public static String httpGet(String getURL,  List<NameValuePair> nameValuePairList) throws Exception{
				
		try {
			HttpClient httpclient = new HttpClient();
//			httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
//			httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
//			Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);   
//			Protocol.registerProtocol("https", myhttps);
			if(nameValuePairList != null && !nameValuePairList.isEmpty()){
				boolean isFirstFlag = true;
				for(NameValuePair nameValuePair : nameValuePairList){
					getURL = getURL +( isFirstFlag?"?":"&") + nameValuePair.getName() + "=" + nameValuePair.getValue();
					isFirstFlag = false;
				}
			}
			logger.info("getURL:" + getURL);
			GetMethod getMethod = new GetMethod(getURL); 
			getMethod.setRequestHeader( "Content-Type", "text/html;charset=utf-8" );
			
			int statusCode = httpclient.executeMethod(getMethod);
			String response = getMethod.getResponseBodyAsString();
			logger.info("statusCode:" + statusCode + " response:" + response);
			getMethod.releaseConnection();
			return response;
		} catch (HttpException e) {
			logger.error("HttpException error!", e);
			throw new HttpException("HttpException error!", e);
		} catch (IOException e) {
			logger.error("IOException error!", e);
			throw new IOException("IOException error!", e);
		}
	}
}

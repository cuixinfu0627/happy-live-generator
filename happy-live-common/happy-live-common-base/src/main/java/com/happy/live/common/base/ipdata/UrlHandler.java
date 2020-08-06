package com.happy.live.common.base.ipdata;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 此类描述的是:Url处理工具类
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:24:22
 */
public class UrlHandler {

	public static String getResponseString(HttpMethod post) throws IOException {
		InputStream resStream = post.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
		StringBuffer resBuffer = new StringBuffer();
		String resTemp = "";
		while ((resTemp = br.readLine()) != null) {
			resBuffer.append(resTemp);
		}
		String response = resBuffer.toString();
		resStream.close();
		post.releaseConnection();
		return response;
	}
	
	public static String getResponseString(HttpMethod post,String charset) throws IOException {
		InputStream resStream = post.getResponseBodyAsStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(resStream,charset));
		StringBuffer resBuffer = new StringBuffer();
		String resTemp = "";
		while ((resTemp = br.readLine()) != null) {
			resBuffer.append(resTemp);
		}
		String response = resBuffer.toString();
		resStream.close();
		post.releaseConnection();
		return response;
	}
	
	public static String getUrlContentByGet(String url,int timeout){
		String res = null;
		GetMethod get = null;
		HttpClient client = null;
		try {
			get = new GetMethod(url);
			get.setDoAuthentication(true);
			client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			client.executeMethod(get);
			res = getResponseString(get);
		} catch (Exception e) {
			throw new RuntimeException("请求超时");
		}finally{
			if(null != client && null != get){
				get.releaseConnection();
			}
		}
		return res;
	}
	
	public static String getUrlContentByGet(String url,int timeout,String charset){
		String res = null;
		GetMethod get = null;
		HttpClient client = null;
		try {
			get = new GetMethod(url);
			get.setDoAuthentication(true);
			client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			client.executeMethod(get);
			res = getResponseString(get,charset);
		} catch (Exception e) {
			throw new RuntimeException("请求超时");
		}finally{
			if(null != client && null != get){
				get.releaseConnection();
			}
		}
		return res;
	}
	
	public static String getUrlContentByPost(String url,int timeout){
		String res = null;
		PostMethod post = null;
		HttpClient client = null;
		try {
			post = new PostMethod(url);
			post.setDoAuthentication(true);
			client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
			client.executeMethod(post);
			res = getResponseString(post);
		} catch (Exception e) {
			throw new RuntimeException("请求超时");
		}finally{
			if(null != client && null != post){
				post.releaseConnection();
			}
		}
		return res;
	}
	
	public static String getUrlRedirectCode(String url) throws Exception{
		String code = "";
		try{
			//获取URL对象
			URL u = new URL(url);
			//打开连接
			URLConnection rulConnection = u.openConnection();
			HttpURLConnection httpUrlConnection  =  (HttpURLConnection) rulConnection;
			//设置连接超时的时间 5000ms
			httpUrlConnection.setConnectTimeout(5000);
			httpUrlConnection.setReadTimeout(5000);
			httpUrlConnection.connect();
			code = new Integer(httpUrlConnection.getResponseCode()).toString();
		}catch(Exception e) {
			throw e;
		}
        return code;
	}
	
}

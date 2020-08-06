package com.happy.live.common.third;

import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * 此类描述的是: http处理工具
 * 
 * @author: cuixinfu@51play.com
 * @date:2017年11月21日 下午1:40:08
 */
@SuppressWarnings("deprecation")
public class HttpHandler {

	private static Logger logger = LoggerFactory.getLogger(HttpHandler.class);
	private static CloseableHttpClient httpClient = null;

	public static String httpPost(String postURL, List<NameValuePair> nameValuePairList, String requestBody)
			throws Exception {
		try {
			HttpClient httpclient = new HttpClient();

			if (nameValuePairList != null && !nameValuePairList.isEmpty()) {
				boolean isFirstFlag = true;
				for (NameValuePair nameValuePair : nameValuePairList) {
					postURL = postURL + (isFirstFlag ? "?" : "&") + nameValuePair.getName() + "="
							+ nameValuePair.getValue();
					isFirstFlag = false;
				}
			}
			PostMethod postMethod = new PostMethod(postURL);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
			postMethod.setRequestHeader("Content-Type", "text/html;charset=utf-8");
			postMethod.setRequestBody(requestBody);

			int statusCode = httpclient.executeMethod(postMethod);
			String response = postMethod.getResponseBodyAsString();
			logger.info("statusCode:" + statusCode + " response:" + response);
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

	public static String httpGet(String getURL, List<NameValuePair> nameValuePairList) throws Exception {

		try {
			HttpClient httpclient = new HttpClient();
			if (nameValuePairList != null && !nameValuePairList.isEmpty()) {
				boolean isFirstFlag = true;
				for (NameValuePair nameValuePair : nameValuePairList) {
					getURL = getURL + (isFirstFlag ? "?" : "&") + nameValuePair.getName() + "="
							+ nameValuePair.getValue();
					isFirstFlag = false;
				}
			}
			logger.info("getURL:" + getURL);
			GetMethod getMethod = new GetMethod(getURL);
			getMethod.setRequestHeader("Content-Type", "text/html;charset=utf-8");

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

	/**
	 * ===========================================调用处理==========================
	 * ==========================
	 **/
	public static JSONObject doGetStr(String url) {
		String result = doGetStr2(url);
		return JSONObject.fromObject(result);
	}

	public static final String doGetStr2(String url) {
		Long start = System.currentTimeMillis();
		CloseableHttpClient httpClient = null;
		try {
			httpClient = creatHttpClient();
		} catch (NoSuchAlgorithmException e1) {
			logger.error("请求出错：url={},{}" , url, e1);
		}
		if (httpClient == null) {
			return null;
		}
		HttpGet httpGet = new HttpGet(url);
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				logger.info("单例httpClient请求时长：{},{}", (System.currentTimeMillis() - start),url);
				return EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			logger.error("请求出错：url={},{}",url, e);
		}
		return null;
	}

	/**
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static CloseableHttpClient creatHttpClient() throws NoSuchAlgorithmException {
		if(httpClient == null){
			LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cm.setMaxTotal(200);
			cm.setDefaultMaxPerRoute(20);
			httpClient = HttpClients.custom().setConnectionManager(cm).build();
		}
		
		return httpClient;
	}

	/**
	 * @param url
	 * @param outStr
	 * @return
	 * @Description: post请求
	 * @author wangbing
	 * @Since:2015年8月17日
	 */
	@SuppressWarnings("resource")
	public static JSONObject doPostStr(String url, String outStr) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static void main(String[] args) {
		String aaa = doGetStr2("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx22972e1527274740&secret=2ee86ef088543b194c77be06de761566&code=061vFh970IqpzG1FUW870deu970vFh9e&grant_type=authorization_code");
		String aaa2 = doGetStr2("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx22972e1527274740&secret=2ee86ef088543b194c77be06de761566&code=061vFh970IqpzG1FUW870deu970vFh9e&grant_type=authorization_code");
		String aaa21= doGetStr2("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx22972e1527274740&secret=2ee86ef088543b194c77be06de761566&code=061vFh970IqpzG1FUW870deu970vFh9e&grant_type=authorization_code");
		String aaa3 = doGetStr2("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx22972e1527274740&secret=2ee86ef088543b194c77be06de761566&code=061vFh970IqpzG1FUW870deu970vFh9e&grant_type=authorization_code");
		String aaa4 = doGetStr2("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx22972e1527274740&secret=2ee86ef088543b194c77be06de761566&code=061vFh970IqpzG1FUW870deu970vFh9e&grant_type=authorization_code");
	}
}

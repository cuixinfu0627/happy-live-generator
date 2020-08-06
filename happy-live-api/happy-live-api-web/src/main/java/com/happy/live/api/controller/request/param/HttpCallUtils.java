package com.happy.live.api.controller.request.param;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCallUtils {
	
	private static Log log = LogFactory.getLog(HttpCallUtils.class);
	private static final int HTTP_TIMEOUT = 1000 * 60;
	private static String cookie;
	
	public static JSONObject httpCall(String httpUrl,byte[] bpost){
		JSONObject jsonObj = null;
		byte bb[] = null;
		HttpURLConnection urlconn = null;
		InputStream in = null;
		try {
			System.out.println("*****"+httpUrl);
			URL url = new URL(httpUrl);
			urlconn = (HttpURLConnection)url.openConnection();
			urlconn.setRequestMethod("POST");
			urlconn.setDoOutput(true);
			urlconn.setConnectTimeout(HTTP_TIMEOUT);
			urlconn.setReadTimeout(HTTP_TIMEOUT);
			urlconn.setRequestProperty("Content-Type", "application/json");
			if(cookie != null) urlconn.setRequestProperty("Cookie", cookie);
			urlconn.connect();
			if(bpost!=null){
				OutputStream os = urlconn.getOutputStream();
				os.write(bpost);
				os.close();
			}
			int code = urlconn.getResponseCode();
			System.out.println("***code:"+code);
			if (code == 200) {
				in = urlconn.getInputStream();
			}else{
				in = urlconn.getErrorStream();
			}
			System.out.println("***in:"+in);
			if(in == null) return null;
			bb = FileUtils.readbodydata(in, 0);
			String str = new String(bb,"utf-8");
			String tmpStr = str;
			if(str.length() > 1024 * 10) tmpStr = str.substring(0, 1024 * 10);
			log.debug(tmpStr);
			System.out.println("----str:"+str);
			jsonObj = JSON.parseObject(str);
		}catch (Exception e) {
			log.error(e.getMessage());
			jsonObj = new JSONObject();
			jsonObj.put("id", -2);
			jsonObj.put("info", "服务器异常,请联系管理员 !");
		} finally{
			if(null != urlconn) urlconn.disconnect();
			if(null != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return jsonObj;
	}
	
	/*public static JSONObject httpQrCodeCall(String httpUrl,byte[] bpost,String path,String fileName){
		JSONObject jsonObj = null;
		HttpURLConnection urlconn = null;
		InputStream in = null;
		try {
			System.out.println("*****"+httpUrl);
			URL url = new URL(httpUrl);
			urlconn = (HttpURLConnection)url.openConnection();
			urlconn.setRequestMethod("POST");
			urlconn.setDoOutput(true);
			urlconn.setConnectTimeout(HTTP_TIMEOUT);
			urlconn.setReadTimeout(HTTP_TIMEOUT);
			urlconn.setRequestProperty("Content-Type", "application/json");
			if(cookie != null) urlconn.setRequestProperty("Cookie", cookie);
			urlconn.connect();
			if(bpost!=null){
				OutputStream os = urlconn.getOutputStream();
				os.write(bpost);
				os.close();
			}
			int code = urlconn.getResponseCode();
			System.out.println("***code:"+code);
			if (code == 200) {
				in = urlconn.getInputStream();
			}else{
				in = urlconn.getErrorStream();
			}
			System.out.println("***in:"+in);
			if(in == null) return null;
        	File targetFile = new File(path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
            OutputStream out = new FileOutputStream(new File(path+File.separator+fileName));  
            int read = 0;  
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {  
                out.write(bytes, 0, read);  
            }
            out.flush();  
            out.close();
            jsonObj = new JSONObject();
            jsonObj.put("url","https://wx.jiaopb.com/taekwondosysws/swagger/activity/qrCodePic/"+fileName);
		}catch (Exception e) {
			log.error(e.getMessage());
			jsonObj = new JSONObject();
			jsonObj.put("id", -2);
			jsonObj.put("info", "服务器异常,请联系管理员 !");
		} finally{
			if(null != urlconn) urlconn.disconnect();
			if(null != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return jsonObj;
	}*/
	
	public static JSONObject httpQrCodeCall(String httpUrl,String params,String path,String fileName){
		JSONObject jsonObj = null;
		HttpURLConnection urlconn = null;
		InputStream in = null;
		try {
			System.out.println("*****"+httpUrl);
			URL url = new URL(httpUrl);
			urlconn = (HttpURLConnection)url.openConnection();
			urlconn.setRequestMethod("POST");
			urlconn.setDoOutput(true);
			urlconn.setDoInput(true);// 设置是否从httpUrlConnection读入，默认情况下是true;
			urlconn.setConnectTimeout(HTTP_TIMEOUT);
			urlconn.setReadTimeout(HTTP_TIMEOUT);
			urlconn.setRequestProperty("Content-Type", "application/json");
			if(cookie != null) urlconn.setRequestProperty("Cookie", cookie);
			urlconn.connect();
			if(params!=null){
				OutputStream os = urlconn.getOutputStream();
				os.write(params.getBytes("UTF-8"));
				os.close();
			}
			int code = urlconn.getResponseCode();
			System.out.println("***code:"+code);
			if (code == 200) {
				in = urlconn.getInputStream();
			}else{
				in = urlconn.getErrorStream();
			}
			System.out.println("***in:"+in);
			if(in == null) return null;
        	File targetFile = new File(path);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
            OutputStream out = new FileOutputStream(new File(path,fileName));  
            int read = 0;  
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {  
                out.write(bytes, 0, read);  
            }
            out.flush();  
            out.close();
            jsonObj = new JSONObject();
            jsonObj.put("url","https://device.77lock.com/upload/qrCode/"+fileName);
		}catch (Exception e) {
			log.error(e.getMessage());
			jsonObj = new JSONObject();
			jsonObj.put("id", -2);
			jsonObj.put("info", "服务器异常,请联系管理员 !");
		} finally{
			if(null != urlconn) urlconn.disconnect();
			if(null != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return jsonObj;
	}
	
	// 通过get请求得到读取器响应数据的数据流
    public static InputStream getInputStreamByGet(String url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                return inputStream;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 将服务器响应的数据流存到本地文件
    public static void saveData(InputStream is, File file) {
        try (BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(
                        new FileOutputStream(file));) {
            byte[] buffer = new byte[1024];
            int len = -1;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}

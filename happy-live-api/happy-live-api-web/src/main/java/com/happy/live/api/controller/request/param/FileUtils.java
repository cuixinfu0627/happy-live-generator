package com.happy.live.api.controller.request.param;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * I/O文件操作函数
 */
public class FileUtils {

	protected static Log log = LogFactory.getLog(FileUtils.class);

	private static final int HTTP_TIMEOUT = 1000 * 3;
	
	
	//获取文件扩展名
	/*public static String getExtName(String fileName){
		String ext = CommonUtils.regx(fileName, "(\\.[^\\.]+)$");
		if(ext == null) ext = "";
		return ext.toLowerCase();
	}*/
	
	public static byte[] readData(String path){
		File f = new File(path);
		byte[] b = null;
		try {
			FileInputStream fin = new FileInputStream(f);
			b = new byte[fin.available()];
			fin.read(b);
			fin.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return b;
	}
	
	public static String readDataStr(String path,String encode){
		if(encode == null) encode = "utf-8";
		try {
			return new String(readData(path),encode);
		} catch (UnsupportedEncodingException e) {
			log.debug(e.getMessage());
		}
		return "";
	}
	
	
	
	static public byte[] getHtml4Byte(String httpurl, byte b[]) {
		byte bb[] = null;
		try {
			URL url = new URL(httpurl);
			HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
			if(b == null){
				urlconn.setRequestMethod("GET");
			}else{
				urlconn.setRequestMethod("POST");
				urlconn.setDoOutput(true);
			}
			urlconn.setConnectTimeout(HTTP_TIMEOUT);
			urlconn.setReadTimeout(HTTP_TIMEOUT);
			urlconn.setRequestProperty("Content-Type", "application/xml");
			urlconn.connect();
			if(b != null){
				OutputStream os = urlconn.getOutputStream();
				os.write(b);
				os.close();
			}
			InputStream in = null;
			int code = urlconn.getResponseCode();
			if (code == 200) {
				in = urlconn.getInputStream();
			}else{
				in = urlconn.getErrorStream();
			}
			if(in != null){
				bb = readbodydata(in, 0);
				in.close();
			}
			urlconn.disconnect();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bb;
	}
	
	
	/*
	 * read response body
	 */
	static public byte[] readbodydata(InputStream in, int size) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		final int DEFAULT_SIZE = 8192;
		try {
			if (size == 0)  size = DEFAULT_SIZE;
			byte[]  buf = new byte[size];
			int len = 0;
			while ((len = in.read(buf)) > 0) {
			    bout.write(buf, 0, len);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != in) in.close();
			} catch (IOException e) {}
		}
		return bout.toByteArray();
	}
	
	
	public static boolean writeFile(String path,byte[] body){
		File file = new File(path);
		File pfile =file.getParentFile();
		if(pfile.exists() == false)
			pfile.mkdirs();
		boolean ret = true;
		try {
			file.createNewFile();
			OutputStream out = new FileOutputStream(file);
			out.write(body);
			out.close();
		} catch (IOException e) {
			//e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	public static boolean writeFile(String path,String body){
		try {
			return writeFile(path, body.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
}

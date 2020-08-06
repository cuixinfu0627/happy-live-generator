package com.happy.live.common.base;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 类名: URLEncodeHandler <br>  
 * 描述: 把URL 编码或是解码 <br>
 * 作者: cuixinfu@ralncy.com <br>
 * 时间: 2017年12月1日 下午4:06:40 <br>
 */
public class URLEncodeHandler {

   /**
    * 方法名：urlEncodeUTF8</br>
    * 详述： URL 编码  </br>
    * 开发人员：souvc </br>
    * 创建时间：2015-11-30  </br>
    * @param URL
    * @return
    * @throws
    */
    public static String urlEncodeUTF8(String URL) {
        String result = URL;
        try {
            result = URLEncoder.encode(URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
    * 方法名：urlDecodeUTF8</br>
    * 详述：URL 解码 </br>
    * 开发人员：souvc </br>
    * 创建时间：2015-11-30  </br>
    * @param URL
    * @return
    * @throws
     */
    public static String urlDecodeUTF8(String URL) {
        String result = "";
        try {
            result = URLDecoder.decode(URL, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
    * 方法名：urlDecodeGb2312</br>
    * 详述：URL 解码</br>
    * 开发人员：souvc  </br>
    * 创建时间：2015-11-30  </br>
    * @param URL
    * @return
    * @throws
     */
    public static String urlDecodeGb2312(String URL) {
        String result = "";
        try {
            result = URLDecoder.decode(URL, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    /**
    * 方法名：urlEncodeGb2312</br>
    * 详述：URL 编码  </br>
    * 开发人员：souvc  </br>
    * 创建时间：2015-11-30  </br>
    * @param URL
    * @return
    * @throws
     */
    public static String urlEncodeGb2312(String URL) {
        String result = URL;
        try {
            result = URLEncoder.encode(URL, "gb2312");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    /** 对字符串进行编码.
	 * 
	 * @param source
	 * @return */
	public static String escape(String source) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(source.length() * 6);
		for (i = 0; i < source.length(); i++) {
			j = source.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j)) {
				tmp.append(j);
			}
			else if (j < 256) {
				tmp.append("%");
				if (j < 16) {
					tmp.append("0");
				}
				tmp.append(Integer.toString(j, 16));
			}
			else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}
	
	/** 对字符串进行解码.
	 * 
	 * @param source
	 * @return */
	public static String unescape(String source) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(source.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < source.length()) {
			pos = source.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (source.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(source.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				}
				else {
					ch = (char) Integer.parseInt(source.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			}
			else {
				if (pos == -1) {
					tmp.append(source.substring(lastPos));
					lastPos = source.length();
				}
				else {
					tmp.append(source.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
    public static void main(String[] args) {
        String URL ="http://www.souvc.com/oauthServlet";
        System.out.println(urlEncodeUTF8(URL));
        System.out.println(urlEncodeGb2312(URL));
        System.out.println(urlDecodeUTF8("http%3A%2F%2Fwww.souvc.com%2FoauthServlet"));
        System.out.println(urlDecodeGb2312("http%3A%2F%2Fwww.souvc.com%2FoauthServlet"));
    }
}
package com.happy.live.common.base.http;

    
/**   
 * 类:Sha1Util.java <tb>
 * 描述：  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2017年12月22日 下午1:27:02 <tb>
 */
/** 
 * 2016年1月18日 
 * Chatpay 
 *  
 */


import java.security.MessageDigest;
import java.util.*;

/* 
'============================================================================ 
'api说明： 
'createSHA1Sign创建签名SHA1 
'getSha1()Sha1签名 
'============================================================================ 
'*/  
public class Sha1Handler {  
  
    //微信公众号开发的随机字串  
    public static String getNonceStr() {  
        Random random = new Random();  
        return MD5Handler.getMD5Str(String.valueOf(random.nextInt(10000)));  
    }  
    //微信公众号开发的获取时间戳  
    public static String getTimeStamp() {  
        return String.valueOf(System.currentTimeMillis() / 1000);  
    }  
      
   //创建签名SHA1  
    @SuppressWarnings("rawtypes")
	public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {  
        StringBuffer sb = new StringBuffer();  
        Set es = signParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            sb.append(k + "=" + v + "&");  
            //要采用URLENCODER的原始值！  
        }  
        String params = sb.substring(0, sb.lastIndexOf("&"));  
  
        return getSha1(params);  
    }  
    //Sha1签名  
    public static String getSha1(String str) {  
        if (str == null || str.length() == 0) {  
            return null;  
        }  
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',  
                'a', 'b', 'c', 'd', 'e', 'f' };  
  
        try {  
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");  
            mdTemp.update(str.getBytes("UTF-8"));  
  
            byte[] md = mdTemp.digest();  
            int j = md.length;  
            char buf[] = new char[j * 2];  
            int k = 0;  
            for (int i = 0; i < j; i++) {  
                byte byte0 = md[i];  
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];  
                buf[k++] = hexDigits[byte0 & 0xf];  
            }  
            return new String(buf);  
        } catch (Exception e) {  
            return null;  
        }  
    }  
}  

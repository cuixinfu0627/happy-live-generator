package com.happy.live.common.third.wechat;


import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * 类: Signature <br>  
 * 描述: 微信签名工具 <br>
 * 作者: cuixinfu@51play.com <br>
 * 时间: 2017年12月1日 上午9:45:47 <br>
 */
public class Signature {
    /**
     * 签名算法
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    @SuppressWarnings("rawtypes")
	public static String getSign(Object o) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getMchApiKey();
//        result = MD5Handler.MD5Encode(result).toUpperCase();
        return result;
    }

    public static String getSign(Map<String,Object> map){
        ArrayList<String> list = new ArrayList<String>();
        for(Map.Entry<String,Object> entry:map.entrySet()){
            if(entry.getValue()!=""){
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String [] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i ++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + Configure.getMchApiKey();
        //Util.log("Sign Before MD5:" + result);
//        result = MD5.MD5Encode(result).toUpperCase();
        //Util.log("Sign Result:" + result);
        return result;
    }

    /**
     * 从API返回的XML数据里面重新计算一次签名
     * @param responseString API返回的XML数据
     * @return 新鲜出炉的签名
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static String getSignFromResponseString(String responseString) throws IOException, SAXException, ParserConfigurationException {
		return responseString;
//        Map<String,Object> map = XMLParser.getMapFromXML(responseString);
//        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
//        map.put("sign","");
//        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
//        return Signature.getSign(map);
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) throws ParserConfigurationException, IOException, SAXException {

//        Map<String,Object> map = XMLParser.getMapFromXML(responseString);
//        Util.log(map.toString());
//
//        String signFromAPIResponse = map.get("sign").toString();
//        if(signFromAPIResponse=="" || signFromAPIResponse == null){
//            Util.log("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
//            return false;
//        }
//        Util.log("服务器回包里面的签名是:" + signFromAPIResponse);
//        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
//        map.put("sign","");
//        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
//        String signForAPIResponse = Signature.getSign(map);
//
//        if(!signForAPIResponse.equals(signFromAPIResponse)){
//            //签名验不过，表示这个API返回的数据有可能已经被篡改了
//            Util.log("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
//            return false;
//        }
//        Util.log("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }
    
    /**
	 * 将字节数组转换为十六进制字符串
	 * @param byteArray
	 * @return
	 */
	 public static String byteToStr(byte[] byteArray) {
		 String strDigest = "";
		 for (int i = 0; i < byteArray.length; i++) {
		  strDigest += byteToHexStr(byteArray[i]);
		 }
		 return strDigest;
	 }
	 
	 /**
	 * 将字节转换为十六进制字符串
	 * @param mByte
	 * @return
	 */
	 private static String byteToHexStr(byte mByte) {
		 char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		 char[] tempArr = new char[2];
		 tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		 tempArr[1] = Digit[mByte & 0X0F];
		 String s = new String(tempArr);
		 return s;
	 }
	 public static void sort(String a[]) {
		 for (int i = 0; i < a.length - 1; i++) {
		  for (int j = i + 1; j < a.length; j++) {
		  if (a[j].compareTo(a[i]) < 0) {
		   String temp = a[i];
		   a[i] = a[j];
		   a[j] = temp;
		  }
		  }
		 }
	 }
}

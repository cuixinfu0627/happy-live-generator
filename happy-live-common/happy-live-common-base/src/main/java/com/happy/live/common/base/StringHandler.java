package com.happy.live.common.base;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类描述的是:字符串工具
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 上午11:57:14
 */
public class StringHandler {
	
	/**
	 * 字符串是否为数字
	 * @return true为数字
	 */
	public static boolean isNumber(String str) {
		if(isEmpty(str)){
			return false;
		}
		String regex;
		int index = str.indexOf(",");
		// 有逗号等分隔符的数字
		if (index >= 0) {
			regex = "[0-9]+[0-9]*(,[0-9]{3})+(\\.[0-9]+)?";
		} else {
			regex = "[0-9]+[0-9]*(\\.[0-9]+)?";
		}
		return str.matches(regex);
	}
	
	/**
	 * 把date转换成一个时间戳字符串
	 * 格式：yyyyMMddHHmmssSSS
	 */
	public static String date2long(Date date){
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
	}
	
	/**
	 * 对象为空(String Map List Set为空)
	 * @return true为空
	 */
	public static boolean isEmpty(Object object) {
		if (null == object) {
			return true;
		}
		if(object instanceof String){
			return "".equals(((String) object).trim());
		}
		else if(object instanceof Map){
			return ((Map<?, ?>) object).isEmpty();
		}
		else if(object instanceof Collection){
			return ((Collection<?>) object).isEmpty();
		}
		return false;
	}
	
	/**
	 * 对象非空(String Map List Set非空)
	 * @return true为非空
	 */
	public static boolean isNotEmpty(Object object) {
		return !isEmpty(object);
	}

	/**
	 * 转换错误栈为为字符串。
	 */
	public static String getExceptionStack(Throwable e) {
		if (isEmpty(e)) return "";
		OutputStream ou = new ByteArrayOutputStream();
		PrintStream o = new PrintStream(ou);
		e.printStackTrace(o);
		return ou.toString();
	}

	/**
	 * 验证邮箱地址是否正确
	 */
	public static boolean isEmail(String email) {
		try {
			String check = "^([a-z0-9A-Z]+[_|-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			return Pattern.compile(check).matcher(email).matches();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 验证手机号码
	 */
	public static boolean isMobile(String mobile) {
		try {
			String regex =  "^1(3[0-9]|4[57]|5[0-35-9]|7[0-9]|8[0-9])\\d{8}$";
			return Pattern.compile(regex).matcher(mobile).matches();
		} catch (Exception e) {
			return false;
		}
	}
	
	/** 
	 * 判断是否为合法IP 
	 * @return the ip 
	 */  
	public static boolean isIp(String ipAddress) {  
       try {
    	   String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		   return Pattern.compile(ip).matcher(ipAddress).find();
       } catch (Exception e) {
    	   return false;
       }   
	}

	/**
	 * 非空返回String值 空或出错返回默认值
	 */
	public static String getString(Object obj, Object defStr) {
		return isEmpty(obj)?String.valueOf(defStr):String.valueOf(obj);
	}

	/**
	 * 非空返回long值 空或出错返回零
	 */
	public static long getLong(Object obj) {
		return getLong(obj, 0);
	}

	/**
	 * 非空返回long值 空或出错返回默认值
	 */
	public static long getLong(Object obj, long defArg) {
		try {
			if(obj instanceof Number){
				return ((Number)obj).longValue();
			}
			return Long.parseLong(getString(obj, defArg));
		} catch (NumberFormatException e) {
			return defArg;
		}
	}

	/**
	 * 非空返回int值 空或出错返回零
	 */
	public static int getInt(Object obj) {
		return getInt(obj, 0);
	}

	/**
	 * 非空返回int值 空或出错返回默认值
	 */
	public static int getInt(Object obj, int defArg) {
		try {
			if(obj instanceof Number){
				return ((Number)obj).intValue();
			}
			return Integer.parseInt(getString(obj, defArg));
		} catch (NumberFormatException e) {
			return defArg;
		}
	}
	
	/**
	 * 非空返回double值 空或出错返回零
	 */
	public static double getDouble(Object obj) {
		return getDouble(obj, 0);
	}

	/**
	 * 非空返回double值 空或出错返回默认值
	 */
	public static double getDouble(Object obj, int defArg) {
		try {
			if(obj instanceof Number){
				return ((Number)obj).doubleValue();
			}
			return Double.parseDouble(getString(obj, defArg));
		} catch (NumberFormatException e) {
			return defArg;
		}
	}
	
	public static float getFloat(Object obj, float defArg) {
		try {
			if(obj instanceof Number){
				return ((Number)obj).floatValue();
			}
			return Float.parseFloat(getString(obj, defArg));
		} catch (NumberFormatException e) {
			return defArg;
		}
	}

	/**
	 * 
	 * @param input 被分割的字符串
	 * @param regix 分隔符 如 ","
	 * @return Set<Integer>
	 */
	public static Set<Integer> splitToInteger(String input,String regix){
		Set<Integer> set = null;
		if(null != input && null != regix){
			String[] subs = input.split(regix);
			if(null != subs){
				set = new HashSet<Integer>();
				for(int i=0; i< subs.length; i++){
					set.add(Integer.parseInt(subs[i]));
				}
			}
		}
		return set;
	}
	
	/**
	 * 
	 * @param input 被分割的字符串
	 * @param regix 分隔符 如 ","
	 * @return int[]
	 */
	public static int[] splitToIntArray(String input,String regix){
		int[] st = null;
		if(null != input && null != regix){
			String[] subs = input.split(regix);
			if(null != subs){
				st = new int[subs.length];
				for(int i=0; i< subs.length; i++){
					st[i] = Integer.parseInt(subs[i]);
				}
			}
		}
		return st;
	}
	
	/**
	 * 检测sql字符串类型参数是否存在sql注入
	 * @param pam
	 * @return boolean
	 */
	public static boolean isSqlInjection(String pam){
		pam = pam.trim().replaceAll(".*([';]+|(--)+).", "");
		String inj_str = "'|exec|insert|select|delete|update|count|*|%|mid|master|truncate|declare|;|-|+|,|=";  
		String inj_stra[] = inj_str.split("|");  
		for(int i=0;i<inj_str.length();i++){
			if(pam.indexOf(inj_stra[i]) > 0){
				return true;
			}
		}
		return false;
	}
	
	/**获取干净的json字符串：不包含注释、换行、忽略js文件中var开头的部分<br/>
	 * <b>配置文件中的json字符串可能存在注释等与json无关的字符，该方法主要目的就是去除这些无关字符</b>
	 * @param jsonStr
	 * @return
	 */
	public static String getPureJsonStr(String jsonStr){
		if(isEmpty(jsonStr)){
			return jsonStr;
		}
		char[] cs = jsonStr.toCharArray();
		int begin = 0;
		//忽略开头处的注释、空格、换行等
		outer:
		for(int i=begin;i<cs.length;i++){
			switch (cs[i]) {
				case ' ':
				case '\t':
				case '\n':
				case '\r':
					continue;
				case '/' :
					i = getCommentEnd(i+1, cs);
					break;
				default:
					begin = i;
					break outer;
			}
		}
		//忽略非{和[开头的部分
		for(;begin<cs.length&&(cs[begin]!='{'&&cs[begin]!='[');begin++){}
		
		//提取json字符串
		StringBuilder sb = new StringBuilder(cs.length-begin);
		for(int i=begin;i<cs.length;i++){
			switch (cs[i]) {
				case ' ' :
				case '\t':
				case '\n':
				case '\r':
					continue;
				case '/' :
					int next = i+1;
					i = getCommentEnd(next, cs);
					if(next==i){
						sb.append('/').append(cs[i]);
					}
					break;
				case '\'':
				case '"':
					i = appendString(i, cs, sb);
					break;
				default:
					sb.append(cs[i]);
					break;
			}
		}
		return sb.toString();
	}
	
	private static int appendString(int index,char[] cs,StringBuilder sb){
		char c = cs[index];
		char quote = c;
		char prev = c;
		outer:
		while(true){
			while((c=cs[++index])!=quote){
				sb.append(prev);
				prev = c;
			}
			sb.append(prev);
			if(prev!='\\'){
				sb.append(c);
				break outer;
			}
			prev = c;
		}
		return index;
	}
	
	private static int getCommentEnd(int next,char[] cs){
		char c = cs[next];
		switch (c) {
			case '/'://单行注释
				while(cs[++next]!='\n'){}
				break;
			case '*'://多行注释
				char prev = c;
				outer:
				while(true){
					while((c=cs[++next])!='/'){
						prev = c;
					}
					if(prev=='*'){
						break outer;
					}
				}
			default:
				break;
		}
		return next;
	}
	
	/**判断异常是否为主键冲突
	 * @param cause
	 * @return
	 */
	public static boolean isPKConflict(Throwable cause){
		int count = 20;
		while(cause!=null&&count-->0){
			if(cause instanceof SQLException && cause.getMessage().indexOf("PRIMARY KEY")>-1){
				return true;
			}
			cause = cause.getCause();
		}
		return false;
	}
	
	/**
	 * 方法: fullToHalfChange <br>
	 * 描述: 全角转换半角 <br>
	 * 作者: hailong@xiu8.com<br>
	 * 时间: 2014年11月12日 下午3:01:19
	 */
	public static String fullToHalfChange(String str) {
		char c[] = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		return new String(c);
	}
	
	/**
	 * 检查是否包含域名后缀
	 * @param input 查询字符
	 */
	public static boolean checkDomain(String input) {
		String regDomain = "";
		if ("".equals(regDomain)) {
			for (int i = 0; i < domain.length; i++) {
				if (regDomain == "") {
					regDomain += "\\." + domain[i];
					regDomain += "|。" + domain[i];
					regDomain += "|点" + domain[i];
					regDomain += "|點" + domain[i];
				} else {
					regDomain += "|\\." + domain[i];
					regDomain += "|。" + domain[i];
					regDomain += "|点" + domain[i];
					regDomain += "|點" + domain[i];
				}
			}
		}
		Pattern pattern = Pattern.compile(regDomain);
		String lowerCase = fullToHalfChange(input).toLowerCase();
		//替换俄文混淆字母
		lowerCase = lowerCase.replaceAll("с", "c").replaceAll("ь", "b").replaceAll("а", "a").replaceAll("о", "o");
		//替换拉丁文混淆字母
		lowerCase = lowerCase.replaceAll("ο", "o");
		//替换韩文混淆字母
		lowerCase = lowerCase.replaceAll("ㅇ", "o");
		//替换数字0
		lowerCase = lowerCase.replaceAll("0", "o");
		//替换符号〇
		lowerCase = lowerCase.replaceAll("〇", "o");
		Matcher matcher = pattern.matcher(lowerCase);
		return matcher.find();
	}
	
	/**
	 * 方法: dangerInput <br>
	 * 描述: 检测非法字符  包含关键字和域名后缀、全数字，html标签 <br>
	 * 作者: hailong@xiu8.com<br>
	 * 时间: 2014年11月12日 下午3:00:04
	 * @param senWord	待检测字符串
	 * @param allSenWords	所有敏感词
	 */
	public static boolean dangerInput(String senWord, String allSenWords) {
		//全角转换半角
		senWord = fullToHalfChange(senWord).replace(" ", "");
		//正则替换ASCII码在8000-9000的占位符
		senWord.replaceAll("[\\u1f40-\\u2328]", "");
		boolean result = false;
		//正则验证字符串
		Pattern p_badwords = Pattern.compile(allSenWords, Pattern.CASE_INSENSITIVE);
		Pattern p_html = Pattern.compile("<([^>]*)>", Pattern.CASE_INSENSITIVE);
		Matcher m2 = p_badwords.matcher(senWord);
		Matcher m3 = p_html.matcher(senWord);
		if (checkDomain(senWord) || m2.find() || m3.find()) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 数组去重重复
	 * 作者: baijiangtao@xiu8.com<br>
	 * 时间: 2016年8月24日 下午3:00:04
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] removeRepeatStr(String[] arr) {
		if (StringHandler.isEmpty(arr)) {
			return arr;
		}
		List list = Arrays.asList(arr);
		Set set = new HashSet(list);
		return (String[]) set.toArray(new String[0]);
	}
	
	/**
	 * 域名后缀
	 */
	private static String[] domain = { "ac", "ad", "ae", "aero", "af", "ag", "ai", "al", "am", "an", "ao", "aq", "ar", "arpa", "as", "asia", "at", "au", "aw",
			"ax", "az", "ba", "bb", "bd", "be", "bf", "bg", "bh", "bi", "biz", "bj", "bl", "bm", "bn", "bo", "bq", "br", "bs", "bt", "bv", "bw", "by", "bz",
			"ca", "cat", "cc", "cd", "cf", "cg", "ch", "ci", "ck", "cl", "cm", "cn", "co", "com", "coop", "cr", "cu", "cv", "cw", "cx", "cy", "cz", "de", "dj",
			"dk", "dm", "do", "dz", "ec", "edu", "ee", "eg", "eh", "er", "es", "et", "eu", "fi", "fj", "fk", "fm", "fo", "fr", "ga", "gb", "gd", "ge", "gf",
			"gg", "gh", "gi", "gl", "gm", "gn", "gov", "gp", "gq", "gr", "gs", "gt", "gu", "gw", "gy", "hk", "hm", "hn", "hr", "ht", "hu", "id", "ie", "il",
			"im", "in", "info", "int", "io", "iq", "ir", "is", "it", "je", "jm", "jo", "jobs", "jp", "ke", "kg", "kh", "ki", "km", "kn", "kp", "kr", "kw",
			"ky", "kz", "la", "lb", "lc", "li", "lk", "lr", "ls", "lt", "lu", "lv", "ly", "ma", "mc", "md", "me", "mf", "mg", "mh", "mil", "mk", "ml", "mm",
			"mn", "mo", "mobi", "mp", "mq", "mr", "ms", "mt", "mu", "museum", "mv", "mw", "mx", "my", "mz", "na", "name", "nc", "ne", "net", "nf", "ng", "ni",
			"nl", "no", "np", "nr", "nu", "nz", "om", "org", "pa", "pe", "pf", "pg", "ph", "pk", "pl", "pm", "pn", "pr", "pro", "ps", "pt", "pw", "py", "qa",
			"re", "ro", "rs", "ru", "rw", "sa", "sb", "sc", "sd", "se", "sg", "sh", "si", "sj", "sk", "sl", "sm", "sn", "so", "sr", "st", "su", "sv", "sx",
			"sy", "sz", "tc", "td", "tel", "tf", "tg", "th", "tj", "tk", "tl", "tm", "tn", "to", "tp", "tr", "travel", "tt", "tv", "tw", "tz", "ua", "ug",
			"uk", "um", "us", "uy", "uz", "va", "vc", "ve", "vg", "vi", "vn", "vu", "wf", "ws", "ye", "yt", "za", "zm", "zw" };
	
	/**
	 * 产生N位的随机数字
	 * @return
	 */
	public static String getRandomNum(int n) {
		Random rad = new Random();
		String result = rad.nextInt(1000000) + "";
		if (result.length() != n) {
			return getRandomNum(n);
		}
		return result;
	} 
	
	/**
	 * 
	 * @Title : filter
	 * @Type : FilterStr
	 * @Description : 过滤出字母、数字和中文
	 * @param character
	 * @return
	 */
	public static String filterSpecialStr(String character)
	{
		character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
		return character;
	}


	/**
	 * 由10 进制数转换其余进制数
	 * @param userId
	 * @param base 要转换的进制数
	 * @return
	 */
	public static String convertBy10(long userId, int base){
		if(userId <= 0 ){
			return null;
		}
		return Long.toString(userId, base);
	}
	
	/**
	 * 由其余进制数转为10进制
	 * @param str 
	 * @param base 要转换的进制数
	 * @return
	 */
	public static long convertTo10(String str ,int base){
		if(StringHandler.isEmpty(str)){
			return 0;
		}
		try {
			return Long.parseLong(str, 36);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	/**
	 * 此方法描述的是：判断参数是否为空   
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static String nullDeal(Map params,String key,String defaultValue){
		if(params == null || StringHandler.isEmpty(params.get(key)+"")){
			return StringHandler.isNotEmpty(defaultValue) ? defaultValue : "";
		}
		return String.valueOf(params.get(key));
	}
	
	
	private static String charBaseStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	
	/**随机字符串
	 * @param length 长度
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(charBaseStr.length());   
	        sb.append(charBaseStr.charAt(number));   
	    }   
	    return sb.toString();   
	 }
	
	 /**
     * 此方法描述的是：  随机生成4位随机验证码 
     * @return String
     */
    public static String getRandomVcode(){
        String vcode = "";
        for (int i = 0; i < 4; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }

	public static String generateSerialCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static boolean equals(String value1, String value2) {
		if (value1 == value2) {
			return true;
		} else {
			return value1 == null ? value2.equals(value1) : value1.equals(value2);
		}
	}

	public static String setHidePhone(String phone) {
		if (phone != null && !phone.isEmpty()) {
			StringBuilder sb = new StringBuilder(phone);
			sb.replace(3, 7, "****");
			return sb.toString();
		} else {
			return "";
		}
	}

	public static String setHideEmail(String email) {
		if (email != null && !email.isEmpty()) {
			StringBuilder sb = new StringBuilder(email);
			sb.replace(1, sb.lastIndexOf("@") - 1, "******");
			return sb.toString();
		} else {
			return "";
		}
	}

	public static String setHideIdCard(String idCard) {
		if (idCard != null && !idCard.isEmpty()) {
			int length = idCard.length();
			int beforeLength = 1;
			int afterLength = 1;
			String replaceSymbol = "*";
			StringBuffer sb = new StringBuffer();

			for(int i = 0; i < length; ++i) {
				if (i >= beforeLength && i < length - afterLength) {
					sb.append(replaceSymbol);
				} else {
					sb.append(idCard.charAt(i));
				}
			}

			return sb.toString();
		} else {
			return "";
		}
	}

	public static String getFixLenthNumberString(int strLength) {
		Random rm = new Random();
		double pross = (1.0D + rm.nextDouble()) * Math.pow(10.0D, (double)strLength);
		String fixLenthString = String.valueOf(pross);
		return fixLenthString.substring(1, strLength + 1);
	}


	public static void main(String[] args) {
//		for (int i = 8000; i < 9000; i++) {
//			System.out.println("ASCII码：" + i + ",转换16进制：" + Integer.toHexString(i) + ",显示汉字为：" + (char) Integer.parseInt("" + i));
//		}
//		System.out.println("ASCII码转换16进制数：" + Integer.toHexString(8000));
//		String s = "ὀbb⌛aa⌚ỿ⌧nu⌤l";
//		String replace = s.replaceAll("[\\u1f40-\\u2328]", "");
//		System.out.println("原字符:" + s + ",替换回字符:" + replace);
		System.out.println(getRandomString(10));;
	}
}

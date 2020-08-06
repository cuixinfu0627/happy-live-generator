package com.happy.live.common.base.ipdata;

import com.happy.live.common.base.JSONHandler;
import com.happy.live.common.base.StringHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 此类描述的是:IP处理工具
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:31:22
 * @version V1.0
 */
public class IpToolHandler {
	private static String IP_DATA_PATH = "/usr/local/ipdata/ipdb.dat";

	private static final Logger logger = LoggerFactory.getLogger(IpToolHandler.class);
	/**
	 * 得到IP流
	 */
	public static byte[] getIpByteArrayFromString(String ip) {
		byte[] ret = new byte[4];
		java.util.StringTokenizer st = new java.util.StringTokenizer(ip, ".");
		try {
			ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
			ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 对原始字符串进行编码转换，如果失败，返回原始的字符串
	 */
	public static String getString(String s, String srcEncoding, String destEncoding) {
		try {
			return new String(s.getBytes(srcEncoding), destEncoding);
		} catch (Exception e) {
			return s;
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, String encoding) {
		try {
			return new String(b, encoding);
		} catch (Exception e) {
			return new String(b);
		}
	}

	/**
	 * 根据某种编码方式将字节数组转换成字符串
	 * 
	 * @param b
	 *            字节数组
	 * @param offset
	 *            要转换的起始位置
	 * @param len
	 *            要转换的长度
	 * @param encoding
	 *            编码方式
	 * @return 如果encoding不支持，返回一个缺省编码的字符串
	 */
	public static String getString(byte[] b, int offset, int len, String encoding) {
		try {
			return new String(b, offset, len, encoding);
		} catch (Exception e) {
			return new String(b, offset, len);
		}
	}

	/**
	 * @param ip
	 *            ip的字节数组形式
	 * @return 字符串形式的ip
	 */
	public static String getIpStringFromBytes(byte[] ip) {
		StringBuffer sb = new StringBuffer();
		sb.append(ip[0] & 0xFF);
		sb.append('.');
		sb.append(ip[1] & 0xFF);
		sb.append('.');
		sb.append(ip[2] & 0xFF);
		sb.append('.');
		sb.append(ip[3] & 0xFF);
		return sb.toString();
	}

	/**
	 * ip地址转成整数.
	 */
	public static long ip2long(String ip) {
		String[] ips = ip.split("[.]");
		return 16777216L * Long.parseLong(ips[0]) + 65536L * Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2]) + Long.parseLong(ips[3]);
	}

	/**
	 * 整数转成ip地址.
	 */
	public static String long2ip(long ipLong) {
		long mask[] = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
		long num = 0;
		StringBuffer ipInfo = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			num = (ipLong & mask[i]) >> (i * 8);
			if (i > 0)
				ipInfo.insert(0, ".");
			ipInfo.insert(0, Long.toString(num, 10));
		}
		return ipInfo.toString();
	}

	@SuppressWarnings("unchecked")
	public static IPDatCityEntity getTaoBaoLocalInfoByAPI(String ip) throws Exception {
		if(isInner(ip)){
			return null;
		}
		IPDatCityEntity d = null;
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("ip", ip);
			String url = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
			String result = UrlHandler.getUrlContentByGet(url, 1000);
			Map<String, Object> map = JSONHandler.getGson().fromJson(result, Map.class);
			if (null == map || StringHandler.getInt(map.get("code"), -1) != 0) {
				return null;
			}
			if (StringHandler.isEmpty(map.get("data"))) {
				return null;
			}
			map = (Map<String, Object>) map.get("data");
			d = new IPDatCityEntity();
			d.country = map.get("country").toString();
			d.province = map.get("region").toString();
			d.city = map.get("city").toString();
			d.district = map.get("area").toString();
			d.isp = map.get("isp").toString();
		} catch (Exception e) {
			logger.error("从淘宝获得IP:" + ip + "信息出错 : " + e.getLocalizedMessage());
			throw e;
		}
		return d;
	}
	
	
	/**
	 * 调用新浪接口通过ip解析省份城市
	 * @param ip
	 * @return
	 * @throws Exception 
	 */
	public static IPDatCityEntity getSinaIpDataByAPI(String ip) throws Exception {
		if(isInner(ip)){
			return null;
		}
		IPDatCityEntity d = null;
		try {
			String url = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip="+ip;
			String result = UrlHandler.getUrlContentByGet(url, 1000);
			@SuppressWarnings("unchecked")
			Map<String, Object> map = JSONHandler.getGson().fromJson(result, Map.class);
			if (null == map || StringHandler.getInt(map.get("ret"), -1) != 1) {
				return null;
			}
			d = new IPDatCityEntity();
			d.country = map.get("country").toString();
			d.province = map.get("province").toString();
			d.city = map.get("city").toString();
			d.district = map.get("district").toString();
			d.isp = map.get("isp").toString();
		} catch (Exception e) {
			logger.error("从新浪获得IP:" + ip + "信息出错 : " + e.getLocalizedMessage());
			throw e;
		}
		return d;
	}

	/**
	 * 请使用 Networks.ipv4();
	 * 
	 * @return 返回Ip地址
	 */
	@Deprecated
	public static String getMachineIP() {
		try {
			String hostIP = InetAddress.getLocalHost().getHostAddress();
			if (!hostIP.equals("127.0.0.1")) {
				return hostIP;
			}
			/*
			 * Above method often returns "127.0.0.1", In this case we need to
			 * check all the available network interfaces
			 */
			Enumeration<NetworkInterface> nInterfaces = NetworkInterface.getNetworkInterfaces();
			while (nInterfaces.hasMoreElements()) {
				Enumeration<InetAddress> inetAddresses = nInterfaces.nextElement().getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					String address = inetAddresses.nextElement().getHostAddress();
					if (!address.equals("127.0.0.1")) {
						return address;
					}
				}
			}
		} catch (Exception e1) {
		}
		return "";
	}

	/**
	 * 根据IP获取省份
	 */
	public static String getUserLoginProvince(String ip) {
		String province = "未知省份";
		try {
			//判断如果为内网IP，直接返回
			if (isInner(ip)) {
				return province;
			}
			IPDatCityEntity iPDatCity = IpDataSeeker.getInstance(IP_DATA_PATH).getAddressInfo(ip);
			if (null != iPDatCity && StringHandler.isNotEmpty(iPDatCity.province)) {
				province = iPDatCity.province;
			}
			logger.info("根据IP获取省份---解析IP:" + ip + "得到的省份为：" + province);
		} catch (Exception e) {
			logger.error("解析IP[" + ip + "]异常！" + StringHandler.getExceptionStack(e));
		}
		return province;
	}

	/**
	 * 判断IP是否为内网IP
	 */
	public static boolean isInner(String ip) {
		if(StringHandler.isEmpty(ip)) {
			return true;
		}
		//C类：192.168.0.0-192.168.255.255
		if(ip.startsWith("192.168.")){
			return true;
		}
		//A类：10.0.0.0-10.255.255.255
		if(ip.startsWith("10.")){
			return true;
		}
		//B类：172.16.0.0-172.31.255.255 
		if(ip.startsWith("172.")){
			int end = ip.indexOf(".", 4);
			if(end>0){
				int num = StringHandler.getInt(ip.substring(4, end), 0);
				return num>=16&&num<=31;
			}
		}
		return false;
	}

	/**
	 * 获取IP地址
	 *
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader("x-forwarded-for");
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} catch (Exception e) {
			logger.error("IPUtils ERROR ", e);
		}

//        //使用代理，则获取第一个IP地址
//        if(StringUtils.isEmpty(ip) && ip.length() > 15) {
//			if(ip.indexOf(",") > 0) {
//				ip = ip.substring(0, ip.indexOf(","));
//			}
//		}

		return ip;
	}

    public static void main(String[] args) {  
        try {
			//IPDatCityEntity sinaIpDataByAPI = getSinaIpDataByAPI("223.104.1.144");
			IPDatCityEntity sinaIpDataByAPI2 = getTaoBaoLocalInfoByAPI("106.120.89.26");
			//System.out.println(JSONHandler.getGson().toJson(sinaIpDataByAPI));
			System.out.println("================haha=================");
			System.out.println(JSONHandler.getGson().toJson(sinaIpDataByAPI2));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  

}
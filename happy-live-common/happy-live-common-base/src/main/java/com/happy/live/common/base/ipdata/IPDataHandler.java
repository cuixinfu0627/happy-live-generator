package com.happy.live.common.base.ipdata;

/*************************************************************************
> File Name: IPDataHandler.java
> Author: ldq
> Mail: 446529842@qq.com 
> Created Time: Sat 08 Mar 2014 02:19:59 PM EST
************************************************************************/
/************************************************************************
*1.fix ip to  int overflow issue
*2.reduce memory use
*3.clean the code ,make int more shorter and simple
*4.default limit  size of ip data 17monipdb.dat is 2GB（not test yet）。  
************************************************************************/

import com.happy.live.common.base.StringHandler;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IPDataHandler {
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(IPDataHandler.class);
	
	private static String IP_DATA_PATH = "/usr/local/ipdata/ipdb.dat";
	private static DataInputStream inputStream = null;
	private static long fileLength = -1;
	private static int dataLength = -1;
	private static Map<String, String> cacheMap = null;
	private static byte[] allData = null;
	
	public IPDataHandler(String filePath) {
		super();
		IP_DATA_PATH = filePath;
		try {
			File file = new File(IP_DATA_PATH);
			inputStream = new DataInputStream(new FileInputStream(file));
			fileLength = file.length();
			cacheMap = new HashMap<String, String>();
			if (fileLength >Integer.MAX_VALUE) {
				throw new Exception("the filelength over 2GB");
			}
			dataLength = (int) fileLength;
			allData = new byte[dataLength];
			inputStream.read(allData, 0, dataLength);
			dataLength = (int)getbytesTolong(allData, 0, 4,ByteOrder.BIG_ENDIAN);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static long getbytesTolong(byte[] bytes, int offerSet,int size,ByteOrder byteOrder){
		if ((offerSet+size) > bytes.length || size <= 0) {
			return -1;
		}
		byte[] b = new byte[size];
		for (int i = 0; i < b.length; i++) {
			b[i] = bytes[offerSet+i];
		}
		
		ByteBuffer byteBuffer = ByteBuffer.wrap(b);
		byteBuffer.order(byteOrder);
		
		long temp = -1;
		if (byteBuffer.hasRemaining()) {
			temp = byteBuffer.getInt();
		}
		return temp;
	}
	
	private static long ip2long(String ip) throws UnknownHostException {
		InetAddress address = InetAddress.getByName(ip);
		byte[] bytes = address.getAddress();	
		long reslut = getbytesTolong(bytes, 0, 4,ByteOrder.BIG_ENDIAN);
		return reslut;
	}
	
	
	private static int getIntByBytes(byte[] b,int offSet){
		if (b == null || (b.length < (offSet+3))) {
			return -1;
		}
		
		byte[] bytes = Arrays.copyOfRange(allData, offSet, offSet+3);
		byte[] bs = new byte[4];
		bs[3] = 0;
		for (int i = 0; i < 3;i++) {
			bs[i]=bytes[i];
		}
		
		return (int)getbytesTolong(bs, 0, 4, ByteOrder.LITTLE_ENDIAN);
	}
	
	
	public static String findGeography(String address){
		if (StringHandler.isEmpty(address)) {
			return "illegal address";
		}
		
		if (dataLength < 4 || allData == null) {
			return "illegal ip data";
		}
		
		String ip = "127.0.0.1";
		try {
			ip = Inet4Address.getByName(address).getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		
		String[] ipArray = StringUtils.split(ip, ".");
		int ipHeadValue = Integer.parseInt(ipArray[0]);
		if (ipArray.length !=4 || ipHeadValue < 0 || ipHeadValue > 255) {
			return "illegal ip";
		}
		
		if (cacheMap.containsKey(ip)) {
			return cacheMap.get(ip);
		}
		
		
		long numIp = 1;
		try {
			numIp = ip2long(address);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		
		int tempOffSet = ipHeadValue* 4 + 4;
		long start = getbytesTolong(allData, tempOffSet, 4,ByteOrder.LITTLE_ENDIAN);
		int max_len = dataLength - 1028;
		long resultOffSet = 0;
		int resultSize = 0;
		for (start = start*8 + 1024; start < max_len; start+=8) {
			if (getbytesTolong(allData, (int)start+4, 4,ByteOrder.BIG_ENDIAN) >= numIp) {
				resultOffSet = getIntByBytes(allData, (int)(start+4+4));
				resultSize = (char)allData[(int)start+7+4];
				break;
			}
		}
		
		if (resultOffSet <= 0) {
			return "resultOffSet too small";
		}
		
		byte[] add = Arrays.copyOfRange(allData, (int)(dataLength+resultOffSet-1024), (int)(dataLength+resultOffSet-1024 + resultSize));
		try {
			if (add == null) {
				cacheMap.put(ip, new String("no data found!!"));
			} else {
				cacheMap.put(ip, new String(add,"UTF-8"));
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return cacheMap.get(ip);
	}
	
	/**
	 * 判断IP是否在指定范围；          
	 * @param ipSection ip段  
	 * @param ip ip地址  
	 * @return
	 */
    public static boolean ipIsValid(String ipSection, String ip) {     
        if (ipSection == null)     
            throw new NullPointerException("IP段不能为空！");     
        if (ip == null)     
            throw new NullPointerException("IP不能为空！");     
        ipSection = ipSection.trim();     
        ip = ip.trim();     
        final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";     
        final String REGX_IPB = REGX_IP + "\\-" + REGX_IP;     
        if (!ipSection.matches(REGX_IPB) || !ip.matches(REGX_IP))     
            return false;     
        int idx = ipSection.indexOf('-');     
        String[] sips = ipSection.substring(0, idx).split("\\.");     
        String[] sipe = ipSection.substring(idx + 1).split("\\.");     
        String[] sipt = ip.split("\\.");     
        long ips = 0L, ipe = 0L, ipt = 0L;     
        for (int i = 0; i < 4; ++i) {     
            ips = ips << 8 | Integer.parseInt(sips[i]);     
            ipe = ipe << 8 | Integer.parseInt(sipe[i]);     
            ipt = ipt << 8 | Integer.parseInt(sipt[i]);     
        }     
        if (ips > ipe) {     
            long t = ips;     
            ips = ipe;     
            ipe = t;     
        }     
        return ips <= ipt && ipt <= ipe;     
    }   
    
    /**
     * main 方法测试
     * @param args
     */
	@SuppressWarnings("static-access")
	public static void main(String[] args){
		String address = "172.22.10.9" ;
		String ipFileUrl = "E:\\test\\ip\\ipdb.dat";
		IPDataHandler ipHandler = new IPDataHandler(ipFileUrl);
		String findGeography[] = ipHandler.findGeography(address).split("	");
		if("中国".equals(findGeography[0])){
			System.out.println(findGeography[0]);
		}else{
			System.out.println(findGeography[0]);
		}
	}
}

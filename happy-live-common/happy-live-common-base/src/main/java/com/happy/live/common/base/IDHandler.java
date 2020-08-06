package com.happy.live.common.base;

import java.util.Random;
import java.util.UUID;

/**
 * 此类描述的是:各种id生成策略
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:43:31
 */
public class IDHandler {

	/**
	 * 此方法描述的是：   图片名生成
	 * @return String
	 */
	public static String genImageName() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上三位随机数
		Random random = new Random();
		int end3 = random.nextInt(999);
		//如果不足三位前面补0
		String str = millis + String.format("%03d", end3);
		
		return str;
	}
	
	
	 /**
	  * 此方法描述的是：   商品id生成
	  * @return long
	  */
	public static long genItemId() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//long millis = System.nanoTime();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = millis + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	/**
	 * 此方法描述的是：   uuid
	 * @return String
	 * 
	 */
	public static String genGUID(){
        // 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String guid = uuid.toString();
        // 转换为大写
        guid = guid.toUpperCase();
        // 替换 -
        guid = guid.replaceAll("-", "");
        return guid;
	}
	
	/**
	 * 此方法描述的是：   数据库主键  
	 * @return String
	 */
	public static String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replaceAll("-", "");
    }
	
	public static void main(String[] args) {
	    System.out.println("genImageName : " + genImageName());
	    System.out.println("genItemId : " + genItemId());
	    System.out.println("genGUID : " + genGUID());
	}
}

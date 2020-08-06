package com.happy.live.common.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 此类提供的静态方法，将任意对象的Long属性转成字符串
 * 返回给前台数据当为long时会出现+1-1的情况，此方法可避免该问题
 * @author 北京无限时空网络技术有限公司
 *
 */
public class JSONHandler {

	//资源分类子结构名称
	public static final String RESOURCE_CHILD_NAME = "RESOURCE_CHILD_NAME";

	//创建MAPPER对象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 通过目标对象转化为json字符串
	 * @param obj
	 * @return
	 */
	public static String getJsonStr(Object obj){
		return getGson().toJson(obj).replaceAll("null", "\"\"");
	}
	
	public static String getJsonStrNotNull(Object obj){
		return getGsonNotserializeNulls().toJson(obj).replaceAll("null", "\"\"");
	}
	
	/**
	 * 通过json字符串转化为目标类型对象
	 * @param obj
	 * @param c
	 * @return
	 */
	public static <T> T getObjectByJsonStr(String obj,Class<T> c){
		return getGson().fromJson(obj, c) ;
	}
	
	/**
	 * 通过json字符串转化为目标类型对象
	 * @param obj
	 * @param c
	 * @return
	 */
	public static <T> T getObjectByJsonStr(String obj,Class<T> c,Boolean withNull){
		if(withNull){
			return getGson().fromJson(obj, c) ;
		}
		return getGsonNotserializeNulls().fromJson(obj, c) ;
		
	}
	
	/**
	 * 通过json字符串转化为Object对象
	 * @param obj
	 * @return
	 */
	public static Object getObjectByJsonStr(String obj){
		
		return getGson().fromJson(obj,Object.class) ;
	}
	
	public static Gson getGson(){
		Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls()  
				  .create();
		return gson;
	}
	
	public static Gson getGsonNotserializeNulls(){
		Gson gson = new GsonBuilder()  
				  .setDateFormat("yyyy-MM-dd HH:mm:ss")
				  .create();
		return gson;
	}

	/**
	 * 将对象转换成json字符串。
	 * <p>Title: pojoToJson</p>
	 * <p>Description: </p>
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data) {
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		Map<String,Object> data = new HashMap<String,Object>() ;
		data.put("username", "huying") ;
		data.put("time", new Date()) ;
		System.out.println(getJsonStr(JSONResult.faild("a","sdfsdf",data).addValue("sdfsdf", "sdfsdf"))) ;
		System.out.println(getJsonStr(getObjectByJsonStr(getJsonStr(JSONResult.faild()),JSONResult.class)));
	}
}

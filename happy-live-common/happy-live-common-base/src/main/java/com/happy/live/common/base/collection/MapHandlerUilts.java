package com.happy.live.common.base.collection;

import com.happy.live.common.base.StringHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;


/**   
 * 类:MapHandlerUilts.java <tb>
 * 描述： MapHandlerUilts  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2018年4月26日 下午11:28:21 <tb>
 */
public class MapHandlerUilts {
	/**
	   * 从map集合中获取属性值
	   * 
	   * @param <E>
	   * @param map
	   *      map集合
	   * @param key
	   *      键对
	   * @param defaultValue
	   *      默认值
	   * @return
	   * @author jiqinlin
	   */
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	  public final static <E> E get(Map map, Object key, E defaultValue) {
	    Object o = map.get(key);
	    if (o == null)
	      return defaultValue;
	    return (E) o;
	  }
	   
	  /**
	   * Map集合对象转化成 JavaBean集合对象
	   * 
	   * @param javaBean JavaBean实例对象
	   * @param mapList Map数据集对象
	   * @return
	   * @author jqlin
	   */
	  @SuppressWarnings({ "rawtypes" })
	  public static <T> List<T> map2Java(T javaBean, List<Map> mapList) {
	    if(mapList == null || mapList.isEmpty()){
	      return null;
	    }
	    List<T> objectList = new ArrayList<T>();
	     
	    T object = null;
	    for(Map map : mapList){
	      if(map != null){
	        object = map2Java(javaBean, map);
	        objectList.add(object);
	      }
	    }
	     
	    return objectList;
	     
	  }
	   
	  /**
	   * Map对象转化成 JavaBean对象
	   * 
	   * @param javaBean JavaBean实例对象
	   * @param map Map对象
	   * @return
	   * @author jqlin
	   */
	  @SuppressWarnings({ "rawtypes","unchecked",})
	  public static <T> T map2Java(T javaBean, Map map) {
	    try {
	      // 获取javaBean属性
	      BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
	      // 创建 JavaBean 对象
	      Object obj = javaBean.getClass().newInstance();
	 
	      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	      if (propertyDescriptors != null && propertyDescriptors.length > 0) {
	        String propertyName = null; // javaBean属性名
	        Object propertyValue = null; // javaBean属性值
	        for (PropertyDescriptor pd : propertyDescriptors) {
	          propertyName = pd.getName();
	          if (map.containsKey(propertyName)) {
	            propertyValue = map.get(propertyName);
	            pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
	          }
	        }
	        return (T) obj;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	 
	    return null;
	  }
	 
	  /**
	   * JavaBean对象转化成Map对象
	   * 
	   * @param javaBean
	   * @return
	   * @author jqlin
	   */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	  public static Map java2Map(Object javaBean) {
	    Map map = new HashMap();
	    try {
	      // 获取javaBean属性
	      BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
	 
	      PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	      if (propertyDescriptors != null && propertyDescriptors.length > 0) {
	        String propertyName = null; // javaBean属性名
	        Object propertyValue = null; // javaBean属性值
	        for (PropertyDescriptor pd : propertyDescriptors) {
	          propertyName = pd.getName();
	          if (!propertyName.equals("class")) {
	            Method readMethod = pd.getReadMethod();
	            propertyValue = readMethod.invoke(javaBean, new Object[0]);
	            map.put(propertyName, propertyValue);
	          }
	        }
	      }
	       
	    } catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return map;
	  }
	  
	  /**
		 * map转为list 加key加value
		 */
		public static List<Object> map2List(Map<String, ?> map){
			if(StringHandler.isEmpty(map)){
				return null;
			}
			List<Object> list = new ArrayList<Object>();
			for(Entry<String, ?> entry : map.entrySet()){
				list.add(entry.getKey());
				list.add(entry.getValue());
			}
			return list;
		}
		
		
		/**
		 * 从map中获得long
		 */
		public static long getLong4Map(Map<String, ?> map,String key){
			return Long.parseLong(String.valueOf(map.get(key)));
		}
		
		/**
		 * 移除map中的空值  空值判断 null ""
		 */
		public static void removeEmptyValue(Map<String, ?> map){
			if(null == map || map.isEmpty()){
				return;
			}
			for(Entry<String, ?> entry : map.entrySet()){
				if(StringHandler.isEmpty(entry.getValue())){
					map.remove(entry.getKey());
				}
			}
		}

		/**
		 * 将Map的Object值转化为String
		 */
		public static Map<String, String> obj2Str(Map<String, Object> map){
			if(null == map || map.isEmpty()){
				return null;
			}
			Map<String, String> res = new HashMap<String, String>();
			for(Entry<String, Object> entry : map.entrySet()){
				res.put(entry.getKey(), String.valueOf(entry.getValue()));
			}
			return res;
		}
		
		/**
		 * 将Map不为null的Object值转化为String
		 */
		public static Map<String, String> obj2StrNotNull(Map<String, Object> map){
			if(null == map || map.isEmpty()){
				return null;
			}
			Map<String, String> res = new HashMap<String, String>();
			for(Entry<String, Object> entry : map.entrySet()){
				if(null != entry.getValue()){
					res.put(entry.getKey(), String.valueOf(entry.getValue()));
				}
			}
			return res;
		}
		/**
		 * 初始化Map
		 */
		public static Map<String,String> initMap(String...args){
			Map<String,String> map=new HashMap<String, String>();
			if(args.length==0){
				return map;
			}
			int size=args.length%2==0?args.length:args.length-1;
			for (int i = 0; i < size;) {
				map.put(args[i++], args[i++]);
			}
			return map;
		}
		
		public static Map<String,Object> getMap(Object... params){
			if(null == params || params.length == 0){
				return null;
			}
			Map<String,Object> map = new HashMap<String, Object>();
			int size = (params.length%2==0?params.length:params.length-1);
			for (int i = 0; i < size;i = i+2) {
				map.put(String.valueOf(params[i]), params[i+1]);
			}
			return map;
		}
		
		/** 
	     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	     * @param params 需要排序并参与字符拼接的参数组
	     * @param params 需要排除的key字段
	     * @return 拼接后字符串
	     */
	    public static String map2String(Map<String, String> params, String exclude) {
	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);
	        StringBuffer prestr = new StringBuffer();
	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            if(exclude.equals(key) || null == params.get(key)){
	            	continue;
	            }
	        	prestr.append("&" + key + "=" + params.get(key));
	        }
	        return prestr.substring(1);
	    }
	    
	    /**
	     * 将map中value为空的去掉
	     * @return 
	     */
	    public static Map<String, String> removeNull(Map<String, String> map){
	    	if(StringHandler.isEmpty(map)){
	    		return map;
	    	}
	    	Iterator<Entry<String, String>> it = map.entrySet().iterator();  
	        while(it.hasNext()){
	            if(null == it.next().getValue()){
	            	it.remove(); 
	            }
	        }
	        return map;
	    }
	    
	    /**
		 * 生成签名(没有MD5加密和转换大写)
		 * 把map所有元素字典排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
		 * @param params
		 * @param key 不需要拼接key的传 空字符串""
		 * @return
		 */
		public static String sign(Map<String, ? extends Object> params, String key) {
			StringBuilder sb = new StringBuilder();
			ArrayList<String> keys = new ArrayList<>(params.size());
			keys.addAll(params.keySet());
			//参数名ASCII码从小到大排序（字典序）；
			Collections.sort(keys);
			for (int i = 0; i < keys.size(); i++) {
				Object val = params.get(keys.get(i));
				//如果参数的值为空不参与加密；签名参数不参与加密;签名类型不参与加密
				if (!"sign_type".equals(keys.get(i)) && !"sign".equals(keys.get(i)) && val != null && String.valueOf(params).trim().length() > 0) {
					sb.append('&').append(keys.get(i)).append('=').append(val);
				}
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(0);
				if(StringHandler.isNotEmpty(key)){
					sb.append("&key=").append(key);
				}
			}
			return sb.toString();
		}
}

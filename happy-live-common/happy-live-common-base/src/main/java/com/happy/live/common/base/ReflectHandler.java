package com.happy.live.common.base;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 类: ReflectHandler <br>  
 * 描述: 反射工具类  <br>
 * 作者: cuixinfu@ralncy.com <br>
 * 时间:2017年11月27日 上午9:06:53 <br>
 */

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ReflectHandler {
	/**
	 * 此方法描述的是：   
	 * @param object
	 * @return
	 * @throws Exception 
	 * Object
	 */
	public static Object copy(Object object) throws Exception{
	    Class<?> classType = object.getClass();
	    Object objectCopy = classType.getConstructor(new Class[]{}).newInstance(new Object[]{});
	    
	    //获得对象的所有成员变量
	    Field[] fields = classType.getDeclaredFields();
	    for(Field field : fields)
	    {
	        //获取成员变量的名字
	        String name = field.getName();    //获取成员变量的名字，此处为id，name,age
	        //System.out.println(name);
	
	        //获取get和set方法的名字
	        String firstLetter = name.substring(0,1).toUpperCase();    //将属性的首字母转换为大写            
	        String getMethodName = "get" + firstLetter + name.substring(1);
	        String setMethodName = "set" + firstLetter + name.substring(1);            
	        //System.out.println(getMethodName + "," + setMethodName);
	        
	        //获取方法对象
	        Method getMethod = classType.getMethod(getMethodName, new Class[]{});
	        Method setMethod = classType.getMethod(setMethodName, new Class[]{field.getType()});//注意set方法需要传入参数类型
	        
	        //调用get方法获取旧的对象的值
	        Object value = getMethod.invoke(object, new Object[]{});
	        //调用set方法将这个值复制到新的对象中去
	        setMethod.invoke(objectCopy, new Object[]{value});
	
	    }
	
	    return objectCopy;
	
	}

	public static HashMap getMap(Object o) {
		return getMap(o, true);
	}

	// 得到相应的get值 ，是否执行set =空 方法
	public static HashMap getMap(Object o, boolean flag) {

		return getMap(o, flag, new Class[] { String.class, Integer.class,
				Long.class, Double.class, Date.class });
	}


	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		if (str.trim().length() == 0)
			return null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// 得到相应的get值 ，是否执行set 空 方法 Class 什么类型的数组
	public static HashMap getMap(Object o, boolean flag, Class[] types) {
		HashMap hm = new HashMap();
		try {
			Field[] c = o.getClass().getDeclaredFields();
			for (int i = 0; i < c.length; i++) {

				if (isGetType(c[i].getType(), types)) {
					Object s = getProperty(o, c[i].getName());
					if (s != null) {
						hm.put(c[i].getName(), s);
						if (flag) {
							setProperty(o, c[i].getName(), null);
						}
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return hm;
	}

	/**
	 * 此方法描述的是：  把对象转换成hashmap 
	 * @param list
	 * @return  List<HashMap>
	 */
	public static List<HashMap> ObjectToHashMap(List list) {
		ArrayList<HashMap> mylist = new ArrayList<HashMap>();
		if (list != null && list.size() > 0) {
			try {
				Object o = list.get(0);
				Field[] c = o.getClass().getDeclaredFields();

				for (int j = 0; j < list.size(); j++) {
					o = list.get(j);
					HashMap hm = new HashMap();
					for (int i = 0; i < c.length; i++) {
						Object s = getProperty(o, c[i].getName());
						hm.put(c[i].getName(), s);
					}
					mylist.add(hm);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mylist;
	}

	public static boolean isGetType(Class c, Class[] c2) {
		boolean flag = false;
		for (int i = 0; i < c2.length; i++) {
			if (c == c2[i]) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static PropertyDescriptor getPropertyDescriptor(Class clazz,
			String propertyName) {
		StringBuffer sb = new StringBuffer();// 构建一个可变字符串用来构建方法名称
		Method setMethod = null;
		Method getMethod = null;
		PropertyDescriptor pd = null;

		try {

			Field f = clazz.getDeclaredField(propertyName);// 根据字段名来获取字段
			if (f != null) {
				// 构建方法的后缀
				String methodEnd = propertyName.substring(0, 1).toUpperCase()
						+ propertyName.substring(1);
				sb.append("set" + methodEnd);// 构建set方法
				// 构建set 方法
				setMethod = clazz.getDeclaredMethod(sb.toString(),new Class[] { f.getType() });
				sb.delete(0, sb.length());// 清空整个可变字符串
				sb.append("get" + methodEnd);// 构建get方法
				// 构建get 方法
				getMethod = clazz.getDeclaredMethod(sb.toString(),new Class[] {});
				// 构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
				pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pd;
	}

	public static void setProperty(Object obj, String propertyName, Object value) {
		Class clazz = obj.getClass();// 获取对象的类型
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);// 获取
		// clazz
		// 类型中的
		// propertyName
		// 的属性描述器
		Method setMethod = pd.getWriteMethod();// 从属性描述器中获取 set 方法
		try {
			Class[] c = setMethod.getParameterTypes();
			/*if (c[0] != value.getClass())// 类型不匹配跳出
				return;*/
			setMethod.invoke(obj, new Object[] { value });// 调用 set

			// 方法将传入的value值保存属性中去
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getProperty(Object obj, String propertyName) {
		Class clazz = obj.getClass();// 获取对象的类型
		PropertyDescriptor pd = getPropertyDescriptor(clazz, propertyName);// 获取
		// clazz
		// 类型中的
		// propertyName
		// 的属性描述器
		Method getMethod = pd.getReadMethod();// 从属性描述器中获取 get 方法
		Object value = null;
		try {
			value = getMethod.invoke(obj, new Object[] {});// 调用方法获取方法的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;// 返回值
	}

	public static Object getValueByName(Object obj, String methodName) {
		Class clazz = obj.getClass();// 获取对象的类型
		Object value = null;
		try {
			Method m = clazz.getMethod(methodName);
			value = m.invoke(obj, new Object[] {});// 调用方法获取方法的返回值
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;// 返回值
	}

	public static void copyValuesObject(Object source, Object dest) {

		Field[] df = dest.getClass().getDeclaredFields();
		for (int i = 0; i < df.length; i++) {
			if (checkField(source, df[i].getName())) {
				if (getProperty(source, df[i].getName()) != null) {
					// setProperty(dest, df[i].getName(),
					// ConvertUtils.convert(getProperty(source,
					// df[i].getName()).toString() , df[i].getType()));
					if (getProperty(source, df[i].getName()) != null)
						setProperty(dest, df[i].getName(), getProperty(source,
								df[i].getName()));

				}

			}
		}
	}

	// 检查是否存在
	public static boolean checkField(Object source, String name) {
		Field[] sf = source.getClass().getDeclaredFields();
		for (int i = 0; i < sf.length; i++) {
			if (sf[i].getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Object To json String
	 * @param obj
	 * @return json String
	 */
	public static String objToJsonString(Object obj) {

		// 初始化返回值
		String json = "var json=";

		if (obj == null) {
			return json;
		}

		StringBuilder buff = new StringBuilder();
		Field fields[] = obj.getClass().getFields();
		try {
			buff.append("[");
			buff.append("{");
			int i = 0;
			for (Field field : fields) {
				if (i != 0) {
					buff.append(",");
				}
				buff.append(field.getName());
				buff.append(":");
				buff.append("\"");
				buff.append(field.get(obj) == null ? "" : field.get(obj));
				buff.append("\"");
				i++;
			}
			buff.append("}");
			buff.append("]");
			json = buff.toString();
		} catch (Exception e) {
			throw new RuntimeException("cause:" + e.toString());
		}
		return json;
	}
	
	/**
	 * 将map中的空值置为空字符
	 * @param parameterMap
	 * @return
	 */
	public static Map transNullMap(Map parameterMap){
		  if (parameterMap==null)
			  return null;
		  
	      Iterator entries = parameterMap.entrySet().iterator();
	      Map.Entry entry;
	      while (entries.hasNext()) {
	          entry = (Map.Entry) entries.next();
	          Object valueObj = entry.getValue();
	          if(null == valueObj){
	        	  entry.setValue("");
	          }
	      }
	      return  parameterMap;
	  }

	/**
	 * 将request中的参数放入map中
	 * @param parameterMap
	 * @return
	 */
	public static Map<String,Object> transToMAP(Map parameterMap){
	      // 返回值Map
	      Map<String,Object> returnMap = new HashMap<String,Object>();
	      Iterator entries = parameterMap.entrySet().iterator();
	      Map.Entry entry;
	      String name = "";
	      String value = "";
	      while (entries.hasNext()) {
	          entry = (Map.Entry) entries.next();
	          name = (String) entry.getKey();
	          Object valueObj = entry.getValue();
	          if(null == valueObj){
	              value = "";
	          }else if(valueObj instanceof String[]){
	              String[] values = (String[])valueObj;
	              for(int i=0;i<values.length;i++){
	                  value = values[i] + ",";
	              }
	              value = value.substring(0, value.length()-1);
	          }else{
	              value = valueObj.toString();
	          }
	          returnMap.put(name, value);
	      }
	      String pageNo = StringHandler.nullDeal(returnMap, "page", "1");
	      String pageNum = StringHandler.nullDeal(returnMap, "rows", "10");
	      returnMap.put("pageNo", Integer.parseInt(pageNo));
	      returnMap.put("pageNum", Integer.parseInt(pageNum));
	      return  returnMap;
	  }
}

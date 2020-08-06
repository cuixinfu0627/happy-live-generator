package com.happy.live.common.base.collection;

import java.util.*;

public class MapToListUtil {

	public static List<Object> getList(String key,
			List<Map<String, Object>> mapList) {
		if(mapList==null)
			 return null;
		List<Object> result = new ArrayList<Object>(mapList.size());
		for (Map<String, Object> map : mapList) {
			Object value = map.get(key);
			result.add(value);
		}
		return result;
	}
	
	public static HashMap<String, Object> getMapFromListByKey(String key,Object value,
			List<HashMap<String, Object>> mapList) {
		if(key ==null ||mapList==null)
			 return null;
		for (HashMap<String, Object> map : mapList) {
			Object val = (String) map.get(key);
			if(value.equals(val))
			 return map;
		}
		return null;
	}
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map transStringToMap(String mapString){  
	Map map = new HashMap();  
      StringTokenizer items;
      for(StringTokenizer entrys = new StringTokenizer(mapString, ",");entrys.hasMoreTokens();   
        map.put(items.nextToken(), items.hasMoreTokens() ? ((Object) (items.nextToken())) : null))  
          items = new StringTokenizer(entrys.nextToken(), ":");  
      return map;  
    }
}

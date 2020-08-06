package com.happy.live.common.base.collection;

import java.io.Serializable;
import java.util.Comparator;

/**   
  * 类:MapKeyComparator.java <tb>
  * 描述： Map 排序工具类    <tb>
  * 作者:cuixinfu@51play.com <tb>
  * 时间:2018年7月16日 下午3:43:02 <tb>
  */
public class MapKeyComparator implements Comparator<String>,Serializable{

	private static final long serialVersionUID = -6685504328290192884L;

	@Override
    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
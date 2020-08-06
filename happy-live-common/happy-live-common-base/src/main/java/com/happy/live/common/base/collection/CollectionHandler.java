package com.happy.live.common.base.collection;


import java.util.*;

/**
 * 类名:TestListDiffrent <tb>
 * 描述: 该类提供对集合类的高效操作  <tb>
 * 作者:cuixinfu@51play.com <tb>
 * 时间:2019/8/2 10:11 <tb>
 */
public class CollectionHandler {
    private CollectionHandler() {

    }

    /**
     * 找出两个集合中不同的元素
     * @param collmax
     * @param collmin
     * @return collection
     */
    public static Collection getDifferent(Collection collmax, Collection collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collmax;
        Collection min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) == null) {
                csReturn.add(object);
            } else {
                map.put(object, 2);
            }
        }
        for (Map.Entry<Object, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                csReturn.add(entry.getKey());
            }
        }
        return csReturn;
    }

    /**
     * 找出两个集合中相同的元素
     * @param collmax
     * @param collmin
     * @return collection
     */
    public static Collection getSame(Collection collmax, Collection collmin) {
        //使用LinkedList防止差异过大时,元素拷贝
        Collection csReturn = new LinkedList();
        Collection max = collmax;
        Collection min = collmin;
        //先比较大小,这样会减少后续map的if判断次数
        if (collmax.size() < collmin.size()) {
            max = collmin;
            min = collmax;
        }
        //直接指定大小,防止再散列
        Map<Object, Integer> map = new HashMap<Object, Integer>(max.size());
        for (Object object : max) {
            map.put(object, 1);
        }
        for (Object object : min) {
            if (map.get(object) != null) {
                csReturn.add(object);
            }
        }
        return csReturn;
    }

    /**
     * 获取两个集合的不同元素,去除重复
     * @param collmax
     * @param collmin
     * @return ollection
     */
    public static Collection getDiffentNoDuplicate(Collection collmax, Collection collmin) {
        return new HashSet(getDifferent(collmax, collmin));
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        list1.add("test-"+1);
        list1.add("test-"+3);
        list1.add("test-"+5);
        list1.add("test-"+6);

        list2.add("test-"+1);
        list2.add("test-"+4);
        list2.add("test-"+5);
        long startTime = System.currentTimeMillis();
        Collection different = getDiffentNoDuplicate(list1, list2);
        System.out.println("共耗时：" + (System.currentTimeMillis() - startTime) + "毫秒");
        System.out.println("不同元素共有：" + different.size());
        Collection diffentNoDuplicate = getDiffentNoDuplicate(list1, list2);
        diffentNoDuplicate.forEach(list ->{
            System.out.println(list);
        });

        long startTime2 = System.currentTimeMillis();
        Collection same = getSame(list1, list2);
        System.out.println("共耗时：" + (System.currentTimeMillis() - startTime2) + "毫秒");
        System.out.println("相同元素共有：" + same.size());
        same.forEach(list ->{
            System.out.println(list);
        });

        List<String> newList = new ArrayList<>();
        diffentNoDuplicate.forEach(list ->{
            boolean contains = list2.contains(list);
            if (!contains){
                newList.add((String) list);
            }
        });
        System.out.println("List2集合中没有元素[新增]:");
        newList.forEach(list ->{
            System.out.println(list);
        });

        List<String> newList2 = new ArrayList<>();
        diffentNoDuplicate.forEach(list ->{
            boolean contains = list1.contains(list);
            if (!contains){
                newList2.add((String) list);
            }
        });
        System.out.println("List1集合中没有元素[删掉]:");
        newList2.forEach(list ->{
            System.out.println(list);
        });

    }
}
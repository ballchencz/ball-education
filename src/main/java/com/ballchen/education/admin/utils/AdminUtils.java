package com.ballchen.education.admin.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ChenZhao on 2016/6/15.
 */
public class AdminUtils {
    /**
     * 将POJO转换成Map
     * @param object
     * @return
     */
    public static Map<String,Object> parsePOJOtoMap(Object object){
        BeanMap beanMap = BeanMap.create(object);
        Map<String,Object> map = new HashMap<>();
        Set<String> keySet = beanMap.keySet();
        Iterator<String> it = keySet.iterator();
        while(it.hasNext()){
            String key = it.next();
            Object value = beanMap.get(key);
            if(value!=null){
                map.put(key,value);
            }
        }
        return map;
    }
}

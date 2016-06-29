package com.ballchen.education.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;

/**
 * Created by ballchen on 2016/6/28.
 */
public class EducationUtils {

    /**
     * 写入所有
     * @param filePath 文件路径
     * @param paramMap 键值对
     * @return boolean
     */
    public static boolean writeAllProperties(String filePath,Map<String,Object> paramMap){
        boolean flag = false;
        Properties properties = new Properties();
        InputStream in = null;
        OutputStream out = null;
        try {
            Iterator<String> iterator = paramMap.keySet().iterator();
            in = new FileInputStream(filePath);
            properties.load(in);
            out = new FileOutputStream(filePath);
            while(iterator.hasNext()){
                String key = iterator.next();
                properties.setProperty(key, String.valueOf(paramMap.get(key)));
            }
            properties.store(out,"Update ");
            flag = true;
        }catch (IOException e) {
            //e.printStackTrace();
            flag = false;
        }finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }
    /**
     * 写入properties
     * @param filePath 配置文件路径
     * @param key 键值
     * @param value value
     * @return boolean
     */
    public static boolean writeProperties(String filePath,String key,String value){
        boolean flag = false;
        Properties properties = new Properties();
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(filePath);
            properties.load(in);
            out = new FileOutputStream(filePath);
            properties.setProperty(key,value);
            properties.store(out,"Update " + key + " name");
            flag = true;
        }catch (IOException e) {
            //e.printStackTrace();
            flag = false;
        }finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    /**
     * 根据键值获取value
     * @param filePath 文件路径
     * @param key 键值
     * @return String
     * @throws IOException IO异常
     */
    public static String getValueByKey(String filePath,String key) {
        Properties properties = new Properties();
        InputStream in = null;
        String value = null;
        try {
            in = new BufferedInputStream(new FileInputStream(filePath));
            properties.load(in);
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 读取Properties的全部信息
     * @param filePath 文件路径
     * @return JSONObject
     */
    public static Map<String,Object> getAllProperties(String filePath) {
        Properties pps = new Properties();
        InputStream in = null;
        Map<String,Object> jsonObject = null;
        try {
            jsonObject = new HashMap<>();
            in = new BufferedInputStream(new FileInputStream(filePath));
            pps.load(in);
            Enumeration en = pps.propertyNames(); //得到配置文件的名字
            while(en.hasMoreElements()) {
                String strKey = (String) en.nextElement();
                String strValue = pps.getProperty(strKey);
                jsonObject.put(strKey,strValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
/*    public static void main(String [] args){
        JSONObject jsonObject = EducationUtils.getAllProperties("C:/IEDA_WORKSPACE/ball-education/src/main/resources/fileServer.properties");
        System.out.println(jsonObject.toJSONString());
        boolean flag = EducationUtils.writeProperties("C:/IEDA_WORKSPACE/ball-education/src/main/resources/fileServer.properties","host","/public/upfile");
        System.out.println(flag);
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userName","admin1");
        paramMap.put("password","admin121");
        paramMap.put("port",3000);
        paramMap.put("host","/admin/fileManage");
        flag = EducationUtils.writeAllProperties("C:/IEDA_WORKSPACE/ball-education/src/main/resources/fileServer.properties",paramMap);
    }*/
}

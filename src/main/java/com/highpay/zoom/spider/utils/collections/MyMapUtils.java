package com.highpay.zoom.spider.utils.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.highpay.zoom.spider.utils.string.EncoderUtils;
import com.highpay.zoom.spider.utils.string.StringComparator;

/** 
 * @title 对Map的操作封装，可以写一点针对map key or value排序的策略
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:42
 * @version 1.0
 */
public class MyMapUtils {
	//将Map中的属性key按照ASCII码排序
	public static Map<String,Object> sortMapByKey(Map<String,Object> requestMap){
		if(requestMap == null || requestMap.isEmpty()){
			return new HashMap<String, Object>();
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new StringComparator());
	    sortMap.putAll(requestMap);
		return sortMap;
	}
	
	/**
	 * 将map中的key-value组装成key=value并且用&链接起来
	 * 是否需要编码
	 * @param requestMap
	 * @return
	 */
	public static String getParamString(Map<String,Object> requestMap){
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, Object>> it = requestMap.entrySet().iterator();
		while (it.hasNext()) {
		   Map.Entry<String, Object> entry = it.next();
		   sb.append(entry.getKey()).append("=").append(entry.getValue().toString());
		   if(!it.hasNext())continue;
		   sb.append("&");
		   
		}
		return sb.toString();
	}
	/**
	 * 将需要提交到服务器上面的参数的key-value,也需要排序,进行utf-8编码，防止类似===这样的字符不能被服务器识别
	 * 
	 * @param requestMap
	 * @return
	 */
	public static String getParamStringEncoder(Map<String,Object> requestMap){
		Map<String,Object> sortMap = sortMapByKey(requestMap);
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, Object>> it = sortMap.entrySet().iterator();
		while (it.hasNext()) {
		   Map.Entry<String, Object> entry = it.next();
		   sb.append(EncoderUtils.encodeUrlProperty(entry.getKey())).append("=").append(EncoderUtils.encodeUrlProperty(entry.getValue().toString()));
		   if(!it.hasNext())continue;
		   sb.append("&");
		   
		}
		return sb.toString();
	}
	/**
	 * params post提交时的参数需要进行utf-8编码,暂时先空着
	 * @param sortedRequestMap
	 */
	public static void encodeValues(Map<String,Object> sortedRequestMap){
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder sb = new StringBuilder();
		Iterator<Map.Entry<String, Object>> it = sortedRequestMap.entrySet().iterator();

		while (it.hasNext()) {
		   Map.Entry<String, Object> entry = it.next();
		   sb.append(entry.getKey()).append("=").append(entry.getValue().toString());
		   if(!it.hasNext())continue;
		   sb.append("&");
	   }
	}
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("ch", 1);
		map.put("aa", 2);
		map.put("ab", 3);
		
		Set<String> keys = map.keySet();
		
		MyMapUtils.sortMapByKey(map);
		System.out.println(map.toString());
		System.out.println(keys.toString());
	}
}

/**
 * 
 */
package com.highpay.zoom.spider.utils.http;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import com.highpay.zoom.spider.utils.collections.MyArrayUtils;


/**
 * @author jasonChiu
 * 
 * @title 主要处理合并cookie
 * @time 2016年1月14日下午3:57:59
 * @version 1.0
 */
public class HttpCookieUtils {
	public static final String[] EXCLUDE_COOKIEKEY = {"Path","Expires","HttpOnly","Domain"};
 
	/**
	 * 从urlConnection请求中取得cookie值，多个cookie会合并，主要用于登录后请求
	 */
	public static String getCookieValue(final HttpURLConnection urlConnection) {
		String cookieStr = "";
		String key;
		for (int i = 1;(key = urlConnection.getHeaderFieldKey(i)) != null; i++) {
			if (key.equalsIgnoreCase("Set-Cookie")) {
			    String cookieVal = urlConnection.getHeaderField(i);
				cookieStr += cookieVal + ";";
			}
		}
		System.err.println(cookieStr);
		return cookieStr;
	}
	public static String getCookieByKey(String key){
		String value = StringUtils.EMPTY;
		String cookie = CookieContextThreadLocal.getCookie();
		Map<String,String> sourceMap = getCookieString2Map(cookie);
		if(MapUtils.isNotEmpty(sourceMap)){
			value = MapUtils.getString(sourceMap, key);
		}
		return value;
	}
	
	/**
	 * @title 合并多次请求的cookie，手动放入想要放入的cookie字符串，外部调用方法
	 * @param urlConnection
	 */
	public static void mergeCookie(String newCookie){
		//检查是否存在源cookie
		String source = CookieContextThreadLocal.getCookie();
		Map<String, String> sourceMap = getCookieString2Map(source);
		//合并新老cookie
		mergeCookie(sourceMap,newCookie);
		//在将cookie转成string，放入cookie管理
		String cookie = getCookieMap2String(sourceMap);
		System.err.println(cookie);
		CookieContextThreadLocal.setCookie(cookie);
	}
	
	/**
	 * @title 合并多次请求的cookie，请求级别，外部调用方法
	 * @param urlConnection
	 */
	public static void mergeCookie(final HttpURLConnection urlConnection){
		//获取源cookie，放入新cookie
		List<String> cookieList = getCookieList(urlConnection);
		//检查是否存在源cookie
		String source = CookieContextThreadLocal.getCookie();
		Map<String, String> sourceMap = getCookieString2Map(source);
		//合并新老cookie
		mergeCookie(sourceMap,cookieList);
		//在将cookie转成string，放入cookie管理
		String newCookie = getCookieMap2String(sourceMap);
		System.err.println("合并后的cookie："+newCookie);
		CookieContextThreadLocal.setCookie(newCookie);
	}
	/**
	 * 清楚cookie值
	 */
	public static void clear(){
		CookieContextThreadLocal.setCookie(null);
	}
	public static String getCookieMap2String(Map<String,String> cookieMap){
		StringBuilder sb = new StringBuilder();
		if(MapUtils.isNotEmpty(cookieMap)){
		   Iterator<Map.Entry<String, String>> it = cookieMap.entrySet().iterator();
		   while (it.hasNext()) {
		    Entry<String, String> entry = it.next();
		     sb.append(entry.getKey()).append("=").append(entry.getValue()).append(";");
		  }
		}
		return sb.toString();
	}
	/**
	 * @title 获取每次最新的cookie
	 * 
	 * @param urlConnection
	 * @return
	 */
	public static List<String> getCookieList(final HttpURLConnection urlConnection){
		Map<String,List<String>> headerFields = urlConnection.getHeaderFields();
		List<String> cookies = new ArrayList<String>();
		if (null == headerFields) {
			return cookies;
		}
		cookies = headerFields.get(HttpConst.SET_COOKIE);
		if (CollectionUtils.isEmpty(cookies)) {
		    return cookies;
		}
		return cookies;
	}
	/**
	 * @title 将字符串cookie，转成map形式
	 * 
	 * @param cookie
	 * @return
	 */
	public static Map<String,String>  getCookieString2Map(String cookie){
		Map<String,String> cookieMap = new HashMap<String, String>();
		if(StringUtils.isNotEmpty(cookie)&&StringUtils.contains(cookie,";")){
			String[] cookies = cookie.split("\\;");
			for (String cookieItem : cookies) {
				if(cookieItem.contains("=")){
					String[] item =  cookieItem.split("=");
					cookieMap.put(item[0], item[1]);
				}
			}
		}
		return cookieMap;
	}
	/**
	 * @title 合并cookie，并指明合并的策略
	 * 
	 * @param cookie
	 * @return
	 */
	public static Map<String,String>  mergeCookie(Map<String,String> source,List<String> cookies){
		return mergeCookie(source, cookies,false);
	}
	/**
	 * @title 合并cookie，并指明合并的策略
	 * 
	 * @param cookie
	 * @return
	 */
	public static Map<String,String>  mergeCookie(Map<String,String> source,List<String> cookies,boolean over){
		if(CollectionUtils.isEmpty(cookies)){
			return source;
		}
		for (String cookie : cookies) {
			if(StringUtils.isNotEmpty(cookie)&&StringUtils.contains(cookie,"=")){
				String[] cooks = cookie.split("\\;");
				for (String cookieItem : cooks) {
					if(cookieItem.contains("=")){
						String[] item =  cookieItem.split("=");
						if(!MyArrayUtils.containsIgnoreCase(EXCLUDE_COOKIEKEY,item[0])){
							if(source.containsKey(item[0])){
								if(over){
									source.put(item[0], item[1]);
								}
							}else{
								source.put(item[0], item[1]);
							}
						}
					}
				}
			}
		}
		return source;
	}
	public static Map<String,String>  mergeCookie(Map<String,String> source,String cookie){
		return mergeCookie(source,cookie,false);
	}

	/**
	 * @title 合并cookie，并指明合并的策略
	 * 
	 * @param cookie
	 * @return
	 */
	public static Map<String,String>  mergeCookie(Map<String,String> source,String cookie,boolean over){
		if(StringUtils.isNotEmpty(cookie)&&StringUtils.contains(cookie,";")){
			String[] cooks = cookie.split("\\;");
			for (String cookieItem : cooks) {
				if(cookieItem.contains("=")){
					String[] item =  cookieItem.split("=");
					if(!MyArrayUtils.containsIgnoreCase(EXCLUDE_COOKIEKEY,item[0])){
						if(source.containsKey(item[0])){
							if(over){
								source.put(item[0], item[1]);
							}
						}else{
							source.put(item[0], item[1]);
						}
					}
				}
			}
		}
		return source;
	}
}

package com.highpay.zoom.spider.utils.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.highpay.zoom.spider.utils.io.MyIOUtils;
import com.highpay.zoom.spider.utils.regex.RegexUtils;

public class HttpGetUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpPostUtils.class);

	/**
	 * 
	 * @author jasonChiu
	 * 
	 * @title 增加https的参数
	 * @param path
	 * @param isHttps
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 * @time 2016年1月22日上午11:08:26
	 * @version 1.0
	 */
	public static String sendGetString(String path,boolean isHttps,boolean isNeedCookie,Map<String,Object> header){
        if(isHttps){
		  HttpConnectionUtils.buildHttpsURLConnection();
		}
        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);

		HttpConnectionUtils.buildHeader(conn, HttpConst.HTTP_GET,header);
		//合并cookie
		HttpCookieUtils.mergeCookie(conn);
        String result ="";
		try {
		   if(conn.getResponseCode()==200){
	            InputStream inStream = conn.getInputStream();   
	            //这样也存在局限性，认为返回的一定是字符串
	            result=new String(MyIOUtils.stream2Byte(inStream), "UTF-8");
	        }else if(HttpConst.STATUS_CODE_REDIRECT.contains(String.valueOf(conn.getResponseCode()))){
	        	String location = conn.getHeaderField("Location");
	        	//这里处理的也存在问题，域名解析与拼装
	        	String domain = RegexUtils.getDomian(path);
	        	String procol = isHttps?"https://":"http://";
	        	if(!location.contains(domain)){
	        		location = procol+domain+location;
	        	}
	        	if(StringUtils.isNotEmpty(location)){
	        		result = sendGetString(location,isHttps,true);
	        	}
	        }
		} catch (Exception e) {
			logger.error("发送get请求失败",e);
		}
		return result;
	}
	public static String sendGetString(String path,boolean isHttps){
		return sendGetString(path, isHttps, true, new HashMap<String, Object>());
	}
	public static String sendGetString(String path,boolean isHttps,boolean isNeedCookie){
		return sendGetString(path, isHttps, isNeedCookie, new HashMap<String, Object>());
	}
	/**
	 * 
	 * @title 发送get请求，返回字符串
	 * 
	 * @author JasonChiu
	 * @time 2016年1月21日下午11:46:31
	 * @version 1.0
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String sendGetString(String path){
		return sendGetString(path,true,true,new HashMap<String,Object>());
	}

	public static HttpResponse sendGet(String path,Map<String,Object> header){
		return sendGet(path,true,header);
	}
	public static HttpResponse sendGet(String path){
		return sendGet(path,true,new HashMap<String,Object>());
	}
	public static HttpResponse sendGet(String path,boolean ishttps,Map<String,Object> header){
		HttpResponse response = new HttpResponse();
		  if(ishttps){
			  HttpConnectionUtils.buildHttpsURLConnection();
			}
	        HttpURLConnection conn = HttpConnectionUtils.getConnection(path);
			HttpConnectionUtils.buildHeader(conn, HttpConst.HTTP_GET,header);
			
			//合并cookie
			HttpConnectionUtils.buildHttpResponseBy(response, conn);
			
			if(HttpConst.STATUS_CODE_REDIRECT.contains(String.valueOf(response.getResponseCode()))){
				String location = response.getResponseHeaderByKey(HttpConst.LOCATION);
	        	String domain = RegexUtils.getDomian(path);
	        	String procol = ishttps?"https://":"http://";
	        	if(!location.contains(domain)){
	        		location = procol+domain+location;
	        	}
	        	if(StringUtils.isNotEmpty(location)){
					sendGet(location,ishttps,header);
	        	}
			}
			return response;
	}
	
}

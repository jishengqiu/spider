package com.highpay.zoom.spider.utils.http;

/**
 * @title  这样做有一个缺点，如果是2个请求（线程），cookie并没有维持
 * @author jisheng
 *
 */
public class CookieContextThreadLocal {

	private static final ThreadLocal<String> cookieThreadLocal = new ThreadLocal<String>();
	
	public static void setCookie(String cookie){
		cookieThreadLocal.set(cookie);
	}
	
	public static String getCookie(){
		return cookieThreadLocal.get();
	}
}

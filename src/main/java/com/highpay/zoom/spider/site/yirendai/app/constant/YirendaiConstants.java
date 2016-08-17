package com.highpay.zoom.spider.site.yirendai.app.constant;

/** 
 * @title 宜人贷抓取需要用到的字段，核心key是AES加解密的关键
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:18:37
 * @version 1.0
 */
public class YirendaiConstants {
	public static String AES_KEY = "0C5E75A210884F61";
	
	//这些都是需要提交给服务器的参数，由于后面我们都会放在map里面，所以给这些常量加个前缀
	public static String KEY_CHANNEL_ID = "4C9EF1676B564240DF6AA684F968E85A";
	
	public static String  KEY_CLIENT_TYPE= "2";
	//研究七生产规则
	public static String  KEY_DEVICE_NO= "e205fa700a1211854044887930b5c68bf1f65c45";
	
	public static String  KEY_MARKET_ID="374";
	
	public static String  KEY_SECRET= "yingonline";
	
}

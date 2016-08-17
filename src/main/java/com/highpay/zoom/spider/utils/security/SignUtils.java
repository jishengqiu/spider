package com.highpay.zoom.spider.utils.security;

import java.util.Map;

import com.highpay.zoom.spider.utils.collections.MyMapUtils;

/**
 * 
 * @title 签名工具类
 * 
 * @author jasonChiu
 * @time 2016年1月7日下午7:26:41
 * @version 1.0
 */
public class SignUtils {
	
	//根据所需的入參生成签名
	public static String generateSign(Map<String, Object> requestMap){
		Map<String, Object> sortMap = MyMapUtils.sortMapByKey(requestMap);
		//1.现将排序好的map转成post提交数据格式字符串
		String params = MyMapUtils.getParamString(sortMap);
		//2.利用MD5加密字符串
		String sign = MD5Utils.encode(params);
		return sign;
	}

}

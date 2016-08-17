package com.highpay.zoom.spider.utils.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	//从字符串中提取域名
	public static String getDomian(String url){
		if(url==null||url.trim().equals("")){
			return "";
	    }
        String host = "";
        Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher matcher = p.matcher(url);  
        if(matcher.find()){
            host = matcher.group();  
        }
        return host;
	}
	public static void main(String[] args) {
		String domain = "http://www.baidu.com/aasas/casasa/dwdw?a=112";
		System.err.println(RegexUtils.getDomian(domain));
	}
}

package com.highpay.zoom.spider.utils.string;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
/**
 * 
 * @title 对于http请求参数问题需要url编码（中文）
 * @author jsonChiu
 * @time 2016年5月21日 下午9:09:25
 * @version 1.0
 */
public class EncoderUtils {
	
	public static String encodeUrlProperty(String value) {
		String result = "";
		try {
			result = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(encodeUrlProperty("account=x3zMUeaBrazoJK1Iqq+zxA=="
						+ "&channelId=4C9EF1676B564240DF6AA684F968E85A&clientType=2&"
						+ "deviceNo=e205fa700a1211854044887930b5c68bf1f65c45&marketId=374&password=b9ut1UXb3kbd2Wvx1pJrpQ=="
						+ "&secret=yingonline"));
	}
}

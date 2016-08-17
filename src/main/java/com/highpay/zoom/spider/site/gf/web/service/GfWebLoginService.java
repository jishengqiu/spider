package com.highpay.zoom.spider.site.gf.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.site.gf.web.constant.GfWebConstant;
import com.highpay.zoom.spider.utils.http.HttpConst;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;
import com.highpay.zoom.spider.utils.http.HttpResponse;
import com.highpay.zoom.spider.utils.io.MyFileUtils;

/**
 * 
 * @title 广发证券
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年4月27日
 * @version 1.0
 */
public final class GfWebLoginService extends BaseLoginService<String>{

	private static final String LOGIN_URL = GfWebConstant.LOGIN_URL;
	private static final String LOGIN_VERIFYCODE_URL = GfWebConstant.LOGIN_VERIFYCODE_URL;
	private static final String LOGIN_ACTION_URL = GfWebConstant.LOGIN_ACTION_URL;

	@Override
	public String getLoginUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,
			String username, String password, String authcode) {
		requestMap.put("error_url", "https://store.gf.com.cn/login.html");
		requestMap.put("login_type", "trade");
		requestMap.put("user_id", username);
		requestMap.put("password", password);
		requestMap.put("ticket", authcode);		
	}
	
	public static void main(String[] args) {
		GfWebLoginService spider = new GfWebLoginService();
    	String url333= "https://store.gf.com.cn/rest/user/session?_gfsrc=newgfw_x_x_home";
		Map<String,Object> requestHeader21 = new HashMap<String, Object>();
		HttpResponse response333= HttpGetUtils.sendGet(url333,requestHeader21);
		System.err.println(response333.getResponseBody());
		
		Map<String,Object> requestHeader22 = new HashMap<String, Object>();
		requestHeader22.put(HttpConst.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
		HttpResponse response = HttpGetUtils.sendGet(LOGIN_VERIFYCODE_URL,requestHeader22);
		MyFileUtils.saveImageToDisk(response.getIn(),"E:\\verifyCode.png");
		System.err.println("请输入验证码：");
		Scanner scanner = new Scanner(System.in);
		String verifyCode = scanner.nextLine();
		System.err.println(verifyCode);
		spider.doLogin("031100011802", "681376", verifyCode);
		
		String url22 = "https://store.gf.com.cn/rest/user/session?_gfsrc=newgfw_x_x_home";
		HttpResponse response22 = HttpGetUtils.sendGet(url22);
		System.err.println(response22.getResponseBody());
		
		String url33= "https://store.gf.com.cn/rest/user/asset/total_value";
		HttpResponse response33= HttpGetUtils.sendGet(url33);
		System.err.println(response33.getResponseBody());
	}
}

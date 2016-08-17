package com.highpay.zoom.spider.site.zlfund.web.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.http.HttpCookieUtils;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;

public class ZlfundWebLoginServiceImpl extends BaseLoginService<String> implements ZlfundWebLoginService {
	private String csrftoken = StringUtils.EMPTY;	
	@Override
	public void doBefore() {
		String url = "https://www.zlfund.cn/accounts/login/";
		HttpGetUtils.sendGetString(url, true, true);
		this.csrftoken = HttpCookieUtils.getCookieByKey("csrftoken");
	}
	
	@Override
	public void buildHeader(Map<String, Object> header) {
		header.put("Referer", "https://www.zlfund.cn/");
	}

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.ZLFUND_LOGIN_URL;
	}
	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("identity", username);
		requestMap.put("password", password);
		requestMap.put("csrfmiddlewaretoken", csrftoken);
	}

	public static void main(String[] args){ 
		ZlfundWebLoginServiceImpl login = new ZlfundWebLoginServiceImpl();
		System.err.println(login.doLogin("429004198902172759", "qiujisheng89", ""));
		ZlfundWebAccountInfoService account = new ZlfundWebAccountInfoServiceImpl();
		account.doAccount();
	}
}

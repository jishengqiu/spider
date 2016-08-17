package com.highpay.zoom.spider.site.niwodai.web.service;

import java.util.Map;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;

public class NiwodaiWebLoginServiceImpl extends BaseLoginService<String> implements NiwodaiWebLoginService {

	@Override
	public void buildHeader(Map<String,Object> headerMap) {
		headerMap.put("Referer", "https://member.niwodai.com/login.html");
	}

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.NIWODAI_LOGIN__URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("username", username);
		requestMap.put("pwd", password);
		requestMap.put("codeType", "0");
		requestMap.put("geetestCode", "geetest");
		requestMap.put("geetest_challenge", "");
		requestMap.put("geetest_validate", "");
		requestMap.put("geetest_seccode", "");
		requestMap.put("channel", "3");
		requestMap.put("imgCode", "");
		requestMap.put("phoneCode", "");		
	}
	public static void main(String[] args) throws Exception {
		NiwodaiWebLoginServiceImpl login = new NiwodaiWebLoginServiceImpl();		
		System.err.println(login.doLogin("13349910969", "qiujisheng89", ""));
		
		String url = "https://member.niwodai.com/member/investorsAjax.do";
		System.err.println(HttpGetUtils.sendGetString(url, true, true));
	}
}

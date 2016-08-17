package com.highpay.zoom.spider.site.fbank.web.service;

import java.util.Map;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;

public class FbankWebLoginServiceImpl extends BaseLoginService<String> implements FbankWebLoginService {

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.FBANK_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("userName", username);
		requestMap.put("password", password);
		requestMap.put("returnUrl", "");		
	}
}

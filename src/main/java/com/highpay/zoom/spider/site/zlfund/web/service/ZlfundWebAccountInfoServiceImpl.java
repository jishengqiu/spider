package com.highpay.zoom.spider.site.zlfund.web.service;

import java.util.HashMap;
import java.util.Map;

import com.highpay.zoom.spider.utils.http.HttpGetUtils;

public class ZlfundWebAccountInfoServiceImpl implements ZlfundWebAccountInfoService{
	private Map<String, Object> header = new HashMap<String, Object>();

	@Override
	public void addHeader() {
		header.put("Referer", "https://www.zlfund.cn/trade/");
	}
	@Override
	public String getAccountInfoUrl() {
		return "https://www.zlfund.cn/accounts/profile/?_=1460780588518";
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public String doAccount() {
		addHeader();
		String result = HttpGetUtils.sendGetString(getAccountInfoUrl(), true, true, header);		
		System.err.println(result);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
}


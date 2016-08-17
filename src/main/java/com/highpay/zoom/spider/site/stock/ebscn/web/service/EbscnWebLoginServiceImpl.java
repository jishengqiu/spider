package com.highpay.zoom.spider.site.stock.ebscn.web.service;

import java.util.HashMap;
import java.util.Map;

import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.http.HttpPostUtils;
import com.highpay.zoom.spider.utils.http.HttpResponse;
import com.highpay.zoom.spider.utils.io.MyFileUtils;

public class EbscnWebLoginServiceImpl extends BaseLoginService<String> implements IEbscnWebLoginService<String> {

	
	
	@Override
	public void doBefore() {
		String captchCodeUrl = "https://sc.ebscn.com/thinkweixinsite/servlet/CaptchaServlet";
		HttpResponse response = HttpPostUtils.sendPost(captchCodeUrl);
		//保存验证码图片
		MyFileUtils.saveImageToDisk(response.getIn(), "D://");
	}

	@Override
	public String getLoginUrl() {
		return "";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap, String username, String password, String authcode) {

	}
	public static void main(String[] args) {
		EbscnWebLoginServiceImpl login = new EbscnWebLoginServiceImpl();
		Map<String,Object> requestMap = new HashMap<String, Object>();
		requestMap.put("funcNo", "1000303");
		requestMap.put("accounttype", 1);
		requestMap.put("account", "22431030");
		requestMap.put("clientinfo", "");
		requestMap.put("jsessionid", "");		
		HttpResponse response  = HttpPostUtils.sendPost("https://sc.ebscn.com/thinkweixinsite/servlet/json", requestMap, true, new HashMap<String, Object>());
		System.out.println(response.getResponseBody());
		
	}
}

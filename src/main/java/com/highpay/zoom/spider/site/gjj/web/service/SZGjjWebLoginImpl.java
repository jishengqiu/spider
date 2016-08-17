package com.highpay.zoom.spider.site.gjj.web.service;

import java.util.Map;
import java.util.Scanner;

import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;
import com.highpay.zoom.spider.utils.http.HttpResponse;
import com.highpay.zoom.spider.utils.io.MyFileUtils;

public class SZGjjWebLoginImpl extends BaseLoginService<String>{

	@Override
	public void doBefore() {
       //访问首页
	}
	
	@Override
	public String getLoginUrl() {
		return "http://app.szzfgjj.com:7001/accountQuery";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("accnum", username);
		requestMap.put("certinum",password);
		requestMap.put("qryflag",1);//1.身份证，公积金账号0.电脑号，身份证号（没有登录成功）
		requestMap.put("verify", authcode);
	}
	public static void main(String[] args) {
		SZGjjWebLoginImpl szLogin = new SZGjjWebLoginImpl();
		HttpGetUtils.sendGet("http://app.szzfgjj.com:7001/pages/alone_ywcx.jsp");
		String authCode = "http://app.szzfgjj.com:7001/pages/code.jsp";
		HttpResponse response = HttpGetUtils.sendGet(authCode);
		MyFileUtils.saveImageToDisk(response.getIn(), "D://sz.png");
		System.err.println("请输入验证码");
		Scanner sc = new Scanner(System.in);
		String authcode = sc.next();
		//System.err.println(szLogin.doLogin("您的公积金账号", "您的身份证号码", authcode));
	}

}

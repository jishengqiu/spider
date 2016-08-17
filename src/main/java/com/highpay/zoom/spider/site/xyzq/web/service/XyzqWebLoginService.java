package com.highpay.zoom.spider.site.xyzq.web.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.http.HttpConst;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;
import com.highpay.zoom.spider.utils.http.HttpResponse;
import com.highpay.zoom.spider.utils.io.MyFileUtils;
import com.highpay.zoom.spider.utils.json.JsonMapper;

/**
 * 
 * @title 兴业证券
 * 
 * @author 邱吉胜
 * @email jisheng_qiu@kingdee.com
 * @time 2016年5月5日
 * @version 1.0
 */
public final class XyzqWebLoginService extends BaseLoginService<String> {

	private static final String LOGIN_URL = XyzqWebConstant.LOGIN_URL;
	private static final String LOGIN_ACTION_URL = XyzqWebConstant.LOGIN_ACTION_URL;
	private static final String VERIFYCODE_URL = XyzqWebConstant.VERIFYCODE_URL;

	
	@Override
	public void doBefore() {
		
	}

	@Override
	public String getLoginUrl() {
		return LOGIN_ACTION_URL;
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("name", username);
		requestMap.put("pwd", password);
		requestMap.put("imgcode", authcode);
		requestMap.put("t", "2");// 1.兴政通 2.资金账号
		requestMap.put("mac", "");
	}
	public boolean isNeedAuthCode(String username){
		//https://estock.xyzq.com.cn/validation/img/inspection?name=170024935&_=1462756527857
		String url = XyzqWebConstant.VALIDATE_USERNAME_URL+"?_="+System.currentTimeMillis()+"&name="+username;
		Map<String,Object> requestMap = new HashMap<String, Object>();
		requestMap.put(HttpConst.ACCEPT, HttpConst.CONTENT_TYPE_JSON);
		requestMap.put(HttpConst.REFERER, LOGIN_URL);
		HttpResponse response = HttpGetUtils.sendGet(url,requestMap);
		String result = response.getResponseBody();
		JsonMapper mapper = JsonMapper.nonEmptyMapper();
		Result  res = mapper.fromJson(result, Result.class);
		if(!"0".equals(res.retNo)){
			return true;
		}
		return false;
	}
	
	@Override
	public void buildHeader(Map<String, Object> map) {
		map.put(HttpConst.ACCEPT, HttpConst.CONTENT_TYPE_JSON);
		map.put(HttpConst.REFERER, LOGIN_URL);
	}

	static class Result{
		private String retNo;
		private String retMsg;
		private String errInfo;
		
		public String getRetNo() {
			return retNo;
		}

		public void setRetNo(String retNo) {
			this.retNo = retNo;
		}

		public String getRetMsg() {
			return retMsg;
		}

		public void setRetMsg(String retMsg) {
			this.retMsg = retMsg;
		}

		public String getErrInfo() {
			return errInfo;
		}

		public void setErrInfo(String errInfo) {
			this.errInfo = errInfo;
		}

		@Override
		public String toString() {
			return "Result [retNo=" + retNo + ", retMsg=" + retMsg
					+ ", errInfo=" + errInfo + "]";
		}	
	}
	public static void main(String[] args) {
		XyzqWebLoginService spider = new XyzqWebLoginService();
		System.err.println("请输入资金账号：");
		Scanner s1c = new Scanner(System.in);
		String username = s1c.next();
		System.err.println("请输入密码：");
		Scanner s2c = new Scanner(System.in);
		String password = s2c.next();
		boolean isNeedAuthCode = spider.isNeedAuthCode(username);
		String authcode =StringUtils.EMPTY;
		if(isNeedAuthCode){
			HttpResponse response = HttpGetUtils.sendGet(VERIFYCODE_URL);
			MyFileUtils.saveImageToDisk(response.getIn(), "E://verifyCode.png");
			System.err.println("请输入验证码");
			Scanner sc = new Scanner(System.in);
			authcode = sc.next();
		}
		spider.doLogin(username, password, authcode);
	}
}

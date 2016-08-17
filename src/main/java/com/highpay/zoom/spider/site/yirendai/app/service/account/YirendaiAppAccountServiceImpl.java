package com.highpay.zoom.spider.site.yirendai.app.service.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.AppUrlConstant;
import com.highpay.zoom.spider.site.yirendai.app.constant.YirendaiConstants;
import com.highpay.zoom.spider.utils.collections.MyMapUtils;
import com.highpay.zoom.spider.utils.http.HttpPostUtils;
import com.highpay.zoom.spider.utils.security.SignUtils;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日下午10:42:35
 * @version 1.0
 */
@Service("yirendaiAppAccountService")
public class YirendaiAppAccountServiceImpl implements YirendaiAppAccountService {

	@Override
	public String getAccountInfoUrl() {
		return AppUrlConstant.YIRENDAI_ACCOUNT_URL;
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);	
		//这里再请求其他接口的时候，是否需要重新请求登录接口，获取最新的token
		requestMap.put("token", "c86a33c8b1594a1aa6fc4cd7572a3e17");
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);
		requestMap.put("sign", SignUtils.generateSign(requestMap));
	}

	@Override
	public String doAccount() {
		Map<String, Object> requestMap  = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result = HttpPostUtils.sendPostString(getAccountInfoUrl(), MyMapUtils.getParamStringEncoder(requestMap),true);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
	
	public static void main(String[] args) {
		YirendaiAppAccountServiceImpl accountService = new YirendaiAppAccountServiceImpl();
		System.out.println(accountService.doAccount());
	}
}

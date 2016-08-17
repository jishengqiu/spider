package com.highpay.zoom.spider.site.yirendai.app.service.login;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.AppUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.site.yirendai.app.constant.YirendaiConstants;
import com.highpay.zoom.spider.utils.security.CryptAESUtils;
import com.highpay.zoom.spider.utils.security.SignUtils;

/** 
 * @title 登录service
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:36
 * @version 1.0
 */

 //{"result":"success","errorCode":"","msg":"","data":{"passportId":"","token":"c86a33c8b1594a1aa6fc4cd7572a3e17","name":"*吉胜","accountStatus":"1","accountType":"1","accountTypeChangeable":"","accountBalance":"0","totalAmount":"","accumulatedIncome":"","rates":"0%","isNew":"","level":"000","yrbRatio":"","inviteCodeRewardCount":"","newUserRewardCount":""}}
@Service("yirendaiLoginService")
public class YirendaiAppLoginServiceImpl extends BaseLoginService<String> implements YirendaiAppLoginService {

	@Override
	public String getLoginUrl() {
		return AppUrlConstant.YIRENDAI_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("account", CryptAESUtils.AES_Encrypt(YirendaiConstants.AES_KEY, username));
		requestMap.put("password",  CryptAESUtils.AES_Encrypt(YirendaiConstants.AES_KEY,password));
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);
		requestMap.put("deviceNo", YirendaiConstants.KEY_DEVICE_NO);
		requestMap.put("marketId", YirendaiConstants.KEY_MARKET_ID);
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);
		requestMap.put("sign", SignUtils.generateSign(requestMap));		
		
	}
}

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
package com.highpay.zoom.spider.site.touna.web.service;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.security.MD5Utils;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
@Service("tounaWebLoginService")
public class TounaWebLoginServiceImpl extends BaseLoginService<String> implements TounaWebLoginService{

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.TOUNA_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("method", "login");
		requestMap.put("username", username);
		requestMap.put("md5Pwd", MD5Utils.encode(password));
		requestMap.put("valicode", authcode);
		requestMap.put("subtime", new Date().getTime());		
	}
}

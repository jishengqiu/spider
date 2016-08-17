/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
package com.highpay.zoom.spider.site.dianrong.web.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.site.dianrong.web.model.DianrongWebLoginModel;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午2:15:06
 * @version 1.0
 */
@Service("dianrongWebLoginService")
public class DianrongWebLoginServiceImpl extends BaseLoginService<DianrongWebLoginModel> implements DianrongWebLoginService{
	
	
	@Override
	public void doBefore() {
		String loginUrl = "https://www.dianrong.com/account/login";
		HttpGetUtils.sendGet(loginUrl);
	}

	@Override
	public String getLoginUrl() {
		return WebUrlConstant.DIANRONG_LOGIN_URL;
	}

	@Override
	public DianrongWebLoginModel toModel(String resultStr) {
		return DianrongWebLoginModel.getInstanceByJson(resultStr);

	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("identity", username);
		requestMap.put("password", password);
		requestMap.put("captcha", authcode);
	}
}

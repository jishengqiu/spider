/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
package com.highpay.zoom.spider.site.dianrong.web.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
@Service("dianrongWebAccountService")
public class DianrongWebAccountServiceImpl implements DianrongWebAccountService {
	@Override
	public String getAccountInfoUrl() {
		return WebUrlConstant.DIANRONG_ACCOUNT_URL;
	}

	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		
	}

	@Override
	public String doAccount() {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result = HttpGetUtils.sendGetString(getAccountInfoUrl(), true, true);
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}

}

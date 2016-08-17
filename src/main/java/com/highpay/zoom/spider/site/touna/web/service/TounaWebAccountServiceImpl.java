/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
package com.highpay.zoom.spider.site.touna.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.WebUrlConstant;
import com.highpay.zoom.spider.utils.collections.MyMapUtils;
import com.highpay.zoom.spider.utils.http.HttpPostUtils;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月22日下午5:50:22
 * @version 1.0
 */
@Service("tounaWebAccountService")
public class TounaWebAccountServiceImpl implements TounaWebAccountService {

	@Override
	public String getAccountInfoUrl() {
		return WebUrlConstant.TOUNA_ONESHOP_URL;
	}

	
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put("method", "loadAccountInfo");
		requestMap.put("subtime", new Date().getTime());
	}

	@Override
	public String doAccount() {
		Map<String,Object> requestMap = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result  = HttpPostUtils.sendPostString(getAccountInfoUrl(), MyMapUtils.getParamStringEncoder(requestMap));

		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}

}

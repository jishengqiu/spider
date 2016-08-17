package com.highpay.zoom.spider.site.dianrong.web.model;

import com.highpay.zoom.spider.utils.json.JsonMapper;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月29日下午12:55:13
 * @version 1.0
 */
public class DianrongWebLoginModel {
	
	public static DianrongWebLoginModel getInstanceByJson(String resultStr){
		System.out.println(resultStr);
		JsonMapper mapper = JsonMapper.nonEmptyMapper();
		return mapper.fromJson(resultStr, DianrongWebLoginModel.class);
	}

}

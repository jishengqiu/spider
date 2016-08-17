package com.highpay.zoom.spider.site.base.inter;

import java.util.Map;

public interface IBaseAccountInfoService<T> {
	
	public String getAccountInfoUrl();
		
	public void buildRequestParam(Map<String,Object> requestMap);
	
	public String doAccount();
	
	public T toModel(String resultStr);
}

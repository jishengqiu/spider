package com.highpay.zoom.spider.site.base.inter;

import java.util.Map;


public interface IBaseLoginSevice<T> {
	
	public void doBefore();
	
	public void doPullVerifyCodeImageOutputStream();
	
	public void buildHeader(Map<String,Object> header);
	
	public T toModel(String resultStr);
	
	public boolean isHttps();
	
	public boolean isRedirect302();
	
	public void doAfter();
}

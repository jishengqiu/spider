package com.highpay.zoom.spider.site.base.inter;

import java.util.HashMap;
import java.util.Map;

import com.highpay.zoom.spider.utils.http.HttpPostUtils;
import com.highpay.zoom.spider.utils.http.HttpResponse;

public abstract class BaseLoginService<T> implements IBaseLoginSevice<T>{
	//父类默认登录之前什么都不做,一般是登录之前，请求保存cookie
	public void doBefore(){}
	//父类默认登录之前什么都不做,一般登录请求时，需要指明http请求的来源
	public void buildHeader(Map<String,Object> map){}
	
	public void doPullVerifyCodeImageOutputStream(){
		
	}
	
	public abstract String getLoginUrl();
	
	public abstract void buildLoginParam(Map<String,Object> requestMap,String username,String password,String authcode);
	
	public void buildRequestParam(Map<String,Object> requestMap){}
	
	public T doLogin(String username,String password,String authcode){
		doBefore();
		Map<String,Object> header = new HashMap<String,Object>();
		//收集头信息
		buildHeader(header);
		Map<String,Object> requestMap = new HashMap<String,Object>();
		buildLoginParam(requestMap, username, password, authcode);
		HttpResponse response = HttpPostUtils.sendPost(getLoginUrl(), requestMap, isHttps(),header);
		//父类组合登录请求步骤
		System.err.println(response.getResponseBody());
		return toModel(response.getResponseBody());
	}
	
	public T toModel(T resultStr){
		return resultStr;
	}
	
	public  boolean isHttps(){
		return true;
	}
	
	public  boolean isRedirect302(){
		return true;
	}
	@Override
	public void doAfter() {
		
	}
}

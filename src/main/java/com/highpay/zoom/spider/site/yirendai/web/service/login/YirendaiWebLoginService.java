package com.highpay.zoom.spider.site.yirendai.web.service.login;

import com.highpay.zoom.spider.site.base.inter.IBaseLoginSevice;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:31
 * @version 1.0
 */
public interface YirendaiWebLoginService{
	
	public void generateAuthCode();
	
	public String getCmsHeaderInfo();
}

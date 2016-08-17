package com.highpay.zoom.spider.site.asyn.task;
/**
 * 
 * @title 异步抓取基类
 * 
 * @author jsonChiu
 * @time 2016年5月21日 下午9:16:18
 * @version 1.0
 */
public abstract class BaseAsynSpiderServiceImpl implements IAsynSpiderService{

	public abstract void doBefore();
	
	@Override
	public void doSpider() {

	}
	public abstract void doAfter();

}

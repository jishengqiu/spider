package com.highpay.zoom.spider.site.asyn.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @title 这里最好使用有界的线程池，防止出现内存溢出
 * 
 * 1.加了几个关注返回值的方法
 * 2.任务逻辑，我这里采用，异步执行任务，任何拿到任务结果，如果有就入库；没有不做任何操作
 * 
 * @author jsonChiu
 * @time 2016年5月21日 下午10:31:51
 * @version 1.0
 */
public class AsynSpiderExecutorService {
	private static final Logger logger = LoggerFactory.getLogger(AsynSpiderExecutorService.class);

	private final static ExecutorService executor = Executors.newCachedThreadPool();
	
	public void doSpider(final IAsynSpiderService spider){
		Runnable task = new Runnable() {
			
			@Override
			public void run() {
				spider.doSpider();
			}
		};
		executor.execute(task);
	}
	public static void submit(Runnable task) {
		executor.submit(task);
	}
	
	// 异步任务的关键在Future类,关注返回值
	public static <T> Future<T> submit(Callable<T> callable) {
		return executor.submit(callable);
    }  

	// 异步任务的关键在Future类,关注返回值
	public static Future<?> submitFuture(Runnable task) {
		return executor.submit(task);
	}

	// 异步任务的关键在Future类,关注返回值
	public static Object submitTask(Runnable task, long timeout) {
		try {
			return executor.submit(task).get(timeout, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			logger.error("10获取任务");
		}
		return null;
	}

	// 默认设置超时时间10s
	public static Object submitTask(Runnable task) {
		return submitTask(task, 10000);
	}

}

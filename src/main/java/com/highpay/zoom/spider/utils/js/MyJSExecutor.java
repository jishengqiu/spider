package com.highpay.zoom.spider.utils.js;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 
 * @title 调用js
 * 
 * @author jsonChiu
 * @time 2016年5月22日 上午9:48:40
 * @version 1.0
 */
public class MyJSExecutor {
	/**
	 * 
	 * @param fileFullName 文件全路径
	 * @param method js中定义的方法
	 * @param params 可变传入的参数
	 * @return js执行后的字符串
	 */
	public static String execute(String fileFullName,String method,String ...params){
		ScriptEngineManager manager = new ScriptEngineManager();   
		ScriptEngine engine = manager.getEngineByName("javascript");
		FileReader reader;
		Object result = new Object();
		try {
			reader = new FileReader(fileFullName);
			 // 执行指定脚本   
			engine.eval(reader);   

			if(engine instanceof Invocable) {    
				Invocable invoke = (Invocable)engine;    
				// 调用merge方法，并传入两个参数    
				result  = invoke.invokeFunction(method, params);
			}
			reader.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
	    return result.toString();  
	}
	public static void main(String[] args) {
		MyJSExecutor exec = new MyJSExecutor();
		String fileFullName = exec.getClass().getResource("/encryptjs/").getPath()+"encrypt_des_szsi.js";
		
		String encryptString = exec.execute(fileFullName, "encrypt", "1234987hnisihnisi","Qiujisheng89");
		
		System.err.println(encryptString);
		String hexString  = exec.execute(fileFullName, "stringToHex", encryptString);
		System.err.println(hexString);

	}
}

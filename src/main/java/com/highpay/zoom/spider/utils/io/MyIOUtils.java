package com.highpay.zoom.spider.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyIOUtils {
	/**
	 * 
	 * @title 将inputStream转字节数字
	 * 
	 * @author JasonChiu
	 * @time 2016年1月21日下午11:40:53
	 * @version 1.0
	 */
	public static byte[] stream2Byte(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		byte[] b = new byte[1024];
		while ((len = is.read(b, 0, b.length)) != -1) {
			baos.write(b, 0, len);
		}
		byte[] buffer = baos.toByteArray();
		return buffer;
	}
	/**
	 * @title 将inputStream转成文件，保存的指定路径
	 * 
	 * @param inputStream
	 * @param path
	 * @param fileName
	 */
	public static void stream2File(InputStream inputStream,String path,String fileName){
		
        byte[] data = new byte[1024];  
        int len = 0;  
        FileOutputStream fileOutputStream = null;  
        try {  
        	String filename = path +fileName;
            fileOutputStream = new FileOutputStream("D:\\authcode.png");  
            while ((len = inputStream.read(data)) != -1) {  
                fileOutputStream.write(data, 0, len);  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                    inputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (fileOutputStream != null) {  
                try {  
                    fileOutputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
}

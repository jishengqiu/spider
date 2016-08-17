package com.highpay.zoom.spider.utils.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyFileUtils {
	//把从服务器获得图片的输入流InputStream写到本地磁盘
	  public static void saveImageToDisk(InputStream inputStream,String path) {
		byte[] data = new byte[1024];
	    int len = 0;
	    FileOutputStream fileOutputStream = null;
	    try {
	      fileOutputStream = new FileOutputStream(path);
	      while ((len = inputStream.read(data)) != -1) {
	        fileOutputStream.write(data, 0, len);
	      }
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    } finally {

	      if (inputStream != null) {
	        try {
	          inputStream.close();
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }
	      if (fileOutputStream != null) {
	        try {
	          fileOutputStream.close();
	        } catch (IOException e) {
	          // TODO Auto-generated catch block
	          e.printStackTrace();
	        }
	      }

	    }

	  }
}
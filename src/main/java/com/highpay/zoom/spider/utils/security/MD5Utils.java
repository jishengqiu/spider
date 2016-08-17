package com.highpay.zoom.spider.utils.security;

import java.security.MessageDigest;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:46
 * @version 1.0
 */
public class MD5Utils
{
  public static String encode(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("md5");
      localMessageDigest.update(paramString.getBytes());
      byte[] arrayOfByte = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer("");
      for (int i = 0; i < arrayOfByte.length; i++)
      {
        int j = arrayOfByte[i];
        if (j < 0) {
          j += 256;
        }
        if (j < 16) {
          localStringBuffer.append("0");
        }
        localStringBuffer.append(Integer.toHexString(j));
      }
      String str = localStringBuffer.toString();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
}

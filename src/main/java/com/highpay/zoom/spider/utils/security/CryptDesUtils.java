/**
 * @author jasonChiu
 * @title
 * @time 2016年1月28日下午7:33:24
 * @version 1.0
 */
package com.highpay.zoom.spider.utils.security;

import java.util.HashMap;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月28日下午7:33:24
 * @version 1.0
 */
public class CryptDesUtils {
	  private static final String encoding = "utf-8";

	  public static String decode(String paramString,String iv,String secretKey)throws Exception
	  {
	    DESedeKeySpec localDESedeKeySpec = new DESedeKeySpec(secretKey.getBytes());
	    SecretKey localSecretKey = SecretKeyFactory.getInstance("desede").generateSecret(localDESedeKeySpec);
	    Cipher localCipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
	    localCipher.init(2, localSecretKey, new IvParameterSpec(iv.getBytes()));
	    return new String(localCipher.doFinal(Base64.decodeBase64(paramString)), "utf-8");
	  }

	  public static String encode(String paramString,String iv,String secretKey) throws Exception
	  {
	    DESedeKeySpec localDESedeKeySpec = new DESedeKeySpec(secretKey.getBytes());
	    SecretKey localSecretKey = SecretKeyFactory.getInstance("desede").generateSecret(localDESedeKeySpec);
	    Cipher localCipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
	    localCipher.init(1, localSecretKey, new IvParameterSpec(iv.getBytes()));
	    return Base64.encodeBase64String(localCipher.doFinal(paramString.getBytes("utf-8")));
	  }
}

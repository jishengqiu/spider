package com.highpay.zoom.spider.utils.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/** 
 * @title AES加解密工具类
 * 
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:15:56
 * @version 1.0
 */
public class CryptAESUtils { 
  
    private static final String AESTYPE ="AES/ECB/PKCS5Padding"; 
 
    public static String AES_Encrypt(String keyStr, String plainText) { 
        byte[] encrypt = null; 
        try{ 
            Key key = generateKey(keyStr); 
            Cipher cipher = Cipher.getInstance(AESTYPE); 
            cipher.init(Cipher.ENCRYPT_MODE, key); 
            encrypt = cipher.doFinal(plainText.getBytes());     
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        return new String(Base64.encodeBase64(encrypt)); 
    } 
 
    public static String AES_Decrypt(String keyStr, String encryptData) {
        byte[] decrypt = null; 
        try{ 
            Key key = generateKey(keyStr); 
            Cipher cipher = Cipher.getInstance(AESTYPE); 
            cipher.init(Cipher.DECRYPT_MODE, key); 
            decrypt = cipher.doFinal(Base64.decodeBase64(encryptData)); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
        return new String(decrypt).trim(); 
    } 
 
    private static Key generateKey(String key)throws Exception{ 
        try{            
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES"); 
            return keySpec; 
        }catch(Exception e){ 
            e.printStackTrace(); 
            throw e; 
        } 
 
    } 
 
    public static void main(String[] args) { 
         
        String keyStr = "UITN25LMUQC436IM";  
 
        String plainText = "this is a string will be AES_Encrypt";
         
        String encText = AES_Encrypt(keyStr, plainText);
        String decString = AES_Decrypt(keyStr, encText); 
         
        System.out.println(encText); 
        System.out.println(decString); 
 
    } 
}
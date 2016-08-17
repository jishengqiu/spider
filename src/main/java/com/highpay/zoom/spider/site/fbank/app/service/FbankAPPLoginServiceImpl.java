package com.highpay.zoom.spider.site.fbank.app.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.highpay.zoom.spider.site.base.constant.AppUrlConstant;
import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.site.fbank.app.constant.FBankAppConstant;
import com.highpay.zoom.spider.utils.security.CryptDesUtils;
import com.highpay.zoom.spider.utils.string.EncoderUtils;

public class FbankAPPLoginServiceImpl extends BaseLoginService<String> implements FbankAPPLoginService {
	private static Logger logger = LoggerFactory.getLogger(FbankAPPLoginServiceImpl.class);

	@Override
	public String getLoginUrl() {
		return AppUrlConstant.FBANK_LOGIN_URL;
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mobile", username);
	    map.put("password", password);
	    map.put("version", 242);
	    map.put("platform", "android");
	    map.put("channel", "guanwang");
	    map.put("deviceId", "0");
	    map.put("requestId", UUID.randomUUID().toString().replaceAll("-", ""));
	    map.put("requestMs", String.valueOf(System.currentTimeMillis()));
	    map.put("td_deviceId", "unknow");
	    
		JSONObject object = new JSONObject(map);
		String desStr ="";
		try {
			 desStr =  EncoderUtils.encodeUrlProperty(CryptDesUtils.encode(object.toString(),FBankAppConstant.iv,FBankAppConstant.secretKey));
		} catch (Exception e) {
			logger.error("DES加密APP登录参数失败",e);
		}
		requestMap.put("cipherData", desStr);
	}
}

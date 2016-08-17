package com.highpay.zoom.spider.site.ppmoney.web.service;

import java.util.Map;

import com.highpay.zoom.spider.site.base.inter.BaseLoginService;
import com.highpay.zoom.spider.utils.http.HttpCookieUtils;
import com.highpay.zoom.spider.utils.http.HttpGetUtils;

public class PpmoneyWebLoginServiceImpl extends BaseLoginService<String> implements PpmoneyWebLoginService {

	@Override
	public void doBefore() {
		String cookieStr = "_jzqckmp=1; InitUser=%7B%22value%22%3A%7B%22PayDate%22%3Anull%2C%22Amount%22%3Anull%2C%22NickName%22%3Anull%2C%22NotReadLetterCount%22%3A0%2C%22IsAdmin%22%3Afalse%2C%22AdminUrl%22%3Anull%2C%22NowTime%22%3A8492%7D%2C%22expires%22%3A1458650451307%7D; ASP.NET_SessionId=45g4aucilo2rsfderlvagmns; PrjCount=%7B%22value%22%3A%7B%22PrjNum%22%3A%5B%5B5%2C0%2C54%5D%2C%5B1%2C1%2C3%2C0%2C0%2C0%2C0%2C0%2C54%5D%5D%2C%22DailyBaoInfo%22%3A%7B%22TotalTransInCount%22%3Anull%2C%22MillionProfit%22%3A%221.60%22%2C%22InvestableAmount%22%3A0%2C%22AnnualizedReturn%22%3A%225.85%25%22%7D%7D%2C%22expires%22%3A1458639880657%7D; _qzja=1.283321121.1458639471361.1458639471361.1458639471362.1458639509020.1458639850702..1.0.10.1; _qzjb=1.1458639471361.10.0.0.1.1; _qzjc=1; _qzjto=10.1.0; _jzqa=1.1333688635148178200.1458639471.1458639471.1458639471.1; _jzqc=1; _jzqb=1.20.10.1458639471.1; Hm_lvt_23402e8e24686e10855967941542b317=1458639471; Hm_lpvt_23402e8e24686e10855967941542b317=1458639851; CNZZDATA5025873=cnzz_eid%3D89672580-1458636650-%26ntime%3D1458636650; NTKF_T2D_CLIENTID=guestTEMP3EEE-A351-39DE-D8DB-9DB018CB3A9D; nTalk_CACHE_DATA={uid:kf_9150_ISME9754_guestTEMP3EEE-A351-39,tid:1458639476933988}; _ga=GA1.2.1486810315.1458639472";
		HttpCookieUtils.mergeCookie(cookieStr);
		HttpGetUtils.sendGet(getLoginUrl());
	}

	@Override
	public String getLoginUrl() {
		return "https://www.ppmoney.com/login/";
	}

	@Override
	public void buildLoginParam(Map<String, Object> requestMap,String username, String password, String authcode) {
		requestMap.put("Phone", username);
		requestMap.put("Password", password);
		requestMap.put("returnUrl", "");
		requestMap.put("RandCode", "");
		
	}
}

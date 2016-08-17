package com.highpay.zoom.spider.site.base.constant;

public class  AppUrlConstant{

	//=======================宜人贷=========================//
	//https://yingapi.yirendai.com
	private static String YIRENDAI_BASE_URL = "https://ying.yixin.com";	
	
	public static String YIRENDAI_LOGIN_URL = YIRENDAI_BASE_URL + "/p2puserController/p2puserLogin.action";
	
	public static String YIRENDAI_ACCOUNT_URL = YIRENDAI_BASE_URL + "/p2paccountController/myAccountInfoV2.action";
	
	public static String YIRENDAI_MYORDER_URL =YIRENDAI_BASE_URL + "/p2paccountController/myOrderList.action";
    //======================玖富=================================//
	private static final String FBANK_BASE_URL = "http://api.9f.cn";
	public static final String FBANK_LOGIN_URL = FBANK_BASE_URL+"/v240/login/login";

}

package com.highpay.zoom.spider.utils.security;

import com.highpay.zoom.spider.utils.http.HttpPostUtils;


/** 
 * @title 测试登录类
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:53
 * @version 1.0
 */
public class TestAES {
	public static void main(String[] args) {
		String account = "";
		String password = "";
		String message = "x3zMUeaBrazoJK1Iqq+zxA==";
		String key = "0C5E75A210884F61";
		
		String sign_right = "e9fcfb3f0a740a754af7c56de98af645";

		String url = "https://ying.yixin.com/p2puserController/p2puserLogin.action";
		
		/*ArrayList localArrayList = new ArrayList();
	    localArrayList.add(new BasicNameValuePair("account", CryptAES.AES_Encrypt(key, account)));
	    localArrayList.add(new BasicNameValuePair("channelId", "4C9EF1676B564240DF6AA684F968E85A"));
	    localArrayList.add(new BasicNameValuePair("clientType", "2"));
	    localArrayList.add(new BasicNameValuePair("deviceNo", "e205fa700a1211854044887930b5c68bf1f65c45"));
	    localArrayList.add(new BasicNameValuePair("marketId", "374"));
	    localArrayList.add(new BasicNameValuePair("password", CryptAES.AES_Encrypt(key, password)));
	    localArrayList.add(new BasicNameValuePair("secret", "yingonline"));*/

	    StringBuilder sb = new StringBuilder("account=x3zMUeaBrazoJK1Iqq+zxA=="
	    		+ "&channelId=4C9EF1676B564240DF6AA684F968E85A&clientType=2&"
	    		+ "deviceNo=e205fa700a1211854044887930b5c68bf1f65c45&marketId=374&password=b9ut1UXb3kbd2Wvx1pJrpQ=="
	    		+ "&secret=yingonline");
	    String sign = MD5Utils.encode(sb.toString());
	    System.out.println(sign);
	   // localArrayList.add(new BasicNameValuePair("sign", sign));
	    StringBuilder sb2 = new StringBuilder("account=x3zMUeaBrazoJK1Iqq%2BzxA%3D%3D"
	    		+ "&channelId=4C9EF1676B564240DF6AA684F968E85A&clientType=2&"
	    		+ "deviceNo=e205fa700a1211854044887930b5c68bf1f65c45&marketId=374&password=b9ut1UXb3kbd2Wvx1pJrpQ%3D%3D"
	    		+ "&secret=yingonline&sign=e9fcfb3f0a740a754af7c56de98af645");
	   
	    
	    try {
	    	System.out.println(sb2.toString());
			String result = HttpPostUtils.sendPostString(url, sb2.toString());
			System.out.println(result);
		} catch(Exception e){
			e.printStackTrace();
		}
	}

}

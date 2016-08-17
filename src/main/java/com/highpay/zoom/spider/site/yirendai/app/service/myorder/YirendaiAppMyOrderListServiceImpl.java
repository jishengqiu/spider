package com.highpay.zoom.spider.site.yirendai.app.service.myorder;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.site.base.constant.AppUrlConstant;
import com.highpay.zoom.spider.site.yirendai.app.constant.YirendaiConstants;
import com.highpay.zoom.spider.utils.collections.MyMapUtils;
import com.highpay.zoom.spider.utils.http.HttpPostUtils;
import com.highpay.zoom.spider.utils.security.SignUtils;

/** 
 * @title
 * @author JasonChiu
 * @time 2016年1月8日上午12:15:05
 * @version 1.0
 */
@Service("YirendaiAppMyOrderListService")
public class YirendaiAppMyOrderListServiceImpl implements YirendaiAppMyOrderListService {

	
	@Override
	public String getAccountInfoUrl() {
		return AppUrlConstant.YIRENDAI_MYORDER_URL;
	}
	
	@Override
	public void buildRequestParam(Map<String, Object> requestMap) {
		requestMap.put("token", "c86a33c8b1594a1aa6fc4cd7572a3e17");
		requestMap.put("pageNo", "1");
		requestMap.put("channelId", YirendaiConstants.KEY_CHANNEL_ID);
		requestMap.put("clientType", YirendaiConstants.KEY_CLIENT_TYPE);
		requestMap.put("secret", YirendaiConstants.KEY_SECRET);

		//发现这个接口的签名与登录时的签名不一致，重新生成调用该接口的签名
		requestMap.put("sign", SignUtils.generateSign(requestMap));
	}

	/**
	 * {"result":"success","errorCode":"","msg":"","data":{"updateStatus":"","allCount":"1","pageTotal":"1","pageNo":"1","p2pserviceList":[{"finOrderNo":"15e415af661a45769e56bc163b41cd17","finOrderStatus":"已结束","productId":"0031","productType":"0","p2pserviceName":"宜定盈1月期","p2pserviceNo":"13","expectedIncome":"10","investDate":"2015-09-02","investAmount":"100","startCalcDate":"2015-09-05","incomeAmount":"0.83","expectedIncomeAmount":"0.83","expectedIncomeAmountNow":"0.00","frozenDate":"2015-10-05","frozenTime":"1","exitType":"退回到划扣银行卡","days":"0","exitNextOpt":"0","canApply":"2","nowDate":"2016-01-08"}]}}
	 */
	@Override
	public String doAccount() {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		buildRequestParam(requestMap);
		String result = HttpPostUtils.sendPostString(getAccountInfoUrl(), MyMapUtils.getParamStringEncoder(requestMap));
		return result;
	}

	@Override
	public String toModel(String resultStr) {
		return resultStr;
	}
	public static void main(String[] args) {
		System.out.println(new YirendaiAppMyOrderListServiceImpl().doAccount());
		
	}

}

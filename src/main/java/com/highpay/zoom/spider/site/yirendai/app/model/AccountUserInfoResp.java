/**
 * 
 */
package com.highpay.zoom.spider.site.yirendai.app.model;

/**
 * @author jasonChiu
 * @title
 * @time 2016年1月12日上午10:31:57
 * @version 1.0
 */
public class AccountUserInfoResp extends BaseResp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5522585758438498349L;
	
	private AccountUserInfoModel data;

	public AccountUserInfoModel getData() {
		return data;
	}

	public void setData(AccountUserInfoModel data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "AccountUserInfoResp [data=" + data.toString() + "]";
	}
	
}

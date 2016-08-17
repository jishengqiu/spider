package com.highpay.zoom.spider.utils.http;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.highpay.zoom.spider.utils.io.MyIOUtils;

public class HttpResponse {
	
	private int responseCode;
	private String location;
	private InputStream in;
	private String responseBody;
	private String url;
	private Map<String,Object> responseHeaderMap = new HashMap<String, Object>();
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	public String getResponseBody() {
		String responseBody = StringUtils.EMPTY;
		try {
			if(this.in!=null){
				responseBody=new String(MyIOUtils.stream2Byte(in), "UTF-8");
			} 
		}catch (Exception e){
			e.printStackTrace();
		}
		return responseBody;
	}
	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
	public void setResponseHeader(String key,String value){
		responseHeaderMap.put(key, value);
	}
	public String getResponseHeaderByKey(String key){
		return responseHeaderMap.get(key).toString();
	}
	@Override
	public String toString() {
		return "HttpResponse [responseCode=" + responseCode + ", location="
				+ location + ", in=" + in + ", responseBody=" + responseBody
				+ ", url=" + url + ", responseHeaderMap=" + responseHeaderMap
				+ "]";
	}
	
}

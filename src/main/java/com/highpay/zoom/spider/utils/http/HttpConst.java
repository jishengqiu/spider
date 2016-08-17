package com.highpay.zoom.spider.utils.http;

/**
 * @title http相关的常量
 * 
 * @author jisheng
 *
 */
public final class HttpConst {

	public static final String PROTOCOL_HTTP = "http";
	public static final String PROTOCOL_HTTPS = "https";

	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";

	public static final String EXPIRES = "Expires";
	public static final String USER_AGENT = "User-Agent";
	public static final String CACHE_CONTROL = "Cache-Control";

	/**
	 * 从MyHttpResponse或MyHttpResponse中得到cookie值的key
	 */
	public static final String COOKIE = "Cookie";

	/**
	 * 从urlConnection请求中取得cookie值的key
	 */
	public static final String SET_COOKIE = "Set-Cookie";
	public static final String CONTENT_LENGTH = "Content-Length";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String LOCATION = "Location";
	public static final String ACCEPT_ENCODING = "Accept-Encoding";
	public static final String ACCEPT_CHARSET = "Accept-Charset";
	public static final String ACCEPT_LANGUAGE = "Accept-Language";
	public static final String CONNECTION = "Connection";
	public static final String ACCEPT = "Accept";

	public static final String HOST = "Host";
	public static final String ETAG = "ETag";
	public static final String DATE = "Date";
	public static final String IF_MATCH = "If-Match";
	public static final String IF_MODIFIED_SINCE = "If-Modified-Since";
	public static final String IF_NONE_MATCH = "If-None-Match";
	public static final String REFRESH = "Refresh";
	public static final String REFERER = "Referer";
	public static final String ORIGIN = "Origin";

	/**
	 * 请求资源类型，默认为请求html，其它还有json、 流、头部信息等
	 */
	public static final int RESPONSE_RESOURCE_TYPE_HTML = 0;
	public static final int RESPONSE_RESOURCE_TYPE_INPUTSTREAM = 1;
	public static final int RESPONSE_RESOURCE_TYPE_HEADER = 2;

	public static final String CONTENT_TYPE_TEXT_HTML = "text/html";
	public static final String CONTENT_TYPE_IMAGE = "image/jpeg";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String CONTENT_TYPE_PLAIN = "text/plain";

	public static final String CONTENT_ENCODING_GZIP = "gzip";


	/**
	 * 状态码
	 */
	public static final int STATUS_CODE_SUCCESS = 200;
	public static final String STATUS_CODE_REDIRECT = "301,302,303,304,305";

}

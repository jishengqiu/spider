package com.highpay.zoom.spider.utils.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/** 
 * @title java https公钥信任
 * 
 * @author JasonChiu
 * @time 2016年1月7日上午1:16:08
 * @version 1.0
 */
public final class DefaultTrustManager implements X509TrustManager {

	@Override
	public void checkClientTrusted(final X509Certificate[] x509Certificates, final String s) throws CertificateException {

	}

	@Override
	public void checkServerTrusted(final X509Certificate[] x509Certificates, final String s) throws CertificateException {

	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
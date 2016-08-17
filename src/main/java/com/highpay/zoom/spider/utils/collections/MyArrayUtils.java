package com.highpay.zoom.spider.utils.collections;

import org.apache.commons.lang3.ArrayUtils;
/**
 * 
 * @title 组合优于继承，数组工具类的补充
 * 
 * @author jsonChiu
 * @time 2016年5月21日 下午9:07:54
 * @version 1.0
 */
public final class MyArrayUtils{

	/**
	 * 字符串数组是否包含字符串，不区分大小写.
	 * 
	 * @param array
	 * @param obj
	 * @return
	 */
	public static boolean containsIgnoreCase(String[] array, String obj) {

		if (ArrayUtils.isEmpty(array) || obj == null) {
			return false;
		}
		for (String a : array) {
			if (a.equalsIgnoreCase(obj.trim())) {
				return true;
			}
		}
		return false;
	}
}

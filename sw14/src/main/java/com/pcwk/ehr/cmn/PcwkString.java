package com.pcwk.ehr.cmn;

import com.google.common.base.Strings;

public class PcwkString {

	/**
	 * value가 0이면 defaultValue 리턴
	 * @param value
	 * @param defaultValue
	 * @return int
	 */
	public static int nvlZero(int value, int defaultValue) {
		return (value==0)?defaultValue:value;
	}
	
	/**
	 * null 또는 빈 문자열인지 체크
	 * @param str
	 * @return true/false
	 */
	public static boolean isNullOrEmpty(String str) {
		return Strings.isNullOrEmpty(str);
	}
	

	/**
	 * null->빈문자열
	 * @param str
	 * @return ""
	 */
	public static String nullToEmpty(String str) {
		return Strings.nullToEmpty(str);
	}	
}

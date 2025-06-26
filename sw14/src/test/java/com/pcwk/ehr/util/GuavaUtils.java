package com.pcwk.ehr.util;

import com.google.common.base.Strings;

public class GuavaUtils {

	public static int nvlZero(int value, int defaultValue) {
		return (value==0)?defaultValue:value;
	}
	
	public static boolean isNullOrEmpty(String str) {
		return Strings.isNullOrEmpty(str);
	}
	
	
	//null->빈문자열
	public static String nullToEmpty(String str) {
		//return  (str == null) ? "" : str;
		return Strings.nullToEmpty(str);
	}
	
	public static void main(String[] args) {
		
		//Null테스트
		System.out.println("NullOrEmpty: "+isNullOrEmpty(null));
		System.out.println("NullOrEmpty: "+isNullOrEmpty(""));
		
		System.out.println("nullToEmpty: "+nullToEmpty(null));
		
		System.out.println("nvlZero: "+nvlZero(0,10));
	}

}

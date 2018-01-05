package com.jshstudy.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil
{
	/**
	 * 해당 패턴으로 String 체크
	 * @return 
	 */
	public static boolean checkPattern(String pattern, String s){
		if(StringUtil.isEmpty(pattern)) {
			LogUtil.dLog("","Pattern String is Empty");
			return false;
		}

		if(StringUtil.isEmpty(s)) {
			return false;
		}
		
		Matcher checkMatcher = Pattern.compile(pattern).matcher(s);
		return checkMatcher.matches();
	}
	
	/**
	 * 이메일이 올바른지 조회
	 */
	public static boolean checkEmail(String checkStr){
		return checkPattern("^[_0-9a-zA-Z-.]+@.+\\.[_0-9a-zA-Z-]+", checkStr);
	}
	
	/**
	 * 전화번호 체크 자릿수 ?
	 * 3자리거나 4자리 체크
	 */
	public static boolean checkPhone(String checkStr)
	{
		return checkPattern("\\d{3}\\d?\\d{4}\\d{4}", checkStr);
	}
	
	/**
	 * 영문/ 숫자 인지 체크
	 */
	public static boolean checkEngNum(String checkStr)
	{
		return checkPattern("^[a-zA-Z0-9]+$", checkStr);
	}

	/**
	 * 영소문/ 숫자 인지 체크
	 */
	public static boolean checkSmallEngNum(String checkStr)
	{
		return checkPattern("^[a-z0-9]+$", checkStr);
	}
	
	/**
	 * 숫자인지 체 크
	 */
	public static boolean checkNum (String checkStr)
	{
		return checkPattern("^[0-9]+$", checkStr);
	}

}

package com.jshstudy.common.util;

/**
 * Created by shun6 on 2017-10-03.
 */

public class StringUtil {

    public static String getSplitLastString(String str, String divider){
        String[] strs = str.split(divider);

        if(strs.length>0){
            return strs[strs.length-1];
        }

        return "";
    }

    public static boolean isEmpty(String str){
        return str == null || str.isEmpty();
    }


    public static boolean isEqual(String str1, String str2){
        if(str1 == null || str2 == null) return false;
        return str1.equals(str2);
    }
}

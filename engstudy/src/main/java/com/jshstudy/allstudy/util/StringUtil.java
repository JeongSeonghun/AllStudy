package com.jshstudy.allstudy.util;

import java.util.ArrayList;

/**
 * Created by shun6 on 2017-10-03.
 */

public class StringUtil extends com.jshstudy.common.util.StringUtil{
    public static ArrayList<String> engDivider(String engStr){
        ArrayList<String> list = new ArrayList<>();

        int idxStart = engStr.lastIndexOf("(");
        int idxEnd = engStr.lastIndexOf(")");

        String str = engStr.substring(idxStart + 1, idxEnd);
        String[] listTemp = str.split(",");

        if(listTemp == null || listTemp.length<=0){
            return null;
        }

        String strSt = engStr.substring(0, idxStart); // "(" 전까지, start <= x <idx
        String strEd = engStr.substring(idxEnd + 1);

        String strBase = strSt + strEd;

        strBase = strBase.replaceAll("  ", " ").trim();

        list.add(strBase);

        for(String temp : listTemp){
            String temp2 = strSt + temp + strEd;
            temp2 = temp2.replaceAll("  ", " ").trim();

            list.add(temp2);
        }

        return list;
    }
}

package jsh.example.com.allstudy.util;


import android.util.Log;

import jsh.example.com.allstudy.BuildConfig;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class LogUtil {
    private static boolean isDebug = BuildConfig.DEBUG;

    public static void DLog(String target, String msg){
        if(isDebug){
            Log.d(target, msg);
        }
    }

    public static void ELog(String target, String msg){
        if(isDebug){
            Log.e(target, msg);
        }
    }
}

package jsh.example.com.common.util;


import android.util.Log;

import jsh.example.com.allstudy.BuildConfig;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class LogUtil {
    private static final String TAG = "LogUtil";

    private static boolean isDebug = BuildConfig.DEBUG;

    private static boolean isSave = false;

    public static void DLog(String msg){
        DLog(TAG, msg);
    }

    public static void DLog(String target, String msg){
        if(isDebug){
            Log.d(target, msg);
        }
    }

    public static void DwLog(String target, String msg){
        if(isSave){
            // save
            FileLogUtil.getInstance().append("D:"+target+" :: "+msg);
        }

        DLog(target, msg);
    }

    public static void ELog(String msg){
        ELog(TAG, msg);
    }

    public static void ELog(String target, String msg){
        if(isDebug){
            Log.e(target, msg);
        }
    }

    public static void EwLog(String target, String msg){
        if(isSave){
            // save
            FileLogUtil.getInstance().append("E:"+target+" :: "+msg);
        }

        ELog(target, msg);
    }
}

package com.jshstudy.allstudy;

import com.jshstudy.allstudy.constant.C;

/**
 * Created by EMGRAM on 2017-12-22.
 */

public class AppConfig {
    public static boolean isDebug = BuildConfig.DEBUG;

    public static String buildType = BuildConfig.BUILD_TYPE;
    public static boolean isPaid = C.Config.FRABOR_PAID.equals(BuildConfig.FLAVOR);
}

package com.jshstudy.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * Created by Hyune on 16. 10. 17..
 */
public class AppUtil {

    /**
     * 해당 PackageName으로 PackageInfo 정보를 조회하여 반환한다
     * @param ctx
     * @param packageName
     * @return 인스톨 되지 않을경우 null 반환
     */
    public static PackageInfo getOtherAppInfo(Context ctx, String packageName){
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(packageName, 0);
            return info;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 해당 PackageName으로 Application Version Code 정보를 조회하여 반환한다
     * @param ctx
     * @param packageName
     * @return 인스톨 되지 않을경우 -1 반환
     */
    public static int getOtherAppVersionCode(Context ctx, String packageName){
        PackageInfo info = getOtherAppInfo(ctx, packageName);

        if(info!=null){
            return info.versionCode;
        }

        return -1;
    }

    /**
     * 해당 PackageName으로 Application Version Name 정보를 조회하여 반환한다
     * @param ctx
     * @param packageName
     * @return 인스톨 되지 않을경우 null 반환
     */
    public static String getOtherAppVersionName(Context ctx, String packageName){
        PackageInfo info = getOtherAppInfo(ctx, packageName);

        if(info!=null){
            return info.versionName;
        }

        return null;
    }

    public boolean isForgroundState(Context ctx, String packageName) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {

        } else {
            ActivityManager am = (ActivityManager) ctx.getSystemService(Context.ACTIVITY_SERVICE);

            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    String foregroundPackage = info.pkgList[0];
                    LogUtil.DLog(getClass().getSimpleName(), String.format("Forground App Package=%s - My Package=%s", foregroundPackage, packageName));

                    return StringUtil.isEqual(packageName, foregroundPackage);
                }
            }
        }

        return false;
    }
}

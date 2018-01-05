package com.jshstudy.common.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.PowerManager;

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
                    LogUtil.dLog(getClass().getSimpleName(), String.format("Forground App Package=%s - My Package=%s", foregroundPackage, packageName));

                    return StringUtil.isEqual(packageName, foregroundPackage);
                }
            }
        }

        return false;
    }

    /**
     * 잠금 해제
     * <uses-permission android:name="android.permission.DISABLE_KEYGUARD" /> 잘 안먹힘...
     * KeyguardManager.KeyguardLock.disableKeyguard() 보안 없는 잠금화면만 가능
     * DeviceAdminReceiver,  DevicePolicyManager 이용시 보안 있는 잠금화면 가능
     * https://m.blog.naver.com/PostView.nhn?blogId=oculus213&logNo=220234237655&proxyReferer=http%3A%2F%2Fwww.google.co.kr%2Furl%3Fsa%3Dt%26rct%3Dj%26q%3D%26esrc%3Ds%26source%3Dweb%26cd%3D5%26ved%3D0ahUKEwiAzrO2143XAhWMErwKHQz4BKYQFgg9MAQ%26url%3Dhttp%253A%252F%252Fm.blog.naver.com%252Foculus213%252F220234237655%26usg%3DAOvVaw2bVHFCLxOtudsn32xlcsen
     *
     */
    // <uses-permission android:name="android.permission.WAKE_LOCK" /> 필요
    // 화면만 킴... 잠금은 해제안 됨...
    // release 안 할시 배터리 관련 이슈 있음... -> 주 활용 : 절전 상태 무시 cpu 유지, 개발자사이트에서는 사용 추천하지 않음.
    // PARTIAL_WAKE_LOCK cpu on
    // SCREEN_DIM_WAKE_LOCK cpu on, screen dim
    // SCREEN_BRIGHT_WAKE_LOCK cpu on, screen bright
    // FULL_WAKE_LOCK cpu on, screen bright, keyboard bright
    // PARTIAL_WAKE_LOCK 제외 3항목은 추가 2항목 "|"으로 사용 가능
    // ACQUIRE_CAUSES_WAKEUP 강제로 화면 킴
    //http://aroundck.tistory.com/48
    public static void wakeup(Context context){
        PowerManager powerManager = (PowerManager)context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK |
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                        PowerManager.ON_AFTER_RELEASE, "hello");

        wakeLock.acquire();

        wakeLock.release();
    }
}

package com.jshstudy.common.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.jshstudy.common.exception.CustomUcaughtExceptionHandler;
import com.jshstudy.common.util.FileLogUtil;
import com.jshstudy.common.util.LogUtil;

/**
 * Created by EMGRAM on 2017-11-02.
 */

public class BaseApplication extends Application implements Application.ActivityLifecycleCallbacks{

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

        FileLogUtil.getInstance().init();

        Thread.setDefaultUncaughtExceptionHandler(new CustomUcaughtExceptionHandler());
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtil.DLog("onActivityCreated : "+ activity.getClass().getName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtil.DLog("onActivityStarted : "+ activity.getClass().getName());
    }

    @Override
    public void onActivityResumed(Activity activity) {
        LogUtil.DLog("onActivityResumed : "+ activity.getClass().getName());
    }

    @Override
    public void onActivityPaused(Activity activity) {
        LogUtil.DLog("onActivityPaused : "+ activity.getClass().getName());
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtil.DLog("onActivityStopped : "+ activity.getClass().getName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtil.DLog("onActivitySaveInstanceState : "+ activity.getClass().getName());
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtil.DLog("onActivityDestroyed : "+ activity.getClass().getName());
    }
}

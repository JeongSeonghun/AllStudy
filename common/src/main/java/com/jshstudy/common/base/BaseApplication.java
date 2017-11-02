package com.jshstudy.common.base;

import android.app.Application;

import com.jshstudy.common.exception.CustomUcaughtExceptionHandler;

/**
 * Created by EMGRAM on 2017-11-02.
 */

public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new CustomUcaughtExceptionHandler());
    }


}

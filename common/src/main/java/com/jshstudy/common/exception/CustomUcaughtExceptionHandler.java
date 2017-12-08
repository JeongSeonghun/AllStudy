package com.jshstudy.common.exception;

import com.jshstudy.common.AppConfig;
import com.jshstudy.common.util.FileLogUtil;
import com.jshstudy.common.util.LogUtil;

/**
 * Created by EMGRAM on 2017-11-02.
 * try/catch 외 exception 처리
 */

public class CustomUcaughtExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, final Throwable e) {
        Thread exThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String log;

                log = "final "+e;
                e.getStackTrace();
                if(AppConfig.isSaveLog) FileLogUtil.getInstance().append(log);
            }
        });

        exThread.start();
    }
}

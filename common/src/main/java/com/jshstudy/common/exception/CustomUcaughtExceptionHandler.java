package com.jshstudy.common.exception;

import com.jshstudy.common.util.FileLogUtil;

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

                FileLogUtil.getInstance().append(log);
            }
        });

        exThread.start();
    }
}

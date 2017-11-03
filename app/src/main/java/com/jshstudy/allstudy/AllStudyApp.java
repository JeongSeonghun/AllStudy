package com.jshstudy.allstudy;

import android.app.Application;

import com.jshstudy.common.util.FileLogUtil;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class AllStudyApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        FileLogUtil.getInstance().init(this);
    }

}

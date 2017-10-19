package jsh.example.com.allstudy;

import android.app.Application;

import jsh.example.com.common.util.FileLogUtil;

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

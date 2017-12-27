package com.jshstudy.allstudy;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.jshstudy.allstudy.data.AllStudyDB;
import com.jshstudy.allstudy.data.LangStudyDB;
import com.jshstudy.common.util.FileLogUtil;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class AllStudyApp extends Application{

    AllStudyDB allStudyDB;
    LangStudyDB langStudyDB;

    @Override
    public void onCreate() {
        super.onCreate();

        FileLogUtil.getInstance().init(this);

        //allStudyDB = new AllStudyDB(this);
        init();
    }

    private void init(){
        langStudyDB = new LangStudyDB(this);

        registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    public LangStudyDB getLangStudyDB(){
        return langStudyDB;
    }

    ActivityLifecycleCallbacks lifecycleCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {

        }
    };
}

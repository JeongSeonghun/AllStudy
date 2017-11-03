package com.jshstudy.common.base;

import android.app.Activity;
import android.os.Bundle;

import com.jshstudy.common.util.LogUtil;

/**
 * Created by EMGRAM on 2017-11-02.
 */

public class BaseActivity extends Activity{
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.DLog(TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.DLog(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.DLog(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.DLog(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.DLog(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.DLog(TAG, "onDestroy");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.DLog(TAG, "onBackPressed");
    }

    // device home , app list, etc buttons
    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.DLog(TAG, "onUserLeaveHint");
    }
}
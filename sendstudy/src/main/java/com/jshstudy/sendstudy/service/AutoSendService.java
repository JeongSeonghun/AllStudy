package com.jshstudy.sendstudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.jshstudy.common.util.LogUtil;

/**
 * Created by EMGRAM on 2017-10-26.
 */

public class AutoSendService extends Service{
    private final String WAKEUP_ENGSTUDY = "com.jshstudy.allstudy.wakeup";

    public static final String KEY_TIME = "time";


    IBinder mBinder = new MyBinder();

    // 외부로 데이터를 전달하려면 바인더 사용
    class MyBinder extends Binder {
        AutoSendService getService() { // 서비스 객체를 리턴
            return AutoSendService.this;
        }
    }

    private long time = 5000;
    public void setTime(long time){
        this.time= time;
    }


    // bind : background
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.DLog("service onBind");
//        return null;
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.DLog("service onCreate");
        sendHandler.sendEmptyMessageDelayed(0, time);
    }

    // start : app
//    @IntDef(value = {Service.START_FLAG_REDELIVERY, Service.START_FLAG_RETRY}, flag = true)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private Handler sendHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(msg.what == 0){
                sendWakeUp();
            }
        }
    };

    private void sendWakeUp(){

        LogUtil.DLog("sendBroadcast wakeup");
        Intent isEngencyOpen= new Intent(WAKEUP_ENGSTUDY);
        sendBroadcast(isEngencyOpen);
    }
}

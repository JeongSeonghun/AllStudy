package com.jshstudy.communicatestudy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.jshstudy.common.util.LogUtil;

/**
 * Created by EMGRAM on 2018-01-02.
 */

public class ComService extends Service {

    private onMsgReceiveListener msgReceiveListener = null;

    boolean loop = true;
    int sleep = 1000;

    public interface onMsgReceiveListener{
        void receiveMessage(String msg);
    }

    IBinder mBinder = new MyBinder();

    // 외부로 데이터를 전달하려면 바인더 사용
    public class MyBinder extends Binder {
        public ComService getService() { // 서비스 객체를 리턴
            return ComService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.DLog("service onBind()");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.DLog("service onCreate()");
        LoopTimer.start();
    }

    Thread LoopTimer = new Thread(new Runnable() {
        @Override
        public void run() {
            int cnt = 0;
            while (loop){
                if(msgReceiveListener != null){
                    msgReceiveListener.receiveMessage(String.valueOf(cnt));
                }

                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LogUtil.DLog("service cnt -> "+cnt);
                if(cnt<10){
                    cnt++;
                }else{
                    cnt = 0;
                }
            }
        }
    });

    public void setMsgRecieveListener(onMsgReceiveListener listener){
        this.msgReceiveListener = listener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.DLog("service onDestroy()");
        if(LoopTimer != null){
            loop = false;
            LoopTimer = null;
        }
    }
}

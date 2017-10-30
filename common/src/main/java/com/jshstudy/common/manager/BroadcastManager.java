package com.jshstudy.common.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-10-30.
 */

public class BroadcastManager {
    public static String ACTION_ = "";

    ArrayList<String> actions;

    public void addAction(String action){
        actions.add(action);
    }

    private IntentFilter getIntentFilter(){
        IntentFilter filter = new IntentFilter();
        for(String action : actions){
            filter.addAction(action);
        }
        return filter;
    }

    // app 내에서 만??
    public void LocalBroadcastRegist(Context context, BroadcastReceiver receiver){
        LocalBroadcastManager.getInstance(context)
                .registerReceiver(receiver, getIntentFilter());
    }
    // app 내에만 만 전송?
    public void sendLocalBroadcast(Context context, Intent intent){
        LocalBroadcastManager.getInstance(context)
                .sendBroadcast(intent);
    }
    public void sendLocalBroadcast(Context context, String action){
        Intent intent = new Intent();
        intent.setAction(action);

        sendLocalBroadcast(context, intent);
    }

    public void broadcastRegist(Context context, BroadcastReceiver receiver){
        context.registerReceiver(receiver, getIntentFilter());
    }

    public void sendBroadcast(Context context, Intent intent){
        context.sendBroadcast(intent);
    }

    public void sendBroadcast(Context context, String action){
        Intent intent = new Intent();
        intent.setAction(action);

        sendBroadcast(context, intent);
    }




}

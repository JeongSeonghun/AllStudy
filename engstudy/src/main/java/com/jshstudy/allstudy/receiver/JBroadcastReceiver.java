package com.jshstudy.allstudy.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jshstudy.allstudy.ui.IntroActivity;
import com.jshstudy.common.util.AppUtil;
import com.jshstudy.common.util.LogUtil;

/**
 * Created by EMGRAM on 2017-10-26.
 */

public class JBroadcastReceiver extends BroadcastReceiver{
    private final String WAKEUP_ENGSTUDY = "com.jshstudy.allstudy.wakeup";

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtil.DLog("receive : "+ intent.getAction());
        if(intent.getAction().equals(WAKEUP_ENGSTUDY)){

            AppUtil.wakeup(context);

            Intent introIntent = new Intent(context, IntroActivity.class);
            introIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            introIntent.putExtra("wake", true);

            context.startActivity(introIntent);
        }
    }
}

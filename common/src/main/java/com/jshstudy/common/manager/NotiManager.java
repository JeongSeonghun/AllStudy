package com.jshstudy.common.manager;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by EMGRAM on 2017-11-06.
 * https://developer.android.com/guide/topics/ui/notifiers/notifications.html#ManageChannels
 */

public class NotiManager {

    public NotificationManager createNotiManager(Context ctx){
        return (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    // android 8.0(api 26) 이상 부터는 채널 구현 필요, 8.0은 나중에...
    // Importance, Sound, Lights, Vibration, Show on lockscreen, Override do not disturb
    public Notification createNoti(Context ctx, int idIcon, String title, String msg){
        // Notification noti = new Notification(idIcon,msg,time) -> api 11 deprecate
        // setSmallIcon()setContentTitle()setContentText()필수  -> api 11이상부터 Builder 지원
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx);
        builder.setSmallIcon(idIcon);
        builder.setContentTitle(title);
        builder.setContentText(msg);

        return builder.build();
    }

    public void setNoti(Context ctx, NotificationCompat.Builder builder, Activity act){
        Intent resultIntent = new Intent(ctx, act.getClass());
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        ctx,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(resultPendingIntent);
    }



    public void sendNoti(NotificationManager manager, Notification noti, int id){

        manager.notify(id, noti);
    }
}

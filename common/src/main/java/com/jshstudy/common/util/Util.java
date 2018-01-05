package com.jshstudy.common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.provider.Settings;
import android.view.Display;

import java.io.ByteArrayOutputStream;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class Util {

    /**
     * device display size
     * @param activity
     * @return
     */
    public Point displaySize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        LogUtil.dLog("window width / height : "+ width + " / "+ height);
        return size;
    }

    /**
     * bitmap -> bytes
     * @param bitmap
     * @param format
     * @return
     */
    public byte[] bitmapToByte(Bitmap bitmap, Bitmap.CompressFormat format){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        return outputStream.toByteArray();
    }

    /**
     * bytes -> bitmap
     * @param bytes
     * @param options
     * @return
     */
    public Bitmap bytesToBitmap(byte[] bytes, BitmapFactory.Options options){
        if(options == null){
            return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        }else{
            return BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        }
    }

    /**
     * Automatic bright control check and check cancel
     */
    public void checkBrightMode(Context ctx){
        int brightMode = Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC;
        try {
            brightMode = Settings.System.getInt(ctx.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if(brightMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC){
            Settings.System.putInt(ctx.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        }
    }

    public int getBrightLevel(Context ctx){
        int brightLevel = -1;

        try {
            brightLevel = Settings.System.getInt(ctx.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        return brightLevel;
    }

    public void setBrightLevel(Context ctx, int level){
        Settings.System.putInt(ctx.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, level);
    }

}

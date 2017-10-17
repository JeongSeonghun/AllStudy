package jsh.example.com.allstudy.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;

import java.io.ByteArrayOutputStream;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class Util {

    public Point displaySize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        LogUtil.DLog("window width / height : "+ width + " / "+ height);
        return size;
    }

    public byte[] bitmapToByte(Bitmap bitmap, Bitmap.CompressFormat format){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        return outputStream.toByteArray();
    }

    public Bitmap bytesToBitmap(byte[] bytes, BitmapFactory.Options options){
        if(options == null){
            return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        }else{
            return BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        }
    }


}

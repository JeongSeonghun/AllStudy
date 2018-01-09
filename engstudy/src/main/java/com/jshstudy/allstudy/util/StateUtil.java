package com.jshstudy.allstudy.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by EMGRAM on 2018-01-09.
 */

public class StateUtil {


    // 연결 상태 모니터링 시 <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/> 브로드캐스트 수신
    // 이전에 중단된 업데이트나 다운로드 진행시..?? 브로드캐스트 사용??
    // getNetworkInfo deprecated
    public boolean isUseNetwork (Activity act) {
        ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    // ConnectivityManager TYPE_MOBILE TYPE_WIFI TYPE_WIMAX(wibro)
    // 갤럭시탭(SHW-M180K)은 KT 와이브로 버젼용 단말기로 출시되어 TYPE_WIMAX 통해 통신상태 여부를 판단한다.
    public int getNetType(Activity act){
        ConnectivityManager cm = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo!=null){
            return netInfo.getType();
        }else{
            return -1;
        }

    }

}

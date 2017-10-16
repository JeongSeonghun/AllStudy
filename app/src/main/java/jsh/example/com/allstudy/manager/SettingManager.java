package jsh.example.com.allstudy.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by EMGRAM on 2017-10-12.
 */

public class SettingManager {

    private static SettingManager settingManager;
    private Context ctx;
    private String prefName = "setting";

    private SettingManager(Context ctx){
        this.ctx = ctx;
    }

    public static SettingManager getInstance(Context context){
        if(settingManager == null){
            settingManager = new SettingManager(context);
        }

        return settingManager;
    }

    public void putString(String key, String param){
        SharedPreferences pref = ctx.getSharedPreferences(prefName,0);

        SharedPreferences.Editor editor = pref.edit();

        editor.putString(key, param);

        // commit api 1 동기, apply api 9 비동기 성공 여부 반환. apply 가 빠르다네요...
        // apply 적용 중일 경우 apply가 다 끝나기 전까지 commit 실행 안됨.
        //editor.commit();
        editor.apply();

    }

    public void putInt(String key, int param){
        SharedPreferences pref = ctx.getSharedPreferences(prefName,0);

        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(key, param);

        //editor.commit();
        editor.apply();

    }

    public String getString(String key){
        SharedPreferences pref = ctx.getSharedPreferences(prefName, 0);

        return pref.getString(key, "");
    }

    public int getInt(String key){
        SharedPreferences pref = ctx.getSharedPreferences(prefName, 0);

        return pref.getInt(key, -1);
    }
}

package com.jshstudy.contentsave.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.SeekBar;

import com.jshstudy.common.manager.SettingManager;
import com.jshstudy.common.util.Util;
import com.jshstudy.contentsave.R;

public class SystemSetActivity extends Activity {

    private SeekBar seekBar;
    private SettingManager settingManager;
    Util util;
    private int brightVal = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingManager = SettingManager.getInstance(this);
        util = new Util();

        init();


    }

    private void init(){
        // seekbar height is difference. vector. theme issue.
        // if this activity use AppcompatActivity. not compare api version and use Seekbar
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            setContentView(R.layout.activity_system_set);

            seekBar = (SeekBar)findViewById(R.id.sb_set1);
        }else{
            setContentView(R.layout.activity_system_set_4x);

            seekBar = (AppCompatSeekBar)findViewById(R.id.sb_set1_4x);
        }


        brightInit();


    }

    private void brightInit(){
        brightVal = settingManager.getInt(getString(R.string.key_set_bright));

        util.checkBrightMode(this);
        //255
        if(brightVal<0){

            brightVal = util.getBrightLevel(this);
        }else{
            util.setBrightLevel(this, brightVal);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                brightVal = progress;
                settingManager.putInt(getString(R.string.key_set_bright), brightVal);
                util.setBrightLevel(SystemSetActivity.this, brightVal);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


}

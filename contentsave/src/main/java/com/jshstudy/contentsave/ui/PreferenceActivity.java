package com.jshstudy.contentsave.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jshstudy.common.manager.SettingManager;
import com.jshstudy.common.util.PatternUtil;
import com.jshstudy.contentsave.R;

public class PreferenceActivity extends Activity implements View.OnClickListener{

    private SeekBar sb_num_auto;
    private TextView tv_num_auto;
    private EditText et_pref_str_save;
    private Button btn_save_pref;

    private SettingManager settingManager;

    private final String AUTO_SAVE_TEST = "auto_save_num";
    private final String SAVE_TEST = "save_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preference);

        init();
    }

    private void init(){
        sb_num_auto = (SeekBar)findViewById(R.id.sb_num_auto);
        tv_num_auto = (TextView)findViewById(R.id.tv_num_auto);
        et_pref_str_save = (EditText)findViewById(R.id.et_pref_str_save);
        btn_save_pref = (Button)findViewById(R.id.btn_save_pref);

        sb_num_auto.setOnSeekBarChangeListener(seekBarChangeListener);
        btn_save_pref.setOnClickListener(this);

        sb_num_auto.setMax(100);

        settingManager = SettingManager.getInstance(PreferenceActivity.this);

        load();
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            tv_num_auto.setText("num : "+progress+"%");

            settingManager.putInt(AUTO_SAVE_TEST, progress);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void load(){
        final int auto_num;
        final int num;

        auto_num = settingManager.getInt(AUTO_SAVE_TEST);
        num = settingManager.getInt(SAVE_TEST);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sb_num_auto.setProgress(auto_num);

                tv_num_auto.setText("num : "+auto_num+"%");

                et_pref_str_save.setText(String.valueOf(num));
            }
        });
    }

    private void save(){
        settingManager.putInt(AUTO_SAVE_TEST, sb_num_auto.getProgress());

        String tempInt = et_pref_str_save.getText().toString();
        final int saveNum;

        if(!tempInt.isEmpty()&& PatternUtil.checkNum(tempInt)){
            saveNum = Integer.valueOf(tempInt);
        }else{
            saveNum = -1;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    et_pref_str_save.setText("num : "+saveNum+"%");
                }
            });
        }

        settingManager.putInt(SAVE_TEST, saveNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_pref:
                save();
                break;
        }
    }
}

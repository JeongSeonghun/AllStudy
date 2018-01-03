package com.jshstudy.allstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jshstudy.allstudy.BuildConfig;
import com.jshstudy.allstudy.R;

public class SettingActivity extends AppCompatActivity {

    private TextView tv_version_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUi();
        initData();
    }

    private void initUi(){
        tv_version_setting = (TextView)findViewById(R.id.tv_version_setting);
    }

    private void initData(){
        String version = BuildConfig.VERSION_NAME+"("+BuildConfig.BUILD_TYPE+")";
        tv_version_setting.setText(version);
    }
}
package com.jshstudy.allstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jshstudy.allstudy.BuildConfig;
import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.EngStudyDB;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_version_setting;
    private Button btn_init_success_setting;
    private EngStudyDB engStudyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initUi();
        initData();
    }

    private void initUi(){
        tv_version_setting = (TextView)findViewById(R.id.tv_version_setting);
        btn_init_success_setting = (Button)findViewById(R.id.btn_init_success_setting);
    }

    private void initData(){
        String version = BuildConfig.VERSION_NAME+"("+BuildConfig.BUILD_TYPE+")";
        tv_version_setting.setText(version);

        engStudyDB = new EngStudyDB(this);
    }

    private void initSuccessCnt(){
        engStudyDB.updateSuccessInit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_init_success_setting:
                initSuccessCnt();
                break;
        }
    }
}

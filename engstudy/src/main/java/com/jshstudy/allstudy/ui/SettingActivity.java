package com.jshstudy.allstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jshstudy.allstudy.BuildConfig;
import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.EngBaseInput;
import com.jshstudy.allstudy.data.EngStudyDB;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_version_setting;
    private Button btn_init_success_setting;
    private Button btn_init_setting;
    private Button btn_init_data_setting;

    private EngStudyDB engStudyDB;

    private boolean isLock = false;

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
        btn_init_setting = (Button)findViewById(R.id.btn_init_setting);
        btn_init_data_setting = (Button)findViewById(R.id.btn_init_data_setting);

        btn_init_success_setting.setOnClickListener(this);
        btn_init_setting.setOnClickListener(this);
        btn_init_data_setting.setOnClickListener(this);
    }

    private void initData(){
        String version = BuildConfig.VERSION_NAME+"("+BuildConfig.BUILD_TYPE+")";
        tv_version_setting.setText(version);

        engStudyDB = new EngStudyDB(this);
    }

    private void initSuccessCnt(){
        engStudyDB.updateSuccessInit();
    }

    private void initWordData(){
        lock(true);
        engStudyDB.deleteWordAll();
        EngBaseInput baseInput = new EngBaseInput();
        baseInput.init(this);
        lock(false);
    }

    private void lock(boolean isLock){
        this.isLock = isLock;

        if(isLock){
            
        }
    }

    private Thread lockTimer = new Thread(new Runnable() {
        @Override
        public void run() {
            if(isLock){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isLock = false;
            }
        }
    });

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_init_success_setting:
                initSuccessCnt();
                break;
            case R.id.btn_init_setting:
                break;
            case R.id.btn_init_data_setting:
                initWordData();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(isLock){
            return;
        }
        super.onBackPressed();
    }
}

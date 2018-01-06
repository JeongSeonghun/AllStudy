package com.jshstudy.allstudy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.ui.engstudy.EngStudyActivity;
import com.jshstudy.allstudy.ui.engstudy.EngChkWordActivity;
import com.jshstudy.allstudy.ui.engstudy.SearchEngActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi(){
        findViewById(R.id.btn_eng_start).setOnClickListener(this);
        findViewById(R.id.btn_word_chk_start).setOnClickListener(this);
        findViewById(R.id.btn_eng_search_start).setOnClickListener(this);
        findViewById(R.id.btn_setting_start).setOnClickListener(this);
        findViewById(R.id.btn_user_info_start).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_eng_start:
                goToAct(EngStudyActivity.class);
                break;
            case R.id.btn_word_chk_start:
                goToAct(EngChkWordActivity.class);
                break;
            case R.id.btn_eng_search_start:
                goToAct(SearchEngActivity.class);
                break;
            case R.id.btn_setting_start:
                goToAct(SettingActivity.class);
                break;
            case R.id.btn_user_info_start:
                goToAct(UserInfoActivity.class);
                break;
        }
    }

    private void goToAct(Class act){
        Intent intentAct = new Intent(this, act);
        startActivity(intentAct);
    }

}

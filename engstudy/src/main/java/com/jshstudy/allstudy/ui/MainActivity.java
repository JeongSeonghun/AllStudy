package com.jshstudy.allstudy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.ui.engstudy.EngCheckActivity;
import com.jshstudy.allstudy.ui.engstudy.EngSearchActivity;
import com.jshstudy.allstudy.ui.engstudy.EngStudyActivity;
import com.jshstudy.allstudy.ui.engstudy.EngWordChkActivity;
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
        findViewById(R.id.btn_db_start).setOnClickListener(this);
        findViewById(R.id.btn_eng_chk_start).setOnClickListener(this);
        findViewById(R.id.btn_word_chk_start).setOnClickListener(this);
        findViewById(R.id.btn_eng_search_start).setOnClickListener(this);
        findViewById(R.id.btn_eng_search_start2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_eng_start:
                goToEng();
                break;
            case R.id.btn_db_start:
                goToDB();
                break;
            case R.id.btn_eng_chk_start:
                goToAct(EngCheckActivity.class);
                break;
            case R.id.btn_word_chk_start:
                goToAct(EngWordChkActivity.class);
                break;
            case R.id.btn_eng_search_start:
                goToAct(EngSearchActivity.class);
                break;
            case R.id.btn_eng_search_start2:
                goToAct(SearchEngActivity.class);
                break;
        }
    }

    private void goToAct(Class act){
        Intent intentAct = new Intent(this, act);
        startActivity(intentAct);
    }

    private void goToEng(){
        Intent intent = new Intent(this, EngStudyActivity.class);
        startActivity(intent);
    }

    private void goToDB(){
        Intent intent = new Intent(this, DBStudyActivity.class);
        startActivity(intent);
    }
}

package com.jshstudy.communicatestudy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jshstudy.communicatestudy.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btn_start_bt;
    Button btn_start_br;
    Button btn_start_web;
    Button btn_start_service;
    Button btn_start_acticity;
    Button btn_start_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initUI(){
        btn_start_bt = (Button)findViewById(R.id.btn_start_bt);
        btn_start_br = (Button)findViewById(R.id.btn_start_br);
        btn_start_web = (Button)findViewById(R.id.btn_start_web);
        btn_start_service = (Button)findViewById(R.id.btn_start_service);
        btn_start_acticity = (Button)findViewById(R.id.btn_start_activity);
        btn_start_fragment = (Button)findViewById(R.id.btn_start_fragment);

        btn_start_bt.setOnClickListener(this);
        btn_start_br.setOnClickListener(this);
        btn_start_web.setOnClickListener(this);
        btn_start_service.setOnClickListener(this);
        btn_start_acticity.setOnClickListener(this);
        btn_start_fragment.setOnClickListener(this);
    }

    private void startAct(Class act){
        Intent intentAct = new Intent(this, act);
        startActivity(intentAct);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start_bt:
                break;
            case R.id.btn_start_br:
                break;
            case R.id.btn_start_web:
                break;
            case R.id.btn_start_service:
                startAct(ComServActivity.class);
                break;
            case R.id.btn_start_activity:
                startAct(ComAct1Activity.class);
                break;
            case R.id.btn_start_fragment:
                startAct(ComFragActivity.class);
                break;
        }
    }
}

package com.jshstudy.contentsave.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jshstudy.contentsave.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        findViewById(R.id.btn_run_db).setOnClickListener(this);
        findViewById(R.id.btn_run_preference).setOnClickListener(this);
    }

    private void startAct(Class act){
        Intent intent = new Intent(this, act);

        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_run_db:
                startAct(DBActivity.class);
                break;
            case R.id.btn_run_preference:
                startAct(PreferenceActivity.class);
                break;
        }
    }
}

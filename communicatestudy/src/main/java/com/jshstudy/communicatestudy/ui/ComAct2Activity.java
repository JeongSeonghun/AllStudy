package com.jshstudy.communicatestudy.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jshstudy.common.util.PatternUtil;
import com.jshstudy.communicatestudy.R;
import com.jshstudy.communicatestudy.data.CmData;

public class ComAct2Activity extends AppCompatActivity implements View.OnClickListener{

    private TextView int_act2rec_tv;
    private TextView str_act2rec_tv;
    private EditText res_act2send_et;
    private EditText resint_act2send_et;
    private Button act2send_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_act2);

        initUI();
        initData();
    }

    private void initUI(){
        int_act2rec_tv = (TextView)findViewById(R.id.int_act2rec_tv);
        str_act2rec_tv = (TextView)findViewById(R.id.str_act2rec_tv);
        res_act2send_et = (EditText)findViewById(R.id.res_act2send_et);
        resint_act2send_et = (EditText)findViewById(R.id.resint_act2send_et);
        act2send_btn = (Button)findViewById(R.id.act2send_btn);

        act2send_btn.setOnClickListener(this);
    }

    private void initData(){
        Intent intentRec = getIntent();

        int recInt = intentRec.getIntExtra(CmData.CmActData.KEY_SEND_INT, -1);
        String recString = intentRec.getStringExtra(CmData.CmActData.KEY_SEND_STRING);

        int_act2rec_tv.setText(recInt);
        if(recString !=null&&!recString.isEmpty()){
            str_act2rec_tv.setText(recString);
        }

    }


    // Standard activity result: operation canceled. RESULT_CANCELED    = 0;
    // Standard activity result: operation succeeded. RESULT_OK           = -1;
    // Start of user-defined activity results. RESULT_FIRST_USER   = 1;
    private void resultFinish(){
        String strInt = resint_act2send_et.getText().toString();

        if(PatternUtil.checkNum(strInt)){
            int resInt = Integer.valueOf(strInt);
            Intent intentRes = new Intent();
            intentRes.putExtra(CmData.CmActData.KEY_SEND_RESINT, resInt);
            setResult(RESULT_OK, intentRes);
        }else{
            setResult(RESULT_OK);
        }

        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act2send_btn:
                resultFinish();
                break;
        }
    }
}

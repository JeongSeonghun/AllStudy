package com.jshstudy.communicatestudy.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jshstudy.common.util.PatternUtil;
import com.jshstudy.common.util.StringUtil;
import com.jshstudy.communicatestudy.R;
import com.jshstudy.communicatestudy.data.CmData;
import com.jshstudy.communicatestudy.data.ParcData;
import com.jshstudy.communicatestudy.data.SeriData;

public class ComAct1Activity extends AppCompatActivity implements View.OnClickListener{

    private EditText req_act1send_et;
    private EditText int_act1send_et;
    private EditText str_act1send_et;

    private Button send_act1req_btn;

    private TextView res_act1res_tv;
    private TextView resreq_act1res_tv;
    private TextView recint_act1res_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_act1);

        initUI();
    }

    private void initUI(){
        req_act1send_et = (EditText)findViewById(R.id.req_act1send_et);
        int_act1send_et = (EditText)findViewById(R.id.int_act1send_et);
        str_act1send_et = (EditText)findViewById(R.id.str_act1send_et);
        send_act1req_btn = (Button)findViewById(R.id.send_act1req_btn);
        res_act1res_tv = (TextView)findViewById(R.id.res_act1res_tv);
        resreq_act1res_tv = (TextView)findViewById(R.id.resreq_act1res_tv);
        recint_act1res_tv = (TextView)findViewById(R.id.recint_act1res_tv);

        send_act1req_btn.setOnClickListener(this);
    }

    private void sendStart(){
        String strReq;
        String strInt;
        String sendStr;

        strReq = req_act1send_et.getText().toString();
        strInt = int_act1send_et.getText().toString();
        sendStr = str_act1send_et.getText().toString();

        if(!PatternUtil.checkNum(strReq))return;
        int req = Integer.valueOf(strReq);
        if(!PatternUtil.checkNum(strInt))return;
        int sendInt = Integer.valueOf(strInt);


        Intent intentAct = new Intent(ComAct1Activity.this, ComAct2Activity.class);

        intentAct.putExtra(CmData.CmActData.KEY_SEND_INT, sendInt);
        intentAct.putExtra(CmData.CmActData.KEY_SEND_STRING, sendStr);
        intentAct.putExtra(CmData.IntentValue.KEY_SEND_SERI, createSeriData(sendInt, sendStr));
        intentAct.putExtra(CmData.IntentValue.KEY_SEND_PARC, createParcData(sendInt, sendStr));

        startActivityForResult(intentAct, req);
    }

    private SeriData createSeriData(int num, String str){
        SeriData seriData = new SeriData();
        seriData.setsNum(num);
        if(StringUtil.isEmpty(str)) str = "empty";
        seriData.setsStr(str);
        return seriData;
    }

    private Parcelable createParcData(int num, String str){
        ParcData parcData = new ParcData();
        parcData.setPNum(num);
        if(StringUtil.isEmpty(str)) str = "empty";
        parcData.setPStr(str);
        return parcData;
    }

    // Standard activity result: operation canceled. RESULT_CANCELED    = 0;
    // Standard activity result: operation succeeded. RESULT_OK           = -1;
    // Start of user-defined activity results. RESULT_FIRST_USER   = 1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        res_act1res_tv.setText(String.valueOf(resultCode));
        resreq_act1res_tv.setText(String.valueOf(requestCode));

        if(data != null && data.hasExtra(CmData.CmActData.KEY_SEND_RESINT)){
            int recInt = data.getIntExtra(CmData.CmActData.KEY_SEND_RESINT, -1);
            recint_act1res_tv.setText(String.valueOf(recInt));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_act1req_btn:
                sendStart();
                break;
        }
    }
}

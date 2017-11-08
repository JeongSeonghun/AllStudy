package com.jshstudy.contentsave.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jshstudy.common.util.PatternUtil;
import com.jshstudy.contentsave.R;
import com.jshstudy.contentsave.data.DBData;

public class DBActivity extends Activity implements View.OnClickListener{

    private EditText et_insert_num;
    private EditText et_insert_msg;
    private EditText et_update_idx;
    private EditText et_update_num;
    private EditText et_update_msg;
    private EditText et_delete_idx;

    private DBData dbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        dbData = new DBData(this);

        init();
    }

    public void init(){
        et_insert_num = (EditText)findViewById(R.id.et_insert_num);
        et_insert_msg = (EditText)findViewById(R.id.et_insert_msg);
        et_update_idx = (EditText)findViewById(R.id.et_update_idx);
        et_update_num = (EditText)findViewById(R.id.et_update_num);
        et_update_msg = (EditText)findViewById(R.id.et_update_msg);
        et_delete_idx = (EditText)findViewById(R.id.et_delete_idx);

        findViewById(R.id.btn_db_insert).setOnClickListener(this);
        findViewById(R.id.btn_db_update).setOnClickListener(this);
        findViewById(R.id.btn_db_delete).setOnClickListener(this);
        findViewById(R.id.btn_db_init).setOnClickListener(this);
    }

    private void insert(){
        int num = -1;
        String msg;
        String strNum;

        strNum = et_insert_num.getText().toString();
        if(!strNum.isEmpty()&& PatternUtil.checkNum(strNum)) num = Integer.valueOf(strNum);

        msg = et_insert_msg.getText().toString();

        dbData.insertTb1(num, msg);
    }

    private void update(){
        int idx = -1;
        int num = -1;
        String msg;
        String strNum;
        String strIdx;

        strIdx = et_update_idx.getText().toString();
        if(!strIdx.isEmpty()&& PatternUtil.checkNum(strIdx)) idx = Integer.valueOf(strIdx);

        strNum = et_update_num.getText().toString();
        if(!strNum.isEmpty()&& PatternUtil.checkNum(strNum)) num = Integer.valueOf(strNum);

        msg = et_update_msg.getText().toString();

        dbData.updateTb1(idx, num, msg);
    }

    private void delete(){
        int idx = -1;

        String strIdx;

        strIdx = et_delete_idx.getText().toString();
        if(!strIdx.isEmpty()&& PatternUtil.checkNum(strIdx)) idx = Integer.valueOf(strIdx);

        dbData.deleteTb1(idx);
    }

    private void initTB1(){
        dbData.deleteTb1();
        dbData.alterTb1InitIdx();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_db_insert:
                insert();
                break;
            case R.id.btn_db_update:
                update();
                break;
            case R.id.btn_db_delete:
                delete();
                break;
            case R.id.btn_db_init:
                initTB1();
                break;
        }

        startActivity(new Intent(this, SelectResultActivity.class));
    }
}

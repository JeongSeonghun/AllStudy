package com.jshstudy.contentsave.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.jshstudy.contentsave.R;
import com.jshstudy.contentsave.dao.DBData;
import com.jshstudy.contentsave.dto.TB1Data;

import java.util.ArrayList;

public class SelectResultActivity extends Activity {

    private TextView tv_select_result;

    private DBData dbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_result);

        dbData = new DBData(this);

        init();
    }

    private void init(){
        tv_select_result = (TextView)findViewById(R.id.tv_select_result);

        StringBuilder stringBuilder = new StringBuilder();

        ArrayList<TB1Data> datas = dbData.selectTb1();

        for (TB1Data data : datas) {
            stringBuilder.append(data.toString()).append("\n");
        }

        tv_select_result.setText(stringBuilder.toString());
    }



}

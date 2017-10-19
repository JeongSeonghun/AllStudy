package com.jshstudy.allstudy.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.AllStudyDB;
import com.jshstudy.allstudy.data.SQDBData;

public class DBStudyActivity extends AppCompatActivity implements View.OnClickListener{

    private Context context;

    EditText et_str_sq;
    EditText et_i_sq;
    TextView tv_result_sq;
    AllStudyDB allDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbstudy);

        context = this;

        et_str_sq = (EditText)findViewById(R.id.et_str_sq);
        et_i_sq = (EditText)findViewById(R.id.et_i_sq);
        tv_result_sq = (TextView)findViewById(R.id.tv_result_sq);

        findViewById(R.id.btn_insert_sq).setOnClickListener(this);
        findViewById(R.id.btn_clear_sq).setOnClickListener(this);

        init();
    }

    private void init(){
        allDB = new AllStudyDB(context);

        showResult();
    }

    private void showResult(){
        ArrayList<SQDBData> dataList = allDB.getSQList();

        if(dataList.size()<=0){
            return;
        }

        StringBuilder sb = new StringBuilder("limit 10\n");

        for(SQDBData data : dataList){
            String line = String.format(getString(R.string.sq_result),data.getIndex(), data.getStr(), data.getI());

            sb.append(line + "\n");
        }

        tv_result_sq.setText(sb.toString());
    }

    private void insert(){
        String str;
        int i;

        str = et_str_sq.getText().toString();
        i = Integer.valueOf(et_i_sq.getText().toString());
        allDB.insertSq(str, i);

        showResult();
    }

    private void clear(){
        if(allDB.deleteSq()>=0){
            tv_result_sq.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_insert_sq:
                insert();
                break;
            case R.id.btn_clear_sq:
                clear();
                break;
        }
    }
}

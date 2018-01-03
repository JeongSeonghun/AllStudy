package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.AllStudyDB;
import com.jshstudy.allstudy.data.engdata.EngCheckData;

public class EngCheckAddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText add_eng_sample_et;
    private EditText add_kor_sample_et;
    private EditText add_type_sample_et;
    private Button add_eng_sam_btn;
    private TextView result_add_eng_tv;

    private AllStudyDB allStudyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_check_add);

        initUi();
        initData();

    }

    private void initUi(){
        add_eng_sample_et = (EditText)findViewById(R.id.add_eng_sample_et);
        add_kor_sample_et = (EditText)findViewById(R.id.add_kor_sample_et);
        add_type_sample_et = (EditText)findViewById(R.id.add_type_sample_et);
        add_eng_sam_btn = (Button)findViewById(R.id.add_eng_sam_btn);
        result_add_eng_tv = (TextView)findViewById(R.id.result_add_eng_tv);

        add_eng_sam_btn.setOnClickListener(this);
    }

    private void initData(){
        allStudyDB = new AllStudyDB(this);

        Intent intent = getIntent();
        int idx = intent.getIntExtra("idx", -1);

        if(idx<0) return;

        EngCheckData data = allStudyDB.getEngCheckData(idx);

    }

    private void setData(EngCheckData data){
        String eng = data.getEng();
        String kor = data.getKor();
        String type = data.getType();

        add_eng_sample_et.setText(eng);
        add_kor_sample_et.setText(kor);
        add_type_sample_et.setText(type);
    }

    private void add(){
        String eng, kor, type;

        eng = add_eng_sample_et.getText().toString();
        kor = add_kor_sample_et.getText().toString();
        type = add_type_sample_et.getText().toString();

        allStudyDB.insertEngSample(eng, kor, type);

        result_add_eng_tv.setText("eng : "+eng);
        clear();
    }

    private void clear(){
        add_eng_sample_et.setText("");
        add_kor_sample_et.setText("");
        add_type_sample_et.setText("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_eng_sam_btn:
                add();
                break;
        }
    }
}

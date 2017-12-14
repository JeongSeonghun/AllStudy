package com.jshstudy.allstudy.ui.engstudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.AllStudyDB;
import com.jshstudy.allstudy.data.engdata.EngCheckData;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;

public class EngCheckActivity extends Activity implements View.OnClickListener{

    private TextView eng1_sample_tv;
    private TextView eng2_sample_tv;
    private EditText kor1_sample_et;
    private EditText kor2_sample_et;
    private EditText type1_sample_et;
    private EditText type2_sample_et;
    private Button add_eng_sample_btn;
    private Button check_eng_sample_btn;
    private TextView result_chk1_sample_tv;
    private TextView result_chk2_sample_tv;

    private int total_num = -1;

    AllStudyDB allStudyDB;

    ArrayList<Integer> checkList;

    ArrayList<EngCheckData> engCheckDatas;

    public final int REQUEST_CODE_ADD = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_check);

        initUi();

        initData();
    }

    private void initUi(){
        eng1_sample_tv = (TextView)findViewById(R.id.eng1_sample_tv);
        eng2_sample_tv = (TextView)findViewById(R.id.eng2_sample_tv);
        kor1_sample_et = (EditText)findViewById(R.id.kor1_sample_et);
        kor2_sample_et = (EditText)findViewById(R.id.kor2_sample_et);
        type1_sample_et = (EditText)findViewById(R.id.type1_sample_et);
        type2_sample_et = (EditText)findViewById(R.id.type2_sample_et);
        add_eng_sample_btn = (Button)findViewById(R.id.add_eng_sample_btn);
        check_eng_sample_btn = (Button)findViewById(R.id.check_eng_btn);
        result_chk1_sample_tv = (TextView)findViewById(R.id.result_chk1_sample_tv);
        result_chk2_sample_tv = (TextView)findViewById(R.id.result_chk2_sample_tv);

        add_eng_sample_btn.setOnClickListener(this);
        check_eng_sample_btn.setOnClickListener(this);

    }

    private void initData(){
        allStudyDB = new AllStudyDB(this);
        engCheckDatas = new ArrayList<>();

        total_num = allStudyDB.getEngSampleCnt();

        if(total_num < 2) return;

        checkList = new ArrayList<>();

        int ex1 = -1;
        int ex2 = -1;

        ex1 = getRandomIdx();

        while(ex2 == -1 || ex2 == ex1){
            ex2 = getRandomIdx();
        }

        checkList.add(ex1);
        checkList.add(ex2);

        setEngCheckDatas();
    }

    private void setEngCheckDatas(){
        EngCheckData data1, data2;

        data1 = allStudyDB.getEngCheckData(checkList.get(0));
        data2 = allStudyDB.getEngCheckData(checkList.get(1));

        engCheckDatas.add(data1);
        engCheckDatas.add(data2);

        eng1_sample_tv.setText(data1.getEng());
        eng2_sample_tv.setText(data2.getEng());
    }

    private int getRandomIdx(){
        int rIdx = 0;

        double ran = Math.random();
        int num = (int) Math.floor(Math.log10(total_num)) +1;
        ran *= Math.pow(10, num);
        int num2 = (int) Math.floor(ran);
        rIdx = num2%total_num;

        LogUtil.DLog("random idx = "+rIdx);
        return rIdx+1;
    }

    private void checkEngSample(){

        String str_kor1 = kor1_sample_et.getText().toString();
        String str_kor2 = kor2_sample_et.getText().toString();
        String type1 = type1_sample_et.getText().toString();
        String type2 = type2_sample_et.getText().toString();

        EngCheckData data1 = engCheckDatas.get(0);
        EngCheckData data2 = engCheckDatas.get(1);


        boolean result1, result2;
        result1 = setResult(data1, str_kor1, type1);
        result2 = setResult(data2, str_kor2, type2);

        result_chk1_sample_tv.setText(String.valueOf(result1));
        result_chk2_sample_tv.setText(String.valueOf(result2));
    }

    private void showAnswers(EngCheckData data, String chkData, String chkType){

        kor1_sample_et.setText(addAnswer(chkData, data.getKor()));

        kor2_sample_et.setText(addAnswer(chkType, data.getType()));

    }

    private String addAnswer(String input, String answer){
        return input + "\n -->" + answer;
    }

    private boolean setResult(EngCheckData data, String chkData, String chkType){
        boolean isSuccess = false;

        if(chkData.equals(data.getKor()) && chkType.equals(data.getType())){
            isSuccess = true;
        }else{
            showAnswers(data, chkData, chkType);
        }

        if(isSuccess){
            allStudyDB.updateSuccessFail(isSuccess, data.getSuccess()+1, data.getIdx());
        }else{
            allStudyDB.updateSuccessFail(isSuccess, data.getFail()+1, data.getIdx());
        }

        return isSuccess;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_eng_sample_btn :
                Intent intentAct = new Intent(this, EngCheckAddActivity.class);

                startActivityForResult(intentAct, REQUEST_CODE_ADD);
                break;
            case R.id.check_eng_btn :
                checkEngSample();
                break;
        }
    }
}

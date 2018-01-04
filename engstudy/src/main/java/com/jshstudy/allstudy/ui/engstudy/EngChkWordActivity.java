package com.jshstudy.allstudy.ui.engstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.CommonData;
import com.jshstudy.allstudy.data.EngStudyDB;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngMeanData;
import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class EngChkWordActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv_value_eng_word_chk;
    TextView tv_show_mean_chk_word;
    TextView tv_quest_mean_type_word;
    EditText et_quest_mean_chk_word;
    Button btn_chk_word;
    TextView tv_result_chk_word;
    TextView tv_cnt_try_chk_word;
    TextView tv_result_cnt_chk_word;
    Button btn_next_chk_word;

    private EngStudyDB engStudyDB;

    private EngData engData;

    private int totalNum = -1;
    private int tryCnt = 0;
    private String meanQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_word_chk);

        initUi();
        initData();
    }

    private void initUi(){
        tv_value_eng_word_chk =(TextView)findViewById(R.id.tv_value_eng_word_chk);
        tv_show_mean_chk_word = (TextView)findViewById(R.id.tv_show_mean_chk_word);
        tv_quest_mean_type_word = (TextView)findViewById(R.id.tv_quest_mean_type_word);
        et_quest_mean_chk_word = (EditText)findViewById(R.id.et_quest_mean_chk_word);
        btn_chk_word = (Button)findViewById(R.id.btn_chk_word);
        tv_result_chk_word = (TextView)findViewById(R.id.tv_result_chk_word);
        tv_cnt_try_chk_word = (TextView)findViewById(R.id.tv_cnt_try_chk_word);
        tv_result_cnt_chk_word = (TextView)findViewById(R.id.tv_result_cnt_chk_word);
        btn_next_chk_word = (Button)findViewById(R.id.btn_next_chk_word);

        btn_chk_word.setOnClickListener(this);
        btn_next_chk_word.setOnClickListener(this);
    }

    private void initData(){
        engStudyDB = new EngStudyDB(this);

        setWord();
    }

    private void setWord(){
        totalNum = engStudyDB.selectEngCnt();
        LogUtil.DLog("setWord total : "+ totalNum);

        engData = engStudyDB.selectEng(getRandomIdx(totalNum)+1);

        tv_value_eng_word_chk.setText(engData.getEng());

        tryCnt = 0;
        tv_cnt_try_chk_word.setText(String.format(Locale.KOREA, CommonData.StringFormat.FORMAT_WORD_TRY_CNT, tryCnt));

        tv_result_cnt_chk_word.setText(String.format(Locale.KOREA,
                CommonData.StringFormat.FORMAT_WORD_RESULT_CNT, engData.getSuccess(), engData.getFail()));

        setQuestMean();

    }

    private void setQuestMean(){
        HashMap<String, EngMeanData> meanMap = engData.getMeanMap();

        int posQuestMean = getRandomIdx(meanMap.size());

        Set<String> set = meanMap.keySet();
        ArrayList<String> types = new ArrayList<>(set);
        String typeQuest = types.get(posQuestMean);

        StringBuilder sb = new StringBuilder();

        for(String type : set){
            EngMeanData meanData = meanMap.get(type);
            ArrayList<String> means = meanData.getMeans();
            if(!type.equals(typeQuest)){
                sb.append(type).append(means.toString());
            }else{
                int cnt = getRandomIdx(means.size());

                meanQuest = means.get(cnt);
                means.remove(cnt);

                if(means.size()>0){
                    sb.append(type).append(means.toString());
                }

            }
        }

        LogUtil.DLog("setQuestMean : "+typeQuest+"/"+meanQuest);
        tv_show_mean_chk_word.setText(sb.toString());
        tv_quest_mean_type_word.setText(typeQuest);
    }

    // 0~limit-1
    private int getRandomIdx(int limit){
        int rIdx = 0;

        double ran = Math.random();
        int num = (int) Math.floor(Math.log10(limit)) +1;
        ran *= Math.pow(10, num);
        int num2 = (int) Math.floor(ran);
        rIdx = num2%limit;

        LogUtil.DLog("random idx = "+rIdx);


        return rIdx;
    }

    private void check(){
        LogUtil.DLog("check : "+tryCnt);
        tryCnt++;
        if(tryCnt>3){
            //...
            return;
        }

        String answerMean = et_quest_mean_chk_word.getText().toString();

        tv_cnt_try_chk_word.setText(String.format(Locale.KOREA, CommonData.StringFormat.FORMAT_WORD_TRY_CNT, tryCnt));

        boolean isSuccess = answerMean.equals(meanQuest);

        if(isSuccess){
            engData.updateSuccess(true);
            tv_result_chk_word.setText(R.string.success);
            btn_chk_word.setEnabled(false);
        }else{
            engData.updateSuccess(false);
            tv_result_chk_word.setText(R.string.fail);
        }
        LogUtil.DLog("check result a/q: "+answerMean+"/"+meanQuest+"->"+isSuccess);
        tv_result_cnt_chk_word.setText(String.format(Locale.KOREA,
                CommonData.StringFormat.FORMAT_WORD_RESULT_CNT, engData.getSuccess(), engData.getFail()));

        updateResult(isSuccess);
    }

    private void updateResult(boolean isSuccess){
        engStudyDB.updateSuccess(engData.getIdx(), isSuccess);
    }

    private void next(){
        et_quest_mean_chk_word.setText("");
        btn_chk_word.setEnabled(true);
        setWord();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chk_word:
                check();
                break;
            case R.id.btn_next_chk_word:
                next();
                break;
        }
    }
}

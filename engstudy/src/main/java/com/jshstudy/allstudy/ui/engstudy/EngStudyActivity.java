package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import com.jshstudy.allstudy.R;

import com.jshstudy.allstudy.util.StringUtil;
import com.jshstudy.common.util.LogUtil;

public class EngStudyActivity extends AppCompatActivity implements View.OnClickListener{

    Button check_eng_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_study);

        test();

        initUi();
    }

    private void initUi(){
        check_eng_btn = (Button)findViewById(R.id.check_eng_btn);

        check_eng_btn.setOnClickListener(this);
    }

    public void test(){
        String[] countable = new String[]{
                "many"
                , "a (great, good, large) number of"
                , "several"
                , "a feq"
                , "few"
        };

        String[] unCountable = new String[]{
                "much"
                , "a (great) deal of"
                , "a (large) amount of"
                , "(a) little"
        };

        ArrayList<String> list = new ArrayList<>();

        for(String tempStr : unCountable){
            if(tempStr.contains("(")){
                ArrayList<String> tempList = StringUtil.engDivider(tempStr);

                if(tempList == null || tempList.size()<=0){
                    return;
                }

                list.addAll(tempList);
            }else{

                list.add(tempStr);
            }

        }

        for(String str : list){
            LogUtil.DLog(getClass().getSimpleName(), "list item : "+str);
        }
    }

    private void goToChapter(int num){
        switch (num){
            case 1:
                // QuantityAdjectives
                break;
            case 2:
                // Verb
                break;
            case 3:
                // Tense
                break;
            case 4:
                // Gerund
                break;
            case 5:
                // ToInfinitive
                break;
            case 6:
                // Participle
                break;
            case 7:
                // Conjunction
                break;
            case 8:
                // RelationshipPronounAdverb
                break;
            case 9:
                // noun Conjunction
                break;
            case 10:
                // PrePosition
                break;
            case 11:
                // Inversion
                break;
        }
    }

    private void startEngAct(Class actClass){
        Intent intentAct = new Intent(this, actClass);

        startActivity(intentAct);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.check_eng_btn:
                startEngAct(EngCheckActivity.class);
                break;
        }
    }
}

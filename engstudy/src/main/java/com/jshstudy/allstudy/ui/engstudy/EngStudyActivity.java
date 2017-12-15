package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import com.jshstudy.allstudy.R;

import com.jshstudy.allstudy.data.AllStudyDB;
import com.jshstudy.allstudy.data.engdata.EngWord;
import com.jshstudy.allstudy.study.eng.Gerund;
import com.jshstudy.allstudy.study.eng.QuantityAdjectives;
import com.jshstudy.allstudy.study.eng.Verb;
import com.jshstudy.allstudy.util.StringUtil;
import com.jshstudy.common.util.LogUtil;

public class EngStudyActivity extends AppCompatActivity implements View.OnClickListener{

    private Button check_eng_btn;

    private AllStudyDB allStudyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_study);

        initUi();

        allStudyDB = new AllStudyDB(this);
        saveBaseWord();
    }

    private void initUi(){
        check_eng_btn = (Button)findViewById(R.id.check_eng_btn);

        check_eng_btn.setOnClickListener(this);
    }


    private void saveBaseWord(){
        QuantityAdjectives quantityAdjectives = new QuantityAdjectives();
        insertWord(quantityAdjectives.getQuantityAdjList());

        Verb verb = new Verb();
        insertWord(verb.getVerbList());

        Gerund gerund = new Gerund();
        insertWord(gerund.getGerundList());


    }

    private void insertWord(ArrayList<EngWord> words){
        for(EngWord word : words){
            allStudyDB.insertEngWord(word);
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

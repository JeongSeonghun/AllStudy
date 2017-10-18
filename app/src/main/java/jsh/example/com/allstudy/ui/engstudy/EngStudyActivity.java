package jsh.example.com.allstudy.ui.engstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import jsh.example.com.allstudy.R;
import jsh.example.com.allstudy.util.LogUtil;
import jsh.example.com.allstudy.util.StringUtil;

public class EngStudyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_study);

        test();
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
}

package jsh.example.com.allstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jsh.example.com.allstudy.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

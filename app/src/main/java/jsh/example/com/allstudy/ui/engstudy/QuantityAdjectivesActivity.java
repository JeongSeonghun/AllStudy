package jsh.example.com.allstudy.ui.engstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import jsh.example.com.allstudy.R;
import jsh.example.com.allstudy.data.engdata.EngQuestData;

public class QuantityAdjectivesActivity extends AppCompatActivity {

    public final int TYPE_ADD_ADJ = 0;
    public final int TYPE_CNT_ODD = 1;
    public final int TYPE_NUM_OF = 2;
    public final int TYPE_OTHER = 3;
    public final int TYPE_CNT_NOT = 4;
    public final int TYPE_OF_THE = 5;
    public final int TYPE_PP = 6;
    public final int TYPE_EVERY = 7;


    ArrayList<EngQuestData> questions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity_adjectives);

        setQuestions();
    }

    public void setQuestions(){

        setQuest("The eam leaders said the fact that there were too _ absences during last 9 months.", "many", TYPE_ADD_ADJ);
        // 답 미확실.....
        setQuest("_ pproducts are designed to promote our company's sales figures.", "this", TYPE_CNT_ODD);
        setQuest("A number of peple who attend the seminar _ more than we expect", "are", TYPE_NUM_OF);
        setQuest("I am sorry but you will have to find _ hotel because the  rooms are currently all booked up.", "another", TYPE_OTHER);
        setQuest("_ wquipment will be introduced tomorrow to meet the deadline.", "much", TYPE_CNT_NOT);
        setQuest("Much of the information _ going to be given to the attendees.", "are", TYPE_OF_THE);
        // 답 미확실 ....
        setQuest("You can call us about any questions or you can use _ ways to reach us, such as an email or fax.", "other", TYPE_OTHER);
        setQuest("The document that was _ to the manager was finally accepted after three times of revision.", "sent", TYPE_PP);
        // 답 미확실 .....
        setQuest("_ information will be distributed to the employees.", "all", TYPE_OF_THE);
        setQuest("_ students are going to attend the seminar.", "all", TYPE_OF_THE);
        // 답 미확실
        setQuest("_employees are encouraged to use the stairs during the repair of the elevator.", "all", TYPE_OF_THE);
        setQuest("All assembly line workers are reminded to follow _ safety precautions.", "all", TYPE_OF_THE);
        setQuest("All vehicle manufactured by Seratica Motors is subject to stringent quality control inspection", "Every", TYPE_EVERY);

    }

    public void setQuest(String quest, String answer, int type){
        EngQuestData questData = new EngQuestData(quest, answer, type);
        questions.add(questData);
    }


}

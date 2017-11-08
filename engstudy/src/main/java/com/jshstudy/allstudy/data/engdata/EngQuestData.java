package com.jshstudy.allstudy.data.engdata;

/**
 * Created by shun6 on 2017-09-11.
 */

public class EngQuestData {

    private String quest = "";
    private String answer = "";
    private int type = -1;

    public EngQuestData(String quest, String answer, int type){
        this.quest = quest;
        this.answer = answer;
        this.type = type;
    }

    public String getQuest(){
        return quest;
    }

    public String getAnswer(){
        return answer;
    }

    public int getType(){
        return type;
    }
}

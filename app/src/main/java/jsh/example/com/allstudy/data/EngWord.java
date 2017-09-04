package jsh.example.com.allstudy.data;

import java.util.ArrayList;

import jsh.example.com.allstudy.constant.C;

/**
 * Created by shun6 on 2017-09-05.
 */

public class EngWord {
    private String eng;
    private ArrayList<EngData> korList;

    public EngWord(){
        korList = new ArrayList<>();
    }

    public void setEnglish(String eng){
        this.eng = eng;
    }

    public String getEnglish(){
        return eng;
    }

    public void setKor(EngData engData){
        korList.add(engData);
    }

    public void setKor(String type, String kor){
        EngData engData = new EngData(type, kor);
        setKor(engData);
    }

    public ArrayList<EngData> getKorList(){
        return korList;
    }

    public EngData getKor(String type){
        for(EngData engData : korList){
            if(engData.getType().equals(type)){
                return engData;
            }
        }
        return null;
    }
}

package com.jshstudy.allstudy.data.engdata;

/**
 * Created by shun6 on 2017-09-05.
 */

public class EngData {
    private String type;
    private String kor;
    private String eng;

    public EngData(String type, String kor, String eng){
        this.type = type;
        this.kor = kor;
        this.eng = eng;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setKor(String kor){
        this.kor = kor;
    }

    public String getKor(){
        return kor;
    }

    public void setEng(String eng){
        this.eng = eng;
    }

    public String getEng(){
        return eng;
    }
}

package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

/**
 * Created by shun6 on 2017-12-11.
 */

public class EngCheckData {
    private String type;
    private String kor;
    private String eng;
    private int idx;
    private int success;
    private int fail;

    public EngCheckData(int idx, String kor, String eng, String type, int success, int fail){
        this.idx = idx;
        this.kor = kor;
        this.eng = eng;
        this.type = type;
        this.success = success;
        this.fail = fail;
    }

    public EngCheckData(){

    }

    public void puase(Cursor cursor){
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


    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    @Override
    public String toString() {
        String strData= "data ----\n" +
                "idx : "+ idx + "\n"
                + "eng : "+ eng + "\n"
                + "kor : "+ kor + "\n"
                + "type : "+ type +"\n"
                + "success : "+ success +"\n"
                + "fail : "+ fail;
        //return super.toString();
        return strData;
    }
}

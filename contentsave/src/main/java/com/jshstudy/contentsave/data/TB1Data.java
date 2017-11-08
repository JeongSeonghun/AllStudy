package com.jshstudy.contentsave.data;

/**
 * Created by EMGRAM on 2017-11-07.
 */

public class TB1Data {
    private int idx;
    private int num;
    private String msg;

    public TB1Data(int idx, int num, String msg){
        this.idx = idx;
        this.num = num;
        this.msg = msg;
    }

    public int getIdx(){
        return idx;
    }

    public int getNum(){
        return num;
    }

    public String getMsg(){
        return msg;
    }

    public String toString(){
        return this.getClass().getSimpleName() + "[idx : "+idx+ ", num : "+ num +", msg : "+ msg+"]";
    }
}

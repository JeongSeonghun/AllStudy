package com.jshstudy.communicatestudy.data;

import java.io.Serializable;

/**
 * Created by EMGRAM on 2018-01-02.
 */

public class SeriData implements Serializable{
    /*
    Serializable : marker interface , should not implement method
    serialize use reflection way
     */
    private int sNum = -1;
    private String sStr;

    public void setsNum(int sNum){
        this.sNum = sNum;
    }

    public int getsNum(){
        return sNum;
    }

    public void setsStr(String sStr){
        this.sStr = sStr;
    }

    public String getsStr(){
        return sStr;
    }

    @Override
    public String toString() {
        return "{num : "+sNum+", str : '"+sStr+"'}";
    }
}

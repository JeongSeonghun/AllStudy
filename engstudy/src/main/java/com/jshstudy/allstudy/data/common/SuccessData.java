package com.jshstudy.allstudy.data.common;

/**
 * Created by shun6 on 2018-01-06.
 */

public class SuccessData {
    private int cntSuccess = 0;
    private int cntFail = 0;

    public void setCntSuccess(int cntSuccess){
        this.cntSuccess = cntSuccess;
    }

    public int getCntSuccess(){
        return cntSuccess;
    }

    public void setCntFail(int cntFail){
        this.cntFail = cntFail;
    }

    public int getCntFail(){
        return cntFail;
    }

    public int getTotal(){
        return cntSuccess + cntFail;
    }

    public int getRateSuccess100(){
        if(getTotal()<=0){
            return 0;
        }
        return cntSuccess*100/getTotal();
    }

}

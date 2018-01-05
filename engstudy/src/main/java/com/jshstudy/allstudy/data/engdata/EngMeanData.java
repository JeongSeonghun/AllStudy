package com.jshstudy.allstudy.data.engdata;

import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by shun6 on 2017-12-29.
 */

public class EngMeanData {
    private String type = null;
    private ArrayList<String> means;

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setMean(String mean){
        if(means == null) means = new ArrayList<>();

        means.add(mean);
    }

    public ArrayList<String> getMeans(){
        return means;
    }

    public boolean merge(EngMeanData data){
        LogUtil.dLog(getClass().getSimpleName(), "merge equal: "+(this.equals(data)));
        if(this.equals(data)) return false;

        for(String mean : data.getMeans()){
            LogUtil.dLog(getClass().getSimpleName(), "merge contain: "+mean+"->"+!means.contains(mean));
            if(!means.contains(mean)) means.add(mean);
        }

        return true;
    }
}

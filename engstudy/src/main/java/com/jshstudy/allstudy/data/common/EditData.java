package com.jshstudy.allstudy.data.common;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2018-01-05.
 */

public class EditData {
    private String param;
    private String value;
    private ArrayList<String> values;

    public void setData(String param){
        this.param  = param;
    }

    public void setData(String param, String value){
        this.param = param;
        this.value = value;
    }

    public void setData(String param, ArrayList<String> values){
        setParam(param);
        setValues(values);
    }

    public void setData(EditSubData subData){
        int pos = subData.getPostTitle();
        values = subData.getList();

        setParam(subData.getTitleList().get(pos));
        if(values !=null && values.size()>0){
            setValue(subData.getList().toString());
        }

    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
        if(values != null && values.size()>0) value = values.toString();
    }
}

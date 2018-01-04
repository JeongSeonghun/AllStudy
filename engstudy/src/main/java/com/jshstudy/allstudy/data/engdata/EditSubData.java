package com.jshstudy.allstudy.data.engdata;

import android.os.Parcel;
import android.os.Parcelable;

import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2018-01-04.
 */

public class EditSubData implements Parcelable{

    private String title;
    private ArrayList<String> list;

    public EditSubData(){

    }

    public void setTitle(String title){
        LogUtil.DLog("sub setTitle : "+ title);
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setList(ArrayList<String> list){
        LogUtil.DLog("sub setList : "+ list);
        this.list = list;
    }

    public ArrayList<String> getList(){
        return list;
    }

    protected EditSubData(Parcel in) {
        setTitle(in.readString());
        list = new ArrayList<>();
        in.readStringList(list);

        LogUtil.DLog("parcel list : "+list);
    }

    public static final Creator<EditSubData> CREATOR = new Creator<EditSubData>() {
        @Override
        public EditSubData createFromParcel(Parcel in) {
            return new EditSubData(in);
        }

        @Override
        public EditSubData[] newArray(int size) {
            return new EditSubData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeStringList(list);
    }
}

package com.jshstudy.allstudy.data.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2018-01-04.
 */

public class EditSubData implements Parcelable{

    private ArrayList<String> titleList;
    private ArrayList<String> list;
    private int postTitle;

    public EditSubData(){

    }

    public void setPostTitle(int postTitle){
        this.postTitle = postTitle;
    }

    public int getPostTitle(){
        return postTitle;
    }

    public void setTitleList(ArrayList<String> titleList){
        this.titleList = titleList;
    }

    public ArrayList<String> getTitleList(){
        return titleList;
    }

    public void setList(ArrayList<String> list){
        this.list = list;
    }

    public ArrayList<String> getList(){
        return list;
    }

    protected EditSubData(Parcel in) {
        list = new ArrayList<>();
        in.readStringList(list);
        titleList = new ArrayList<>();
        in.readStringList(titleList);
        setPostTitle(in.readInt());

        LogUtil.dLog("parcel list : "+list);
        LogUtil.dLog("parcel title list : "+titleList);
        LogUtil.dLog("parcel pos title : "+postTitle);
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
        dest.writeStringList(list);
        dest.writeStringList(titleList);
        dest.writeInt(postTitle);

        LogUtil.dLog("parcel dest list : "+list);
        LogUtil.dLog("parcel dest title list : "+titleList);
        LogUtil.dLog("parcel dest pos title : "+postTitle);
    }
}

package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

import com.jshstudy.allstudy.data.EngDataC;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by shun6 on 2017-12-29.
 */

public class EngChapterData {
    private int chapter = -1;
    private ArrayList<String> subList;
    private String desc;

    public void setChapter(int chapter){
        this.chapter = chapter;
    }

    public int getChapter(){
        return chapter;
    }

    public void setSubList(ArrayList<String> subList){
        this.subList = subList;
    }
    public void setSubList(JSONArray jaSubList){
        subList = new ArrayList<>();
        try {
            for(int cnt = 0 ; cnt<jaSubList.length() ; cnt++){
                subList.add(jaSubList.getString(cnt));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void setSubList(String sub){
        subList = new ArrayList<>();
        subList.add(sub);
    }

    public ArrayList<String> getSubList(){
        return subList;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return desc;
    }

    public void parse(Cursor cursor){

        for(String col : cursor.getColumnNames()){
            if(EngDataC.EngDetailChapterDB.COL_CHAPTER.equals(col)){
                setChapter(cursor.getInt(cursor.getColumnIndex(col)));
            }else if(EngDataC.EngDetailChapterDB.COL_SUB.equals(col)){
                setSubList(cursor.getString(cursor.getColumnIndex(col)));
            }else if(EngDataC.EngDetailChapterDB.COL_DESC.equals(col)){
                setDesc(cursor.getString(cursor.getColumnIndex(col)));
            }
        }
    }
}

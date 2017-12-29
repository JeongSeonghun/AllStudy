package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

import com.jshstudy.allstudy.data.EngDataC;

/**
 * Created by shun6 on 2017-12-29.
 */

public class EngChapterData {
    private int chapter = -1;
    private String detail;
    private String text;

    public void setChapter(int chapter){
        this.chapter = chapter;
    }

    public int getChapter(){
        return chapter;
    }

    public void setDetail(String detail){
        this.detail = detail;
    }

    public String getDetail(){
        return detail;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void parse(Cursor cursor){

        for(String col : cursor.getColumnNames()){
            if(EngDataC.EngDetailChapterDB.COL_CHAPTER.equals(col)){
                setChapter(cursor.getInt(cursor.getColumnIndex(col)));
            }else if(EngDataC.EngDetailChapterDB.COL_CHAPTER.equals(col)){
                setDetail(cursor.getString(cursor.getColumnIndex(col)));
            }else if(EngDataC.EngDetailChapterDB.COL_CHAPTER.equals(col)){
                setText(cursor.getString(cursor.getColumnIndex(col)));
            }
        }
    }
}

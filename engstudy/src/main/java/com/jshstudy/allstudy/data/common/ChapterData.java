package com.jshstudy.allstudy.data.common;

/**
 * Created by EMGRAM on 2018-01-08.
 */

public class ChapterData {
    private int chapterNum = -1;
    private String chapterStr = null;
    private boolean check = false;

    public ChapterData(int chapterNum, String chapterStr){
        setChapterNum(chapterNum);
        setChapterStr(chapterStr);
    }

    public void setChapterNum(int chapterNum){
        this.chapterNum = chapterNum;
    }

    public int getChapterNum(){
        return chapterNum;
    }

    public void setChapterStr(String chapterStr){
        this.chapterStr = chapterStr;
    }

    public String getChapterStr(){
        return chapterStr;
    }

    public void setCheck(boolean check){
        this.check = check;
    }

    public boolean isCheck(){
        return check;
    }
}

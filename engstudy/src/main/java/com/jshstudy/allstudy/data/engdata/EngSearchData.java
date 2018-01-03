package com.jshstudy.allstudy.data.engdata;

import com.jshstudy.allstudy.custom.adapter.ChapterAdapter;
import com.jshstudy.common.util.StringUtil;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2018-01-03.
 */

public class EngSearchData {
    private String word;
    private boolean isEng = false;
    private ArrayList<ChapterAdapter.Chapter> chapters = new ArrayList<>();
    public static final int TYPE_CHAPTER_SEARCH_ALL = 0;
    public static final int TYPE_CHAPTER_SEARCH_CONTAIN = 1;
    public static final int TYPE_CHAPTER_SEARCH_NO = 2;
    private int searchChapType = TYPE_CHAPTER_SEARCH_ALL;

    public void setWord(boolean isEng, String word){
        this.isEng = isEng;
        this.word = word;
    }

    public boolean isEng(){
        return isEng;
    }

    public String getWord(){
        return word;
    }

    public void setChapters(ArrayList<ChapterAdapter.Chapter> chapters){
        if(chapters != null){
            this.chapters = chapters;
        }

        searchChapType = chkSearchChapType();
    }

    public ArrayList<ChapterAdapter.Chapter> getChapters(){
        return chapters;
    }

    private int chkSearchChapType(){
        if(chapters == null || chapters.size()<=0){
            return TYPE_CHAPTER_SEARCH_ALL;
        }

        ChapterAdapter.Chapter chapter = chapters.get(0);
        if(chapter.getChapterNum() == -1){
            return TYPE_CHAPTER_SEARCH_ALL;
        }

        if(chapter.getChapterNum() == 0){
            return TYPE_CHAPTER_SEARCH_NO;
        }

        return TYPE_CHAPTER_SEARCH_CONTAIN;
    }

    public int getSearchChapType(){
        return searchChapType;
    }

    public boolean check() {
        return !((chapters.size()<=0 ||searchChapType == TYPE_CHAPTER_SEARCH_ALL) && StringUtil.isEmpty(word));
    }
}

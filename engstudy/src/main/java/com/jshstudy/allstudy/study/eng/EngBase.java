package com.jshstudy.allstudy.study.eng;

import com.jshstudy.allstudy.data.engdata.EngData;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-12-28.
 */

public abstract class EngBase {
    private int chapter = -1;
    private ArrayList<EngData> engList = new ArrayList<>();

    public EngBase(){
        setEngList();
    }

    public EngBase(int chapter){
        this.chapter = chapter;
        setEngList();
    }

    public abstract void setEngList();

    public ArrayList<EngData> getEngList(){
        return engList;
    }

    public void setChapter(int chapter){
        this.chapter = chapter;
    }

    public void addEngWord(String type, String eng, String... means){
        engList.add(createEng(type, eng, means));
    }

    public void addEngPhr(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_PHR, eng, means);
    }

    public void addEngAdj(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_ADJ, eng, means);
    }

    public void addEngVerb(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_V, eng, means);
    }

    public void addEngNoun(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_N, eng, means);
    }

    public void addEngAdv(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_ADV, eng, means);
    }

    public void addEngConj(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_CONJ, eng, means);
    }

    public void addEngProp(String eng, String... means){
        addEngWord(EngCommon.EngType.KEY_PROP, eng, means);
    }

    private EngData createEng(String type, String eng, String... means){
        EngData engData = new EngData();
        engData.setEng(eng);
        engData.setKor(type, means);
        engData.setCh(chapter);
        return engData;
    }
}

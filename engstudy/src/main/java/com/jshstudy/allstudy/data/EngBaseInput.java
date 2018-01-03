package com.jshstudy.allstudy.data;

import android.content.Context;

import com.jshstudy.allstudy.data.EngStudyDB;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.study.eng.EngBase;
import com.jshstudy.allstudy.study.eng.EngCommon;
import com.jshstudy.allstudy.study.eng.Gerund;
import com.jshstudy.allstudy.study.eng.Inversion;
import com.jshstudy.allstudy.study.eng.Participle;
import com.jshstudy.allstudy.study.eng.Preposition;
import com.jshstudy.allstudy.study.eng.QuantityAdjectives;
import com.jshstudy.allstudy.study.eng.RelationshipPronounAdverb;
import com.jshstudy.allstudy.study.eng.Tense;
import com.jshstudy.allstudy.study.eng.ToInfinitive;
import com.jshstudy.allstudy.study.eng.Verb;

/**
 * Created by shun6 on 2017-12-29.
 */

public class EngBaseInput {
    // base save db

    private EngStudyDB engStudyDB = null;

    public void init(Context context){
        engStudyDB = new EngStudyDB(context);
        initWord();
        initChapter();
    }

    private void initWord(){

        int cnt = engStudyDB.selectEngCnt();
        if(cnt>0)return;

        insertBaseEng(new QuantityAdjectives());
        insertBaseEng(new Verb());
        insertBaseEng(new Tense());
//        insertBaseEng(new Gerund());
//        insertBaseEng(new ToInfinitive());
//        insertBaseEng(new Inversion());
//        insertBaseEng(new Participle());
//        insertBaseEng(new Preposition());
//        insertBaseEng(new RelationshipPronounAdverb());
    }

    private void insertBaseEng(EngBase engClass){
        for(EngData data : engClass.getEngList()){
            engStudyDB.insertEng(data);
        }
    }

    private void initChapter(){
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES, "1.1.1", "가산 수량 a");
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES,"1.1.2", "불가산 수량 a");
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES,"1.1.3", "모든 수량 a");
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES,"1.1.4", "all of the");
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES,"1.1.5", "수량 a + 명");
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES,"1.1.6", "other 류");
        setDetailChap(EngCommon.EngChapter.QUANTITY_ADJECTIVES,"1.1.7", "number 류");
        setDetailChap(EngCommon.EngChapter.VERB,"2.1.1", "조동사");
        setDetailChap(EngCommon.EngChapter.VERB,"2.1.2", "be 동사");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.1", "자동사");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.1.3", "자동사 구문");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.1", "타동사 3형식");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.2", "타동사 4형식");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.4", "타동사 5형식");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.5", "타동사 enable 과");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.6", "타동사 사역동사");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.7", "타동사 return 과");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.8","타동사 inform 과");
        setDetailChap(EngCommon.EngChapter.VERB,"2.3.2.9", "말하다 동사");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.1.1", "현재");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.1.2", "현재 진행");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.1.3", "현재 완료");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.2.1", "과거");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.2.2", "과거 진행");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.2.3", "과거 완료");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.3.1", "미래");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.3.2", "미래 진행");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.3.3", "미래 완료");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.4.1", "시간 조건절");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.4.3.1", "요구 주장 제안");
        setDetailChap(EngCommon.EngChapter.TENSE,"3.4.3.2", "필요 의무 형용사");
        setDetailChap(EngCommon.EngChapter.GERUND,"4.2", "동명사를 목적어로");
        setDetailChap(EngCommon.EngChapter.GERUND,"4.6", "to + 명/동명사");
        setDetailChap(EngCommon.EngChapter.TO_INFINITIVE,"5.2", "to부정사를 목적어로");
        setDetailChap(EngCommon.EngChapter.TO_INFINITIVE,"5.5", "to부정사 구문");
        setDetailChap(EngCommon.EngChapter.TO_INFINITIVE,"5.6", "to부정사 + 동명사");
        setDetailChap(EngCommon.EngChapter.PARTICIPLE,"6.4.2", "감정 동사");
        setDetailChap(EngCommon.EngChapter.CONJUNCTION,"7.2.1.1", "등위접속사 순접");
        setDetailChap(EngCommon.EngChapter.CONJUNCTION,"7.2.1.2", "등위접속사 인과");
        setDetailChap(EngCommon.EngChapter.CONJUNCTION,"7.2.1.3", "등위접소사 역접");
        setDetailChap(EngCommon.EngChapter.CONJUNCTION,"7.3.1", "상관접속사");
        setDetailChap(EngCommon.EngChapter.CONJUNCTION,"7.4.1", "부사절 이끄는 접속사");
        setDetailChap(EngCommon.EngChapter.RELATIONSHIP_PRONOUN_ADVERB,"8.3", "관계 대명사");
        setDetailChap(EngCommon.EngChapter.RELATIONSHIP_PRONOUN_ADVERB,"8.6", "that vs what");
        setDetailChap(EngCommon.EngChapter.RELATIONSHIP_PRONOUN_ADVERB,"8.9", "관계 부사");
    }

    private void setDetailChap(int chapter, String detail, String text){

    }
}

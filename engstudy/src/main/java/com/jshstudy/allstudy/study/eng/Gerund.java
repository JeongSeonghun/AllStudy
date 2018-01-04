package com.jshstudy.allstudy.study.eng;

import com.jshstudy.allstudy.data.engdata.EngCommon;

/**
 * Created by shun6 on 2017-09-05.
 */
// 동명사 Ch04
public class Gerund extends EngBase{
    /*
    ~하는 것
    명사자리 주어 목적어 보어 전치사 뒤
    동사 성격 : 타동사면 뒤에 목적어, 앞의 부사 꾸밈
     */

    public Gerund() {
        super(EngCommon.EngChapter.GERUND);
    }

    // 동명사를 목적어로 취하는 동사
    String[] o = new String[]{
            "suggest" , "give up", "mind"
            , "finish", "avoid", "recommend"
            , "enjoy", "consider", "discontinue"
            , "envy", "keep"
            , "deny", "postpone"
    };

    // 명사 vs 동명사
    // 목적어 취할 수 있는 것은 전치사 타동사
    // 동명사 앞 관사x
    // 동명사 주어는 단수

    // 구문 to + 명사/ 동명사
    String[] str = new String[]{
            "look forward to", "be accustomed to", "be used to"
            ,"be committed to", "be dedicated", "devoted to"
            , "react to", "respond to", "reply to"
            , "attribute A to 명사"
            , "be opposed to", "object to"
            , "contribute A to 명사"
            , "contribute to 명사"
            , "be superior to", "be inferior to"
            , "prior to", "previous to"
            , "bo subject to 명사"
            , "bo loyal to", "bo equal to"
    };

    // base

    @Override
    public void setEngList() {
        setVerbToHaveOGerund();

        setToOAndGerund();
    }

    private void setVerbToHaveOGerund(){
        setDetailChap("4.2");

        addEngVerb("suggest", "~을 제안하다");
        addEngVerb("finish", "~을 마치다");
        addEngVerb("enjoy", "~을 즐기다");
        addEngVerb("envy", "~을 부러워하다");
        addEngVerb("deny", "~을 거부하다");
        addEngVerb("give up", "~을 포기하다");
        addEngVerb("avoid", "~을 회피하다");
        addEngVerb("consider", "~을 고려하다");
        addEngVerb("keep", "~을 유지하다");
        addEngVerb("postpone", "~을 연기하다");
        addEngVerb("recommend", "~을 추천하다");
        addEngVerb("mind", "~을 꺼리다");
        addEngVerb("discontinue", "~을 중단하다");

        initDetailChap();
    }

    private void setToOAndGerund(){
        setDetailChap("4.6");

        addEngPhr("look forward to", "~을 기대하다");
        addEngPhr("be accustomed to", "~에 익숙해지다");
        addEngPhr("be used to", "~에 익숙해지다");
        addEngPhr("be committed to", "~을 헌신하다");
        addEngPhr("be dedicated to", "~을 헌신하다");
        addEngPhr("devoted to", "~을 헌신하다");
        addEngPhr("react to ", "~에 반응하다");
        addEngPhr("respond to", "~에 응답하다");
        addEngPhr("reply to", "~에 응답하다");
        addEngPhr("attribute A to B", "A는 B때문이다");
        addEngPhr("be opposed to", "~에 반대하다");
        addEngPhr("object to", "~을 반대하다");
        addEngPhr("contribute A to B", "A를 B에 헌신하다");
        addEngPhr("contribute to A", "A를 헌신하다");
        addEngPhr("be superior to", "상위이다??");
        addEngPhr("be inferior to", "열등하다???");
        addEngPhr("prior to", "~전에");
        addEngPhr("previous to", "~전에");
        addEngPhr("be subject to", new String[]{"~당하기 쉽다", });
        addEngPhr("be loyal to", "~에 복종하다");
        addEngPhr("be equal to", "동등하다");

        initDetailChap();
    }
}

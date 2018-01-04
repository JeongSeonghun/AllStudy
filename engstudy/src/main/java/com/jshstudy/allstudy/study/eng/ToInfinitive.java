package com.jshstudy.allstudy.study.eng;

import com.jshstudy.allstudy.data.engdata.EngCommon;

/**
 * Created by shun6 on 2017-09-05.
 */
// to부정사 Ch05
public class ToInfinitive extends EngBase{

    public ToInfinitive(){
        super(EngCommon.EngChapter.TO_INFINITIVE);
    }

    /*
    동명사 to부정사 ~하는 것
    명사자리 -> 주어, 목적어, 보어 but 전치사 뒤 x
    동사의 성격, 부사자리로도 쓰임
     */

    // 형용사적 용법 -> 명사를 꾸밈
    // 부사적 용법 목적 결과 원인
    // ~하기 위해서

    // to 부정사를 목적어로 취하는 동사
    // want류(hope, expect, wish)
    String[] o = new String[]{
            "want", "hope", "expect", "wish"
            , "plan", "decide", "fail", "ask"
            , "aim", "intend to", "refuse", "agree"
            , "tend to", "try"
    };
    // 가주어 진주어 주어가 너무 길면 동사 안보이니
    // it is possible for me to 동원

    // 가목적어 진목적어 목적어가 너무 길면 목적격 보어 잘안보이니
    // make it possible to 동원

    // 구문
    String[] str = new String[]{
            "be able to", "be unable to", "be willing to", "be unwilling to"
            , "be likely to", "be reluctant to", "be eager to"
            , "be pleased to", "be delighted to ", "be glad to"
            , "be eligible to", "be qualified to", "be ready to"
            , "be intended to", "be scheduled to", "be due to"
            , "be supposed to", "be about to", "be sure to", "be proud to"
    };

    // 부정사와 동명사 둘 다 취하는 동사
    String[] with = new String[]{
            //기억
            "forget", "remember"
            //선호
            , "prefer", "like", "love", "hate"
            //시작 계속 끝
            , "start", "begin", "continue", "stop"
    };


    @Override
    public void setEngList() {
        setVerbOTo();

        setPhrForTo();

        setVerbBothGerundORTo();
    }

    private void setVerbOTo(){
        setDetailChap("5.2");

        addEngVerb("want", "~을 원하다");
        addEngVerb("hope", "~을 원하다");
        addEngVerb("expect", "~을 원하다");
        addEngVerb("wish", "~을 원하다");
        addEngVerb("plan", "~을 계획하다");
        addEngVerb("decide", "~을 결정하다");
        addEngVerb("fail", "~을 실패하다");
        addEngVerb("ask", "~을 부탁하다");
        addEngVerb("aim", "~을 목적으로 하다");
        addEngPhr("intend to", "~을 목적으로 하다");
        addEngVerb("refuse", "~을 거절하다");
        addEngVerb("agree", "~을 동의하다");
        addEngPhr("tend to", "~하는 경향이 있다");
        addEngVerb("try", "~을 시도하다");

        initDetailChap();
    }

    private void setPhrForTo(){

        setDetailChap("5.5");

        addEngPhr("be able to", "~할 수 있다");
        addEngPhr("be unable to", "~할 수 없다");
        addEngPhr("be willing to", "기꺼이 ~하다");
        addEngPhr("be unwilling to", "~하려 하지 않다");
        addEngPhr("be reluctant to", "~하기를 꺼리다");
        addEngPhr("be likely to", "~하는 것 같다");
        addEngPhr("be liable to", "~하는 것 같다");
        addEngPhr("be eager to", "간절히 ~하고 싶어 하다");
        addEngPhr("be pleased to", "~해서 기쁘다");
        addEngPhr("be delighted to", "~해서 기쁘다");
        addEngPhr("be glad to", "~해서 기쁘다");
        addEngPhr("be eligible to", "~할 자격이 되다");
        addEngPhr("be qualified to", "~할 자격이 되다");
        addEngPhr("be entitled to", "~할 자격이 되다");
        addEngPhr("be ready to", "~할 준비가 되다");
        addEngPhr("be intend to", "~할 의도이다");
        addEngPhr("be scheduled to", "~할 계획이다");
        addEngPhr("be due to", "~할 계획이다");
        addEngPhr("be supposed to", "~하기로 되어 있다");
        addEngPhr("be about to", "막 ~하려 하다");
        addEngPhr("be sure to", "확실히 ~하다");
        addEngPhr("be proud to", "~해서 자랑스럽다");

        initDetailChap();
    }

    private void setVerbBothGerundORTo(){
        setDetailChap("5.6");

        addEngVerb("forget", "~을 잊다");
        addEngVerb("remember", "~을 기억하다");
        addEngVerb("prefer", "~을 선호하다");
        addEngVerb("like", "~을 좋아하다");
        addEngVerb("love", "~을 사랑하다");
        addEngVerb("hate", "~을 싫어하다");
        addEngVerb("start", "~을 시작하다");
        addEngVerb("continue", "~을 계속하다");
        addEngVerb("stop", "~을 멈추다");

        initDetailChap();
    }
}

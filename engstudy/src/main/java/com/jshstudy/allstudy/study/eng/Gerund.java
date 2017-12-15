package com.jshstudy.allstudy.study.eng;

import com.jshstudy.allstudy.data.engdata.EngWord;

import java.util.ArrayList;

/**
 * Created by shun6 on 2017-09-05.
 */
// 동명사 Ch04
public class Gerund {
    /*
    ~하는 것
    명사자리 주어 목적어 보어 전치사 뒤
    동사 성격 : 타동사면 뒤에 목적어, 앞의 부사 꾸밈
     */

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
    ArrayList<EngWord> gerundList = new ArrayList<>();

    public ArrayList<EngWord> getGerundList(){

        addGerund("suggest", "~을 제안하다");
        addGerund("finish", "~을 마치다");
        addGerund("enjoy", "~을 즐기다");
        addGerund("envy", "~을 부러워하다");
        addGerund("deny", "~을 거부하다");
        addGerund("give up", "~을 포기하다");
        addGerund("avoid", "~을 회피하다");
        addGerund("consider", "~을 고려하다");
        addGerund("keep", "~을 유지하다");
        addGerund("postpone", "~을 연기하다");
        addGerund("recommend", "~을 추천하다");
        addGerund("mind", "~을 꺼리다");
        addGerund("discontinue", "~을 중단하다");


        addGerund_phr("look forward to", "~을 기대하다");
        addGerund_phr("be accustomed to", "~에 익숙해지다");
        addGerund_phr("be used to", "~에 익숙해지다");
        addGerund_phr("be committed to", "~을 헌신하다");
        addGerund_phr("be dedicated to", "~을 헌신하다");
        addGerund_phr("devoted to", "~을 헌신하다");
        addGerund_phr("react to ", "~에 반응하다");
        addGerund_phr("respond to", "~에 응답하다");
        addGerund_phr("reply to", "~에 응답하다");
        addGerund_phr("attribute A to B", "A는 B때문이다");
        addGerund_phr("be opposed to", "~에 반대하다");
        addGerund_phr("object to", "~을 반대하다");
        addGerund_phr("contribute A to B", "A를 B에 헌신하다");
        addGerund_phr("contribute to A", "A를 헌신하다");
        addGerund_phr("be superior to", "상위이다??");
        addGerund_phr("be inferior to", "열등하다???");
        addGerund_phr("prior to", "~전에");
        addGerund_phr("previous to", "~전에");
        addGerund_phr("be subject to", new String[]{"~당하기 쉽다", });
        addGerund_phr("be loyal to", "~에 복종하다");
        addGerund_phr("be equal to", "동등하다");


        return gerundList;
    }



    private void addGerund(String eng, String... kor){
        gerundList.add(createEngWord(eng, kor));
    }

    public EngWord createEngWord(String eng, String... kor){
        EngWord engWord = new EngWord();
        engWord.setEng(eng);
        engWord.setKor(EngWord.EngType.KEY_VERB, kor);
        engWord.setChapter(EngWord.EngChapter.GERUND);
        return engWord;
    }

    private void addGerund_phr(String eng, String... kor){
        gerundList.add(createEngWord_phr(eng, kor));
    }

    public EngWord createEngWord_phr(String eng, String... kor){
        EngWord engWord = new EngWord();
        engWord.setEng(eng);
        engWord.setKor(EngWord.EngType.KEY_PHR, kor);
        engWord.setChapter(EngWord.EngChapter.GERUND);
        return engWord;
    }
}

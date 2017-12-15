package com.jshstudy.allstudy.study.eng;

import com.jshstudy.allstudy.data.engdata.EngWord;

import java.util.ArrayList;

/**
 * Created by shun6 on 2017-09-05.
 */
// 동사 Ch02
public class Verb {

    // 조동사 auxiliary verb
    String[] aux = new String[]{
            "will"
            , "can"
            , "may"
            , "must"
            , "should"
    };

    // be동사 , be 동사류
    String[] be = new String[]{
            "am"
            ,"are"
            ,"is"
            ,"was"
            ,"were"
            ,"become"
            ,"get"
            ,"seem"
            ,"remain"
            ,"stay"
    };

    // 일반
    // 자동사 intransitive verb
    String[] intransitive = new String[]{
            "rise", "remain", "last", "persist"
            , "exist", "expire", "emerge"
            , "apply", "arrive", "convene", "differ"
            , "progress", "proceed"
            , "take place", "happen", "occur"
            , "disappear", "appear"
            , "communicate", "cooperate", "collaborate"
            , "interact"
            , "reside", "resign", "retire"
    };

    String[] intransitivePre = new String[]{
            "concentrate on", "focus on"
            , "comply with", "adhere to", "observe", "respond to"
            , "react to"
            , "participate in"
            , "deal with"
            , "account for"
            , "reply to"
            , "arrive in", "arrive at"
            , "interfere with"
            , "object to"
    };

    // 목적어를 가지지 않는다.
    // 수동태 불가능
    // 분사형 형용사로 ing만
    // 전치사를 통한 목적어

    // 타동사 transitive verb 주 + 동 + 목(s + v + o)
    String[] transitiveThree = new String[]{
            "suggest", "recommend"
            , "introduce"
            , "make sure", "ensure"
            , "express"
            , "indicate"
            , "note"
            , "insist"
            , "mention"
            , "ask", "require", "request"
            , "show"
            , "say", "explain"
            , "announce"
    };

    // 목적어 (명사 / 대명사 목적격 / that 주어 동사 목적어)

    // 4형식 간접 목적어에게 직접 목적어를
    String[] transitiveFore = new String[]{
            "give", "offer", "send", "grant", "bring", "award"
    };

    // 5형식
    // 5형식, enable과, 사역

    // 5형식 목적어가 목적보어하다고 생각한다.
    String[] transitiveFive = new String[]{
            "make", "find", "consider", "keep", "deem", "hold"
    };

    // enable과 동사 목적어가 to부정사하는 것을 가능하게 하다.
    String[] transitiveEnable = new String[]{
            "enable"
            , "ask", "require", "request"
            , "allow"
            , "expect", "want"
            , "encourage"
            , "recommend"
            , "force"
            , "cause"
            , "persuade"
    };

    // 사역동사 causative verb 목적어가 ~하게끔 하다
    // 목적어가 당하면 pp, 행하면 동원
    String[] transitiveCausative = new String[]{
            "make", "have", "let"
    };

    //return과 동사 RRSSTDFL ~을 목적어에게 보내다
    String[] verbReturn = new String[]{
            "return"
            , "report"
            , "send"
            , "submit"
            , "transfer"
            , "direct"
            , "charge"
            , "forward"
            , "lead"
            , "contribute"
            , "attribute A to B"
    };

    // inform과 동사 목적어에게 ~을 알리다
    String[] verbInform = new String[]{
            "inform"
            , "notify"
            , "remind"
            , "assure"
            , "convince"
            , "advise"
    };

    // 말하다 동사 TSST
    String[] verbTalk = new String[]{
            "talk"
            , "speak"
            , "say"
            , "tell"
    };


    // base
    ArrayList<EngWord> verbList = new ArrayList<>();

    public ArrayList<EngWord> getVerbList(){
        addVerb("rise", "오르다");
        addVerb("remain", "남다");
        addVerb("last", "지속되다");
        addVerb("persist", "지속되다");
        addVerb("exist", "존재하다");
        addVerb("expire", "만료되다");
        addVerb("emerge", "나타나다");
        addVerb("apply", "지원하다");
        addVerb("convene", "모이다");
        addVerb("differ", "다르다");
        addVerb("progress", new String[]{"진보되다", "진전되다"});
        addVerb("proceed", new String[]{"진행되다", "계속되다"});
        addVerb("take place", "열리다");
        addVerb("happen", "발생하다");
        addVerb("occur", "발생하다");
        addVerb("communicate", "의사소통하다");
        addVerb("cooperate", "협력하다");
        addVerb("collaborate", "협력하다");
        addVerb("interact", "상호작용하다");
        addVerb("reside", "살다");
        addVerb("resign", "은퇴하다");
        addVerb("retire", "은퇴하다");

        addVerb("become", "~이 되다");
        addVerb("get", "~하다");
        addVerb("seem", "~하다");
        addVerb("remain", "~하다");
        addVerb("stay", "~하다");

        addVerb_phr("concentrate on", "~에 집중하다");
        addVerb_phr("focus on", "~에 집중하다");
        addVerb_phr("react to", "~에 반응하다");
        addVerb_phr("participate in", "~에 참석하다");
        addVerb("attend", "~에 참석하다");
        addVerb_phr("comply with", "~에 따르다");
        addVerb_phr("adhere to", "~에 따르다");
        addVerb_phr("observe to", "~에 따르다");
        addVerb_phr("respond to","~에 따르다");
        addVerb_phr("account for", "~을 설명하다");
        addVerb_phr("explain", "~을 설명하다");
        addVerb_phr("deal with", "~을 다루다");
        addVerb("handle", "~을 다루다");
        addVerb_phr("reply to", "~에 응답하다");
        addVerb("answer", "~에 응답하다");
        addVerb_phr("arrive in", "~에 도달하다");
        addVerb_phr("arrive at", "~에 도달하다");
        addVerb_phr("interfere with", "~을 방해하다");
        addVerb_phr("object to", "~을 반대하다");

        addVerb("suggest", "~을 제안하다");
        addVerb("recommend", "~을 제안하다");
        addVerb("introduce", new String[]{"~을 소개하다", "~을 도입하다"});
        addVerb("make sure", "~을 확실히하다");
        addVerb("ensure", "~을 확실히하다");
        addVerb("express", "~을 표현하다");
        addVerb("indicate", "~을 나타내다");
        addVerb("note", "~을 알리다");
        addVerb("mention", "~을 알리다");
        addVerb("insist", "~을 주장하다");
        addVerb("ask", "~을 요구하다");
        addVerb("request", "~을 요구하다");
        addVerb("require", "~을 요구하다");
        addVerb("show", "~을 보여주다");
        addVerb("explain", "~을 설명하다");
        addVerb("announce", "~을 발표하다");

        addVerb("give", "~을 주다");
        addVerb("offer", "~을 주다");
        addVerb("send", "~를 주다");
        addVerb("grant", "~을 주다");
        addVerb("bring", "~을 주다");
        addVerb("award", "~을 주다");

        addVerb("make", "~라고 생각하다");
        addVerb("find", "~라고 생각하다");
        addVerb("consider", "~라고 생각하다");
        addVerb("keep", "~라고 생각하다");
        addVerb("deem", "~라고 생각하다");
        addVerb("hold", "~라고 생각하다");

        addVerb("enable", "~을 가능하게하다");
        addVerb("ask", "~을 요청하다");
        addVerb("request", "~을 요청하다");
        addVerb("require", "~을 요청하다");
        addVerb("allow", "~을 허가하다");
        addVerb("expect", "~을 원하다");
        addVerb("want", "~을 원하다");
        addVerb("encourage", "~을 격려하다");
        addVerb("recommend", "~을 제안하다");
        addVerb("force", "~을 강요하다");
        addVerb("cause", "~을 유발하다");
        addVerb("persuade", "~을 설득하다");

        addVerb("make", "~을 시키다");
        addVerb("have", "~을 시키다");
        addVerb("let", "~을 시키다");

        addVerb("return", "~을 돌려주다");
        addVerb("report", "~을 제출하다");
        addVerb("send", "~을 전달하다");
        addVerb("submit", "~을 제출하다");
        addVerb("transfer", "~을 전송하다");
        addVerb("direct", "~을 이끌다");
        addVerb("forward", "~을 전송하다");
        addVerb("lead", "~을 이끌다");
        addVerb("charge", new String[]{"~을 부과하다", "~을 부과하다"});
        addVerb("contribute", "~을 헌신하다");
        addVerb("attribute A to B", "A는 B때문이다");

        addVerb("inform", "~알리다");
        addVerb("notify", "~을 통보하다");
        addVerb("remind", "~을 상기시키다");
        addVerb("assure", "~을 확신하다");
        addVerb("convince", "~을 확신하다");
        addVerb("advise", "~을 충고하다");

        addVerb("talk", "말하다");
        addVerb("speak", "~을 말하다");
        addVerb("say", "~을 말하다");
        addVerb("tell", "~을 말하다");

        return verbList;
    }

    private void addVerb(String eng, String... kor){
        verbList.add(createEngWord(eng, kor));
    }

    private void addVerb_phr(String eng, String... kor){
        verbList.add(createEngWor_phrd(eng, kor));
    }

    public EngWord createEngWord(String eng, String... kor){
        EngWord engWord = new EngWord();
        engWord.setEng(eng);
        engWord.setKor(EngWord.EngType.KEY_VERB, kor);
        engWord.setChapter(EngWord.EngChapter.VERB);
        return engWord;
    }

    public EngWord createEngWor_phrd(String eng, String... kor){
        EngWord engWord = new EngWord();
        engWord.setEng(eng);
        engWord.setKor(EngWord.EngType.KEY_PHR, kor);
        engWord.setChapter(EngWord.EngChapter.VERB);
        return engWord;
    }
}

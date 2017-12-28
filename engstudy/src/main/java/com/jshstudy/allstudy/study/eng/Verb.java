package com.jshstudy.allstudy.study.eng;

/**
 * Created by shun6 on 2017-09-05.
 */
// 동사 Ch02
public class Verb extends EngBase{

    public Verb(){
        super(EngCommon.EngChapter.VERB);
    }

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
    @Override
    public void setEngList() {
        addEngVerb("rise", "오르다");
        addEngVerb("remain", "남다");
        addEngVerb("last", "지속되다");
        addEngVerb("persist", "지속되다");
        addEngVerb("exist", "존재하다");
        addEngVerb("expire", "만료되다");
        addEngVerb("emerge", "나타나다");
        addEngVerb("apply", "지원하다");
        addEngVerb("convene", "모이다");
        addEngVerb("differ", "다르다");
        addEngVerb("progress", new String[]{"진보되다", "진전되다"});
        addEngVerb("proceed", new String[]{"진행되다", "계속되다"});
        addEngVerb("take place", "열리다");
        addEngVerb("happen", "발생하다");
        addEngVerb("occur", "발생하다");
        addEngVerb("communicate", "의사소통하다");
        addEngVerb("cooperate", "협력하다");
        addEngVerb("collaborate", "협력하다");
        addEngVerb("interact", "상호작용하다");
        addEngVerb("reside", "살다");
        addEngVerb("resign", "은퇴하다");
        addEngVerb("retire", "은퇴하다");

        addEngVerb("become", "~이 되다");
        addEngVerb("get", "~하다");
        addEngVerb("seem", "~하다");
        addEngVerb("remain", "~하다");
        addEngVerb("stay", "~하다");

        addEngPhr("concentrate on", "~에 집중하다");
        addEngPhr("focus on", "~에 집중하다");
        addEngPhr("react to", "~에 반응하다");
        addEngPhr("participate in", "~에 참석하다");
        addEngVerb("attend", "~에 참석하다");
        addEngPhr("comply with", "~에 따르다");
        addEngPhr("adhere to", "~에 따르다");
        addEngPhr("observe to", "~에 따르다");
        addEngPhr("respond to","~에 따르다");
        addEngPhr("account for", "~을 설명하다");
        addEngPhr("explain", "~을 설명하다");
        addEngPhr("deal with", "~을 다루다");
        addEngVerb("handle", "~을 다루다");
        addEngPhr("reply to", "~에 응답하다");
        addEngVerb("answer", "~에 응답하다");
        addEngPhr("arrive in", "~에 도달하다");
        addEngPhr("arrive at", "~에 도달하다");
        addEngPhr("interfere with", "~을 방해하다");
        addEngPhr("object to", "~을 반대하다");

        addEngVerb("suggest", "~을 제안하다");
        addEngVerb("recommend", "~을 제안하다");
        addEngVerb("introduce", new String[]{"~을 소개하다", "~을 도입하다"});
        addEngVerb("make sure", "~을 확실히하다");
        addEngVerb("ensure", "~을 확실히하다");
        addEngVerb("express", "~을 표현하다");
        addEngVerb("indicate", "~을 나타내다");
        addEngVerb("note", "~을 알리다");
        addEngVerb("mention", "~을 알리다");
        addEngVerb("insist", "~을 주장하다");
        addEngVerb("ask", "~을 요구하다");
        addEngVerb("request", "~을 요구하다");
        addEngVerb("require", "~을 요구하다");
        addEngVerb("show", "~을 보여주다");
        addEngVerb("explain", "~을 설명하다");
        addEngVerb("announce", "~을 발표하다");

        addEngVerb("give", "~을 주다");
        addEngVerb("offer", "~을 주다");
        addEngVerb("send", "~를 주다");
        addEngVerb("grant", "~을 주다");
        addEngVerb("bring", "~을 주다");
        addEngVerb("award", "~을 주다");

        addEngVerb("make", "~라고 생각하다");
        addEngVerb("find", "~라고 생각하다");
        addEngVerb("consider", "~라고 생각하다");
        addEngVerb("keep", "~라고 생각하다");
        addEngVerb("deem", "~라고 생각하다");
        addEngVerb("hold", "~라고 생각하다");

        addEngVerb("enable", "~을 가능하게하다");
        addEngVerb("ask", "~을 요청하다");
        addEngVerb("request", "~을 요청하다");
        addEngVerb("require", "~을 요청하다");
        addEngVerb("allow", "~을 허가하다");
        addEngVerb("expect", "~을 원하다");
        addEngVerb("want", "~을 원하다");
        addEngVerb("encourage", "~을 격려하다");
        addEngVerb("recommend", "~을 제안하다");
        addEngVerb("force", "~을 강요하다");
        addEngVerb("cause", "~을 유발하다");
        addEngVerb("persuade", "~을 설득하다");

        addEngVerb("make", "~을 시키다");
        addEngVerb("have", "~을 시키다");
        addEngVerb("let", "~을 시키다");

        addEngVerb("return", "~을 돌려주다");
        addEngVerb("report", "~을 제출하다");
        addEngVerb("send", "~을 전달하다");
        addEngVerb("submit", "~을 제출하다");
        addEngVerb("transfer", "~을 전송하다");
        addEngVerb("direct", "~을 이끌다");
        addEngVerb("forward", "~을 전송하다");
        addEngVerb("lead", "~을 이끌다");
        addEngVerb("charge", new String[]{"~을 부과하다", "~을 부과하다"});
        addEngVerb("contribute", "~을 헌신하다");
        addEngVerb("attribute A to B", "A는 B때문이다");

        addEngVerb("inform", "~알리다");
        addEngVerb("notify", "~을 통보하다");
        addEngVerb("remind", "~을 상기시키다");
        addEngVerb("assure", "~을 확신하다");
        addEngVerb("convince", "~을 확신하다");
        addEngVerb("advise", "~을 충고하다");

        addEngVerb("talk", "말하다");
        addEngVerb("speak", "~을 말하다");
        addEngVerb("say", "~을 말하다");
        addEngVerb("tell", "~을 말하다");
    }
}

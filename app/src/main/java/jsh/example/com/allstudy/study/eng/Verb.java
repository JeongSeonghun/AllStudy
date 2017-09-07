package jsh.example.com.allstudy.study.eng;

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

}

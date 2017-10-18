package jsh.example.com.allstudy.study.eng;

/**
 * Created by shun6 on 2017-09-05.
 */
// 시제 Ch03
public class Tense {

    // 현재 시제 : 일반적 사실, 습관, 진리, 일정
    // 주어가 3인칭 단수 현재 일때 동사+s
    // 같이 쓰이는 부사
    String[] currnet = new String[]{
            "everyday"
            , "usually"
            , "always"
            , "every month"
            , "every year"
    };

    // 현재 진행 : be(am, are, is) + ~ing
    // 가까운 미래, 상태
    // 같이 쓰이는 부사
    String[] curIng = new String[]{
            "currently"
            , "now"
            , "presently"
    };

    // 현재 완료 : have/has +p.p, 과거에서 현재까지 영향
    // 계속, 완료, 경험
    // recently 최근에 현제오나료와 과거 시제 같이 쓰임

    // 과거 : 동사 + ed
    // last + 시간, ago, yesterday, in 1990, lately 사용시 과거

    // 과거 진행 : bd(was, were) ~ ing
    // 과거와 동일

    // 과거 완료 had + pp
    // 한 문장 안에 과거 시점과 과거 시점보다 먼저 일어난 일
    // 시간 전후 관계 언급 접속사 사용시 과거완료 과거를 모두 사용 가능
    String[] timeBeforAfter = new String[]{
            "after"
            , "before"
    };

    // 미래 : will + 동원
    // next+ 시간, tomorrow, in the future, soon shortly, in 2030

    // 미래 진행 will be ~ing
    // 미래와 동일 => will be ~ing = will 동원

    // 미래 완료
    // 미래 이전 발생 일이 미래의 어느 시점에 완료될 경우
    // 완료 시점까지의 기간 필요

    // 시점 구분
    public String checkTens(String eng){
        return "";
    }

    // 과거형
    public String getPast(String eng){
        return "";
    }

    // 현재형
    public String getPresent(String eng){
        return "";
    }

    // 과거분사
    public String getPastParticiple(String eng){
        return "";
    }
}

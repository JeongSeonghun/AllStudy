package com.jshstudy.allstudy.study.eng;

import com.jshstudy.allstudy.data.AllStudyDB;
import com.jshstudy.allstudy.data.engdata.EngWord;
import com.jshstudy.allstudy.data.engdata.EngWordList;

import java.util.ArrayList;

/**
 * Created by shun6 on 2017-09-05.
 */
// 수량 형용사 Ch01
public class QuantityAdjectives {
    // the singular 단수
    // the plural 복수

    public static final String ADJECTIVE_TO_UNCOUNTABLE = "ad_to_uncount";
    public static final String ADJECTIVE_TO_COUNTABLE = "ad_to_count";
    public static final String ADJECTIVE_TO_ALLCOUNT = "ad_to_count";
    public static final String ADJECTIVE_TO_UNKNOWN = "ad_to_unknown";

    // 단수명사 취급
    public static final String ADJECTIVE_OFTHE = "ad_of_the";
    public static final String ADJECTIVE_OFTHE_UNCOUNTABLE = "ad_of_un_cnt";
    public static final String ADJECTIVE_OFTHE_PL_UNCOUNTABLE = "ad_of_un_p_cnt";
    public static final String ADJECTIVE_OFTHE_SINGLE_COUNTABLE = "ad_of_s_cnt";
    public static final String ADJECTIVE_OFTHE_PLURAL_COUNTALVE = "ad_of_p_cnt";

    // 복수명사 취급
    public static final String ADJECTIVE_TO_SINGLE_COUNTABLE = "ad_to_single";
    public static final String ADJECTIVE_TO_SINGLE_COUNTABLE_EX = "ad_to_single_ex";

    // 단수 / 복수, 불가산 other
    public static final String ADJECTIVE_TO_PLURAL_UNCOUNTABLE = "ad_to_plural";

    // ~의 숫자(단수)/ 많은(복수)
    public static final String ADJECTIVE_TO_PLURAL_P = "ad_to_plural_p";
    public static final String ADJECTIVE_TO_PLURAL_S = "ad_to_plural_s";

    public String[] countable = new String[]{
            "many"
            , "a (great, good, large)number of"
            , "several"
            , "a feq"
            , "few"
    };

    public String[] unCountable = new String[]{
            "much"
            , "a (great) deal of"
            , "a (large) amount of"
            , "(a) little"
    };

    public String[] allCountable = new String[]{
            "a lot of"
            , "lots of"
            , "plenty of"
            , "no"
            , "all"
            , "some"
            , "most"
    };

    public String[] num = new String[]{
            "one"
            , "two"
            , "three"
            , "fore"
            , "five"
            , "six"
            , "seven"
            , "nine"
            , "ten"
    };

    public String[] ofCountable = new String[]{
            "many"
            ,"a few"
            ,"few"
            ,"both"
            ,"several"
    };

    public String[] ofUncountable = new String[]{
            "much"
            , "a little"
            , "little"
    };
    public String[] ofPlUncountable = new String[]{
            "all"
            , "some"
            , "most"
    };

    public String[] singleCountable = new String[]{
            "each"
            ,"every"
    };

    public String[] singleCountableEx = new String[]{
            "the whole"
    };

    public String[] otherCountable = new String[]{
            "other"
            ,"another"
    };

    public String[] numOfCountable = new String[]{
            "the number of"
            ,"a number of"
    };

    // check countable adjectives
    public String getCountable(String eng){

        if(isCountable(eng)) return ADJECTIVE_TO_COUNTABLE;

        if(isUnCountable(eng)) return ADJECTIVE_TO_UNCOUNTABLE;

        if(isAllCountable(eng)) return ADJECTIVE_TO_ALLCOUNT;

        return ADJECTIVE_TO_UNKNOWN;
    }

    // 단수 취급, ~ 중에서
    public String getOtTheCountable(String eng){
        if(isOfThe(eng)) return ADJECTIVE_OFTHE;

        if(isOfTheUnCnt(eng)) return ADJECTIVE_OFTHE_UNCOUNTABLE;

        if(isOfThePlUnCnt(eng)) return ADJECTIVE_OFTHE_PL_UNCOUNTABLE;

        return ADJECTIVE_TO_UNKNOWN;

    }

    public String getSingeCountable(String eng){
        if(isSingleCountable(eng)) return ADJECTIVE_TO_SINGLE_COUNTABLE;

        if(isSingleCountableEx(eng)) return ADJECTIVE_TO_SINGLE_COUNTABLE_EX;

        return ADJECTIVE_TO_UNKNOWN;
    }

    public String getOtherCountable(String eng){
        if(isOther(eng)){
            if(otherCountable[0].equals(eng)){
                return ADJECTIVE_TO_SINGLE_COUNTABLE;
            }else{
                return ADJECTIVE_TO_PLURAL_UNCOUNTABLE;
            }
        }

        return ADJECTIVE_TO_UNKNOWN;
    }

    public String getNumOfCountable(String eng){
        if(isNumOf(eng)){
            if(numOfCountable[0].equals(eng)){
                return ADJECTIVE_TO_PLURAL_S;
            }else{
                return ADJECTIVE_TO_PLURAL_P;
            }
        }

        return ADJECTIVE_TO_UNKNOWN;
    }

    // adjective only take countable noun
    public boolean isCountable(String eng) {
        return !(eng == null || eng.isEmpty()) && compare(eng, countable);
    }

    // adjective only take unCountable noun
    public boolean isUnCountable(String eng) {
        return !(eng == null || eng.isEmpty()) && compare(eng, unCountable);
    }

    // adjective take both all
    public boolean isAllCountable(String eng) {
        return !(eng == null || eng.isEmpty()) && compare(eng, allCountable);
    }


    public boolean isOfThe(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, ofCountable) || compare(eng, num);
    }
    public boolean isOfTheUnCnt(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, ofUncountable);
    }
    public boolean isOfThePlUnCnt(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, ofPlUncountable);
    }


    public boolean isSingleCountable(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, singleCountable);
    }

    public boolean isSingleCountableEx(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, singleCountableEx);
    }


    public boolean isOther(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, otherCountable);
    }


    public boolean isNumOf(String eng){
        return !(eng == null || eng.isEmpty()) && compare(eng, numOfCountable);
    }

    // compare method of QuantityAdjectives class
    public boolean compare(String str, String[] strArr){

        for(String compareStr : strArr){
            if(str.equals(compareStr)){
                return true;
            }
        }
        return false;
    }

    // incompletion
    private String[] containParenthesis(String str){
        if(str.contains("(")){
            int front = str.indexOf("(");
            int back = str.indexOf(")");

            String temp = str.substring(front, back);
            String[] temps = temp.split(",");

            return temps;
        }
        return null;
    }


    public void saveWord(){;
        getQuantityAdjList();
        for(EngWord word : quantityAdjList){

        }
    }
    ArrayList<EngWord> quantityAdjList = new ArrayList<>();

    // base
    public ArrayList<EngWord> getQuantityAdjList(){
        addQuantityAdj("many", "많은");
        addQuantityAdj("a number of", "많은");
        addQuantityAdj("a good number of", "많은");
        addQuantityAdj("a great number of", "많은");
        addQuantityAdj("a large number of", "많은");
        addQuantityAdj("several", "몇몇의");
        addQuantityAdj("a few", "적은");
        addQuantityAdj("few", "거의 없는");

        addQuantityAdj("much", "많은");
        addQuantityAdj("a deal of", "많은");
        addQuantityAdj("a great deal of", "많은");
        addQuantityAdj("a amount of", "많은");
        addQuantityAdj("a large amount of", "많은");
        addQuantityAdj("a little", "적은");
        addQuantityAdj("little", "거의 없는");

        addQuantityAdj("a lot of", "많은");
        addQuantityAdj("lots of", "많은");
        addQuantityAdj("no", "없는");
        addQuantityAdj("all", "모든");
        addQuantityAdj("some", "몇몇");
        addQuantityAdj("most", "대부분");

        addQuantityAdj("one of the", "~중에 하나");
        addQuantityAdj("all of the", "~중에 모두");
        addQuantityAdj("some of the", "~중에 몇몇");
        addQuantityAdj("most of the", "~중에 대부분");
        addQuantityAdj("many of the", "~중에 많은 것");
        addQuantityAdj("a few of the", "~중에 적은 것");
        addQuantityAdj("few of the", "~중에 거의 없는");
        addQuantityAdj("several of the", "~중에 몇몇");
        addQuantityAdj("both of the", "~중에 둘다");
        addQuantityAdj("a little of the", "~중에 적은 것");
        addQuantityAdj("little of the", "~중에 거의 없는");

        addQuantityAdj("every", "모든");
        addQuantityAdj("each", "각각");
        addQuantityAdj("the whole", "모든");

        addQuantityAdj("another", "다른");
        addQuantityAdj("other", "다른");

        addQuantityAdj("the number of", "~의 숫자");

        return quantityAdjList;

    }

    private void addQuantityAdj(String eng, String... kors){

    }

    private void addQuantityAdj(String eng, String kor){
        quantityAdjList.add(createEngWord(eng, kor));
    }

    public EngWord createEngWord(String eng, String kor){
        EngWord engWord = new EngWord();
        engWord.setEng(eng);
        engWord.setKor(EngWord.EngType.KEY_ADJ, kor);
        engWord.setChapter(EngWord.EngChapter.QUANTITY_ADJECTIVES);
        return engWord;
    }



}

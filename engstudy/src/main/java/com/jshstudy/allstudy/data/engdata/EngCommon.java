package com.jshstudy.allstudy.data.engdata;

/**
 * Created by shun6 on 2017-10-03.
 */

public class EngCommon {

    public class EngType{
        public static final String KEY_ADJ = "adjective";
        public static final String KEY_V = "verb";
        public static final String KEY_N = "noun";
        public static final String KEY_ADV = "adverb";
        public static final String KEY_CONJ = "conjunction";
        public static final String KEY_PROP = "proposition";
        public static final String KEY_PHR = "phrase";

        public static final int a = 101; // = adj
        public static final int v = 102;
        public static final int n = 103;
        public static final int adv = 104; // 부사
        public static final int conjunction = 105; // 접속사
        public static final int propostion = 106; // 전치사
        public static final int phr = 120; // 관용구, 구

    }

    public class EngPosType{
        public static final int s = 201;
        public static final int v = 202;
        public static final int s_c = 203;
        public static final int o = 204;
        public static final int d_o = 205;
        public static final int i_o = 206;
        public static final int o_c = 207;
    }

    public class EngTense{
        public static final int current = 301;
        public static final int current_ing = 302;
        public static final int current_have = 303;
        public static final int ed = 304;
        public static final int ed_ing = 305;
        public static final int ed_have = 306;
        public static final int will = 307;
        public static final int will_ing = 308;
        public static final int will_have = 309;
    }

    public class EngChapter{
        public static final int QUANTITY_ADJECTIVES = 1;
        public static final int VERB = 2;
        public static final int TENSE = 3;
        public static final int GERUND = 4;
        public static final int TO_INFINITIVE = 5;
        public static final int PARTICIPLE = 6;
        public static final int CONJUNCTION = 7;
        public static final int RELATIONSHIP_PRONOUN_ADVERB = 8;
        public static final int O_CONJUNCTION = 9;
        public static final int PREPOSITION = 10;
        public static final int INVERSION = 11;
    }
}

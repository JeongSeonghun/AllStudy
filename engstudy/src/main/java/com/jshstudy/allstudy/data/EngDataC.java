package com.jshstudy.allstudy.data;

/**
 * Created by shun6 on 2017-12-10.
 */

public class EngDataC {
    public class EngType{
        public static final String TYPE_CH1_QUA_ADJ = "QuantityAdjectives";


    }
    // CREATE TABLE IF NOT EXISTS eng_sample (idx integer primary key autoincrement, eng text, kor text, type text, success INTEGER, fail INTEGER)
    public class EngSample{
        public static final String KEY_IDX = "idx";
        public static final String KEY_ENG = "eng";
        public static final String KEY_KOR = "kor";
        public static final String KEY_TYPE = "type";
        public static final String KEY_SUCCESS = "success";
        public static final String KEY_FAIL = "fail";
    }

    public class WordDB{
        public static final String TABLE_NAME = "eng_word";

        public static final String KEY_IDX = "idx";
        public static final String KEY_ENG = "eng";
        public static final String KEY_KOR = "kor";
        public static final String KEY_CHAPTER = "chap";

        public static final String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
                +" ("+KEY_IDX+" integer primary key autoincrement, "+KEY_ENG+" text, "+KEY_KOR +" text, "+KEY_CHAPTER+" text)";
        public static final String QUERY_CNT = "SELECT "+KEY_IDX+" FROM "+TABLE_NAME;
        public static final String QUERY_INSERT = "INSERT INTO "+TABLE_NAME+" ("+KEY_ENG+","+KEY_KOR+","+KEY_CHAPTER+") "
                +"VALUES(%S, %S, %S)";

    }
}

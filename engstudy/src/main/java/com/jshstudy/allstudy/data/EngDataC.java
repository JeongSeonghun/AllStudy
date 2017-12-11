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
}

package com.jshstudy.allstudy.data.common;

/**
 * Created by shun6 on 2018-01-03.
 */

public class CommonData {
    public static final int REQ_CODE_EDIT = 100;
    public static final int REQ_CODE_ADD = 101;

    public static final int REQ_CODE_EDIT_MEAN = 201;
    public static final int REQ_CODE_EDIT_CHAP = 202;
    public static final int REQ_CODE_ADD_MEAN = 203;
    public static final int REQ_CODE_ADD_CHAP = 204;

    public static class IntentData{
        public static final String KEY_MOD = "mode";
        public static final String KEY_IDX = "idx";
        public static final String KEY_SUB_DATA = "data.sub";

        public static final int VALUE_MOD_EDIT = 100;
        public static final int VALUE_MOD_ADD = 101;
    }

    public static class Format{
        public static final String FORMAT_SIMPLE_CHAPTER = "ch%1$d";
        public static final String FORMAT_WORD_TRY_CNT = "%1$d / 3";
        public static final String FORMAT_WORD_RESULT_CNT = "Success %1$d / Fail %2$d";
        public static final String FORMAT_TEMP_VALUE = " : %1$s";
        public static final String FORMAT_WORD_SUCCESS_RATE_TOTAL = "%1$d %% (total try : %2$d)";
    }

    public static class dialog{
        public static final int TYPE_SINGEL = 0;
        public static final int TYPE_MULTY = 1;
    }

    public enum Chapter{
        chapterNo(-1, "no"),
        chapterAll(0, "all");

        private int code;
        private String simple;
        Chapter(int code, String simple){
            this.code = code;
            this.simple = simple;
        }

        public int code(){
            return code;
        }

        public String simple(){
            return simple;
        }
    }
}

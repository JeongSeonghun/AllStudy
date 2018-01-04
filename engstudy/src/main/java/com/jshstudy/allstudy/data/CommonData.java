package com.jshstudy.allstudy.data;

/**
 * Created by shun6 on 2018-01-03.
 */

public class CommonData {
    public static final int REQ_CODE_EDIT = 100;
    public static final int REQ_CODE_ADD = 101;

    public static class IntentData{
        public static final String KEY_MOD = "mode";
        public static final String KEY_IDX = "idx";
        public static final String KEY_SUB_DATA = "data.sub";

        public static final int VALUE_MOD_EDIT = 100;
        public static final int VALUE_MOD_ADD = 101;
    }

    public static class StringFormat{
        public static final String FORMAT_SIMPLE_CHAPTER = "ch%1$d";
        public static final String FORMAT_WORD_TRY_CNT = "%1$d / 3";
        public static final String FORMAT_WORD_RESULT_CNT = "Success %1$d / Fail %2$d";
    }
}

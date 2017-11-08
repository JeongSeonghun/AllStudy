package com.jshstudy.contentsave.db;

import android.provider.BaseColumns;

/**
 * Created by EMGRAM on 2017-11-07.
 */

public final class SaveContracts {

    public static final String DB = "ContentSaveDB";

    private SaveContracts(){}

    public static class DBtb1Entry implements BaseColumns{
        public static final String TB_NAME = "tb1";

        public static final String COL_IDX = "idx";
        public static final String COL_NUM = "num";
        public static final String COL_MESSAGE = "msg";

    }
}

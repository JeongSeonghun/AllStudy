package com.jshstudy.allstudy.data;

import com.jshstudy.common.data.ComDB;

import java.util.Locale;

/**
 * Created by shun6 on 2017-12-10.
 */

public class EngDataC {
    public class EngType{

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
        public static final String KEY_SUCCESS = "success";
        public static final String KEY_FAIL = "fail";

        public static final String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
                +" ("+KEY_IDX+" integer primary key autoincrement, "+KEY_ENG+" text, "+KEY_KOR +" text, "+KEY_CHAPTER+" text" +
                KEY_SUCCESS+" text, "+KEY_FAIL+" text)";
        public static final String QUERY_CNT = "SELECT "+KEY_IDX+" FROM "+TABLE_NAME;
        public static final String QUERY_INSERT = "INSERT INTO "+TABLE_NAME+" ("+KEY_ENG+","+KEY_KOR+","+KEY_CHAPTER+") "
                +"VALUES(%S, %S, %S,0,0)";

    }

    // final eng data
    // idx(int), eng(text), kor(text), chXX(boolean), success(int), fail(int)
    // kor {type1 : [kor1, kor2], type2...}
    public static final class EngDB{
        public static final int TOTAL_CH = 12;

        public static final int VERSION_DB = 1;
        public static final String NAME_DB = "";
        public static final String NAME_TABLE = "";

        public static final String COL_ENG = "eng";
        public static final String COL_KOR = "kor";

        public static final String COL_CH = "ch%1$d";
        public static final String COL_NCH = "ch_n";

        public static final String COL_SUCCESS = "success";
        public static final String COL_FAIL = "fail";

        public static final String QUERY_CREATE_TABLE = String.format(ComDB.QUERY_CREATE_TABLE_N, NAME_TABLE,
                ComDB.BASE_COL_IDX+", "
                        +COL_ENG+" "+ComDB.TYPE_TEXT + ", "+COL_KOR+" "+ ComDB.TYPE_TEXT+", "
                        +COL_SUCCESS+" "+ComDB.TYPE_INT+" DEFAULT 0, "+COL_FAIL+" "+ComDB.TYPE_INT + " DEFAULT 0, "
                        +COL_NCH+ " "+ComDB.TYPE_BOOLEAN+" DEFAULT FALSE, "
                        +getColCh());

        private static String getColCh(){
            StringBuilder sb = new StringBuilder();

            for(int idx=0; idx < TOTAL_CH ; idx++){
                sb.append(String.format(Locale.KOREA, COL_CH, (idx + 1))).append(" ")
                        .append(ComDB.TYPE_INT).append(" DEFAULT -1");

                if(idx<TOTAL_CH-1){
                    sb.append(", ");
                }
            }

            return sb.toString();
        }

        public static final String QUERY_SELECT_ENG = "Select * from "+ NAME_TABLE +
                " where "+ComDB.COL_IDX + " = " + "%1$d";

        public static final String QUERY_SELECT_ENG_CHECK = "SELECT "+ComDB.COL_IDX+" from "+ NAME_TABLE+
                " where "+COL_ENG +" = '%1$s'";

        public static final String QUERY_SELECT_ENG_LIMIT = String.format(ComDB.QUERY_SELECT, NAME_TABLE)+
                " LIMIT %1$d";

        public static final String QUERY_SELECT_ENG_OFFSET = String.format(ComDB.QUERY_SELECT, NAME_TABLE)+
                " LIMIT %1$d OFFSET %2$d";

        public static final String QUERY_SELECT_ENG_OFFSET_WHERE = "";

        public static final String QUERY_INSERT_ENG = String.format(ComDB.QUERY_INSERT, NAME_TABLE,
                COL_ENG+ " , "+ COL_KOR+", %1$s, "+COL_NCH, "%2$s, %3$s, %4$s, true");

        public static final String QUERY_INSERT_ENG_NO_Ch = String.format(ComDB.QUERY_INSERT, NAME_TABLE,
                COL_ENG+ " , "+ COL_KOR, "%1$s, %2$s");


        public static final String QUERY_UPDATE_ENG = String.format(ComDB.QUERY_UPDATE, NAME_TABLE,
                COL_KOR+"=%1$s", ComDB.COL_IDX + "= $2$d");

        public static final String QUERY_UPDATE_SUCCESS = String.format(ComDB.QUERY_UPDATE, NAME_TABLE,
                "%1$s = %2$d", ComDB.COL_IDX +" = %3$d");
    }


}

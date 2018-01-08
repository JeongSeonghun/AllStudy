package com.jshstudy.allstudy.data.engdata;

import com.jshstudy.common.data.ComDB;

import java.util.Locale;

/**
 * Created by shun6 on 2017-12-10.
 */

public class EngDBDataC {

    // final eng data
    // idx(int), eng(text), kor(text), chXX(boolean), success(int), fail(int)
    // kor {type1 : [kor1, kor2], type2...}

    public static final String NAME_DB_DEFAULT_ENG = "eng_study";
    public static final int VERSION_DB_DEFAULT_ENG = 1;

    public static final class EngDB{
        public static final int TOTAL_CH = 12;
        public static final int LIMIT_SEARCH = 10;

        public static final String NAME_TABLE = "eng_words";

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
                        +COL_NCH+ " "+ComDB.TYPE_INT+" DEFAULT 0, "
                        +getColCh());

        private static String getColCh(){
            StringBuilder sb = new StringBuilder();

            for(int idx=0; idx < TOTAL_CH ; idx++){
                sb.append(String.format(Locale.KOREA, COL_CH, (idx + 1))).append(" ");

                sb.append(ComDB.TYPE_TEXT);

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

        public static final String QUERY_SELECT_ENG_OFFSET = String.format(ComDB.QUERY_SELECT, NAME_TABLE)+
                " LIMIT %1$d OFFSET %2$d";

        public static final String QUERY_SELECT_ENG_OFFSET_WHERE = String.format(ComDB.QUERY_SELECT, NAME_TABLE)+
                " %1$s LIMIT %2$d OFFSET %3$d";

        public static final String QUERY_SELECT_ENG_CNT = String.format(ComDB.QUERY_SELECT_CNT_IDX, NAME_TABLE);

        public static final String QUERY_SELECT_ENG_SUCCESS_SUM = "SELECT SUM("+COL_SUCCESS+"), SUM("+COL_FAIL+") "+
                "FROM "+NAME_TABLE;

        public static final String QUERY_SELECT_ENG_LAST_IDX = "SELECT "+ComDB.COL_IDX+" from "+NAME_TABLE+
                " ORDER BY "+ComDB.COL_IDX+" "+ComDB.ORDER_DESC+" LIMIT 1";

        public static final String QUERY_INSERT_ENG = String.format(ComDB.QUERY_INSERT, NAME_TABLE,
                COL_ENG+ " , "+ COL_KOR+", %1$s, "+COL_NCH, "'%2$s', '%3$s', '%4$s', 0");// col_chapList, eng, kor, val_chapLIst

        public static final String QUERY_INSERT_ENG_NO_Ch = String.format(ComDB.QUERY_INSERT, NAME_TABLE,
                COL_ENG+ " , "+ COL_KOR+", "+COL_NCH, "'%1$s', '%2$s', 1");

        public static final String QUERY_UPDATE_ENG = String.format(ComDB.QUERY_UPDATE_WHERE, NAME_TABLE,
                COL_KOR+"='%1$s'", ComDB.COL_IDX + "= %2$d");

        public static final String QUERY_UPDATE_ENG_ALL = String.format(ComDB.QUERY_UPDATE_WHERE, NAME_TABLE,
                COL_ENG+"='%1$s', "+COL_KOR+"='%2$s'"+", %3$s, "+COL_NCH+"=%4$d", ComDB.COL_IDX + "= %5$d");

        public static final String QUERY_UPDATE_SUCCESS = String.format(ComDB.QUERY_UPDATE_WHERE, NAME_TABLE,
                "%1$s = %2$d", ComDB.COL_IDX +" = %3$d");

        public static final String QUERY_UPDATE_SUCCESS_INIT = String.format(ComDB.QUERY_UPDATE, NAME_TABLE,
                COL_SUCCESS+"=0,"+COL_FAIL+"=0");

        public static final String QUERY_UPDATE_IDX_INIT = String.format(Locale.KOREA, ComDB.QURERY_INIT_IDX,
                0, NAME_TABLE);

        public static final String QUERY_DELETE_WORD = String.format(Locale.KOREA, ComDB.QUERY_DELETE_WHERE,
                NAME_TABLE, ComDB.COL_IDX+"=%1$s");

        public static final String QUERY_DELETE_WORD_ALL = String.format(Locale.KOREA, ComDB.QUERY_DELETE,
                NAME_TABLE);
    }

    public static final class EngDetailChapterDB{
        public static final String NAME_TABLE = "eng_chapter";

        public static final String COL_CHAPTER = "chapter";
        public static final String COL_SUB = "sub";
        public static final String COL_DESC = "desc";

        public static final String QUERY_CREATE_TABLE = String.format(ComDB.QUERY_CREATE_TABLE_N, NAME_TABLE,
                ComDB.BASE_COL_IDX + ", "
                +COL_CHAPTER + " "+ ComDB.TYPE_INT + ", "
                +COL_SUB + " "+ ComDB.TYPE_TEXT + ", "
                +COL_DESC + " "+ ComDB.TYPE_TEXT);

        public static final String QUERY_INSERT = String.format(ComDB.QUERY_INSERT, NAME_TABLE,
                COL_CHAPTER+", "+COL_SUB+", "+COL_DESC, "%1$d, '%2$s', '%3$s'");

        public static final String QUERY_SELECT = String.format(ComDB.QUERY_SELECT, NAME_TABLE);
        public static final String QUERY_SELECT_CNT = String.format(ComDB.QUERY_SELECT_CNT_IDX, NAME_TABLE);
        public static final String QUERY_SELECT_CHAP_LIST = String.format(ComDB.QUERY_SELECT, NAME_TABLE)+" WHERE "+COL_CHAPTER+"=%1$d";
        public static final String QUERY_SELECT_CHAP = String.format(ComDB.QUERY_SELECT, NAME_TABLE)+" WHERE "+COL_SUB+"=%1$s";
        public static final String QUERY_SELECT_TEXT = "SELECT "+COL_DESC+" FROM "+NAME_TABLE+" WHERE "+COL_SUB + "=%1$s";
    }

}

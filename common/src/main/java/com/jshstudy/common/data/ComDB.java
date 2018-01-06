package com.jshstudy.common.data;

/**
 * Created by EMGRAM on 2017-12-26.
 */

public class ComDB {

    /* SQLite value type
    NULL 아무것도 없습니다. 빈 값입니다.
    INTEGER 정수형 데이터로 1, 2, 3, 0 등이 해당 됩니다.
    REAL 8바이트의 저장 가능한 부동 소수점 데이터로, 3.14 같이 소수점 붙은 숫자가 해당됩니다.
    TEXT 문자열로 "hello", "w" 등이 해당되며 기본으로 UTF-8 인코딩을 사용합니다.
    BLOB 이진 데이터 그대로 저장합니다. 예를 들면 zip이나 exe같은 바이너리 화일의 데이터를 그대로 저장합니다.

    boolean -> integer 0 or 1
    모든 data null 가능 (java int = null 불가능과 차이 있음)
     */

    public static final String COL_IDX = "idx";

    public static final String BASE_COL_IDX = COL_IDX+ " integer primary key autoincrement";

    public static final String TYPE_INT = "INTEGER";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_REAL = "REAL";
    public static final String TYPE_BLOB = "BLOB";

    public static final String QUERY_CREATE_TABLE_N = "CREATE TABLE IF NOT EXISTS %1$s (%2$s)";

    public static final String QURERY_INIT_IDX = "UPDATE SQLITE_SEQUENCE SET seq = %1$d WHERE name = '%2$s'";

    public static final String QUERY_SELECT = "SELECT * FROM %1$s";

    public static final String QUERY_SELECT_CNT_IDX = "SELECT "+COL_IDX+" FROM %1$s";

    public static final String QUERY_INSERT = "INSERT INTO %1$s (%2$s) VALUES(%3$s)";

    public static final String QUERY_UPDATE = "UPDATE %1$s SET %2$s";

    public static final String QUERY_UPDATE_WHERE = "UPDATE %1$s SET %2$s WHERE %3$s";

    public static final String FORMAT_FUNC_FORMAT_SUM = "SUM(%1$s)";
    public static final String FORMAT_FUNC_FORMAT_AVG = "AVG(%1$s)";
    public static final String FORMAT_FUNC_FORMAT_COUNT = "COUNT(%1$s)";
    public static final String FORMAT_FUNC_FORMAT_MAX = "MAX(%1$s)";
    public static final String FORMAT_FUNC_FORMAT_MIN = "MIN(%1$s)";


}

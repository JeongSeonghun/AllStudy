package com.jshstudy.common.data;

/**
 * Created by EMGRAM on 2017-12-26.
 */

public class ComDB {
    public static final String QUERY_CREATE_TABLE_N = "CREATE TABLE IF NOT EXISTS %1$s (%2$s)";

    public static final String QURERY_INIT_IDX = "UPDATE SQLITE_SEQUENCE SET seq = %1$d WHERE name = '%2$s'";

    public static final String QUERY_SELECT = "SELECT * FROM %1$s";

    public static final String QUERY_INSERT = "INSERT INTO %1$s (%2$s) VALUES(%3$s)";

    public static final String QUERY_UPDATE = "UPDATE %1$s SET %2$s WHERE %3$s";

    public static final String COL_IDX = "idx ";

    public static final String BASE_COL_IDX = COL_IDX+ "integer primary key autoincrement";

    public static final String TYPE_INT = "INTEGER";
    public static final String TYPE_TEXT = "TEXT";
    public static final String TYPE_BOOLEAN = "BOOLEAN";
}

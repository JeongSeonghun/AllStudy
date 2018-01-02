package com.jshstudy.contentsave.dao.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jshstudy.contentsave.vo.SaveContracts;

/**
 * Created by EMGRAM on 2017-11-07.
 */

public class DBStudyDBHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = SaveContracts.DB;
    private static final int DB_VERSION = 1;

//    private Context ctx;
//     CursorFactory, DataBaseErrorHandler
    public DBStudyDBHelper(Context ctx){
//        super(ctx, ctx.getString(R.string.db_name), null, ctx.getResources().getInteger(R.integer.db_version));
//        this.ctx = ctx;
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    public DBStudyDBHelper(Context ctx, DatabaseErrorHandler errorHandler){
//        super(ctx, ctx.getString(R.string.db_name), null, ctx.getResources().getInteger(R.integer.db_version), errorHandler);
        super(ctx, DB_NAME, null, DB_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String sql = ctx.getString(R.string.create_table_tb1);, AUTO_INCREMENT(mysql)
        String sql = "CREATE TABLE IF NOT EXISTS "+ SaveContracts.DBtb1Entry.TB_NAME+
                " ("+ SaveContracts.DBtb1Entry.COL_IDX+ " INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", "+ SaveContracts.DBtb1Entry.COL_NUM+ " INTEGER " +
                ", "+ SaveContracts.DBtb1Entry.COL_MESSAGE+ " TEXT NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

package com.jshstudy.allstudy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jshstudy.allstudy.data.engdata.EngDBDataC;

/**
 * Created by EMGRAM on 2017-12-26.
 */

public class EngAllDBHelper extends SQLiteOpenHelper{


    public EngAllDBHelper(Context context) {
        super(context, EngDBDataC.NAME_DB_DEFAULT_ENG, null, EngDBDataC.VERSION_DB_DEFAULT_ENG);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EngDBDataC.EngDB.QUERY_CREATE_TABLE);
        db.execSQL(EngDBDataC.EngDetailChapterDB.QUERY_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

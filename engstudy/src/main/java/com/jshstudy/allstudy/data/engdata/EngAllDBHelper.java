package com.jshstudy.allstudy.data.engdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jshstudy.allstudy.data.EngDataC;

/**
 * Created by EMGRAM on 2017-12-26.
 */

public class EngAllDBHelper extends SQLiteOpenHelper{


    public EngAllDBHelper(Context context) {
        super(context, EngDataC.EngDB.NAME_DB, null, EngDataC.EngDB.VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EngDataC.EngDB.QUERY_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

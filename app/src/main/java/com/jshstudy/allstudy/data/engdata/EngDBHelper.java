package com.jshstudy.allstudy.data.engdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jshstudy.allstudy.R;

/**
 * Created by shun6 on 2017-09-10.
 */

public class EngDBHelper extends SQLiteOpenHelper{
    private final static int dbVesion = 1;
    private Context context;

    public EngDBHelper(Context context){
        super(context
                , context.getResources().getString(R.string.eng_database)
                , null
                , dbVesion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(context.getString(R.string.eng_create_table_base));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

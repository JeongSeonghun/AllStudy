package jsh.example.com.allstudy.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import jsh.example.com.allstudy.R;

/**
 * Created by shun6 on 2017-09-10.
 */

public class SQLiteDBHelper extends SQLiteOpenHelper{
    private static final int version = 1;
    private Context context;

    public SQLiteDBHelper(Context context){
        super(context, context.getString(R.string.sq_database), null, version);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String dropTable = "DROP TABLE IF EXISTS sq_table";

        try{
            db.execSQL(dropTable);
        }catch (Exception e){
            e.printStackTrace();
        }


        String queryCreateTable = context.getString(R.string.sq_create_table);

        try{
            db.execSQL(queryCreateTable);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

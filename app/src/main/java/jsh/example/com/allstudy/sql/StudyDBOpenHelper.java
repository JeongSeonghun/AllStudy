package jsh.example.com.allstudy.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shun6 on 2017-09-05.
 */

public class StudyDBOpenHelper extends SQLiteOpenHelper{
    private static String DB_NAME = "AllStudy";
    private static int DB_VERSION = 1;
    private static String TABLE_NAME = "EngWord";

    public StudyDBOpenHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // DB 처음 생성시

        String DROP_SQL = "DROP TABLE IF EXIST " + TABLE_NAME;

        db.execSQL(DROP_SQL);

        String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + "(" +
                "eng text" +
                ", type text" +
                ", kor text";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

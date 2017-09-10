package jsh.example.com.allstudy.data;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import jsh.example.com.allstudy.R;

/**
 * Created by shun6 on 2017-09-10.
 */

public class AllStudyDB {
    private Context context;
    private SQLiteDatabase engDB;
    private EngDBHelper engHelper;
    private SQLiteDatabase sqDB;
    private SQLiteDBHelper sqHelper;

    public AllStudyDB(Context context){
        this.context = context;

        engHelper = new EngDBHelper(context);
        sqHelper = new SQLiteDBHelper(context);
    }

    public int insertEngBase(String eng, String kor){
        String insertQuery = String.format(context.getString(R.string.eng_insert_table_base2),eng,kor);

        engDB = engHelper.getWritableDatabase();
        try{
            engDB.execSQL(insertQuery);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            engDB.close();
            engDB = null;
        }
        return 0;
    }

    public int insertSq(String str, int i){
        String insertQuery = String.format(context.getString(R.string.sq_insert_table), str, i);

        sqDB = sqHelper.getWritableDatabase();
        try{
            sqDB.execSQL(insertQuery);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            sqDB.close();
            sqDB = null;
        }
        return 0;
    }

    public int deleteSq(){
        String deleteQuery = String.format(context.getString(R.string.sq_clear_table));

        sqDB = sqHelper.getWritableDatabase();
        try{
            sqDB.execSQL(deleteQuery);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }finally {
            sqDB.close();
            sqDB = null;
        }
        return 0;
    }

    public ArrayList<SQDBData> getSQList(){
        String selectQuery = String.format(context.getString(R.string.sq_select_table));
        ArrayList<SQDBData> sqList = new ArrayList<>();

        sqDB = sqHelper.getReadableDatabase();

        Cursor cursor = sqDB.rawQuery(selectQuery, null);

        if(cursor.getCount()>0){
            int idxIdx = cursor.getColumnIndex("idx");
            int strIdx = cursor.getColumnIndex("col_str");
            int iIdx = cursor.getColumnIndex("col_int");

            while (cursor.moveToNext()){
                int idx = idxIdx>=0 ? cursor.getInt(idxIdx) : -1;
                String str = strIdx>=0 ? cursor.getString(strIdx) : "";
                int i = iIdx>=0 ? cursor.getInt(iIdx) : -1;

                SQDBData data = new SQDBData(idx, str, i);

                sqList.add(data);

            }
        }

        return sqList;
    }
}

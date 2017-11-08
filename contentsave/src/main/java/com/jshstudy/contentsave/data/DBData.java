package com.jshstudy.contentsave.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jshstudy.common.util.LogUtil;
import com.jshstudy.contentsave.db.DBStudyDBHelper;
import com.jshstudy.contentsave.db.SaveContracts;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2017-11-07.
 */

public class DBData {
    private DBStudyDBHelper dbHelper;


    public DBData(Context ctx){
        dbHelper = new DBStudyDBHelper(ctx);
    }

    public void insertTb1(int num, String msg){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db == null) return;

        String sql = "INSERT INTO "+ SaveContracts.DBtb1Entry.TB_NAME
                + " ("+SaveContracts.DBtb1Entry.COL_NUM+", "+SaveContracts.DBtb1Entry.COL_MESSAGE+")"
                + " VALUES("+ num + ", '"+ msg+ "')";

        db.execSQL(sql);
    }

    public void updateTb1(int idx, int num, String msg){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db == null) return;

        String sql = "UPDATE "+SaveContracts.DBtb1Entry.TB_NAME + " SET "
                + SaveContracts.DBtb1Entry.COL_NUM +"= "+num+", "
                + SaveContracts.DBtb1Entry.COL_MESSAGE + "= '"+ msg
                + "' WHERE "+SaveContracts.DBtb1Entry.COL_IDX + "= "+idx;

        db.execSQL(sql);
    }

    public void deleteTb1(int idx){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db == null) return;

        String sql = "DELETE FROM "+SaveContracts.DBtb1Entry.TB_NAME
                + " WHERE "+SaveContracts.DBtb1Entry.COL_IDX + "="+idx;

        db.execSQL(sql);
    }

    public void deleteTb1(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db == null) return;

        String sql = "DELETE FROM "+SaveContracts.DBtb1Entry.TB_NAME;

        db.execSQL(sql);
    }

    public void alterTb1InitIdx(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db == null) return;

        String sql = "UPDATE SQLITE_SEQUENCE SET seq = 0 WHERE name = '"+SaveContracts.DBtb1Entry.TB_NAME+"'";
        // "ALTER TABLE "+SaveContracts.DBtb1Entry.TB_NAME+" AUTO_INCREMENT = 1"; -> sqlite는 안되는 듯...
        //delete from sqlite_sequence where name='"+SaveContracts.DBtb1Entry.TB_NAME+"'";
        db.execSQL(sql);
    }

    public ArrayList<TB1Data> selectTb1(){
        ArrayList<TB1Data> list = new ArrayList<>();
        Cursor cur = null;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if(db == null) return null;

//        String sql = "SELECT ("+SaveContracts.DBtb1Entry.COL_NUM+", "+SaveContracts.DBtb1Entry.COL_MESSAGE+")"
//                + "from "+ SaveContracts.DBtb1Entry.TB_NAME;
        String sql = "SELECT * from "+ SaveContracts.DBtb1Entry.TB_NAME;

        try{

            cur = db.rawQuery(sql, null);

            int idxIdx = cur.getColumnIndex(SaveContracts.DBtb1Entry.COL_IDX);
            int numIdx = cur.getColumnIndex(SaveContracts.DBtb1Entry.COL_NUM);
            int msgIdx = cur.getColumnIndex(SaveContracts.DBtb1Entry.COL_MESSAGE);

            while(cur.moveToNext()){
                int idx = -1;
                int num = -1;
                String msg = "";

                if(idxIdx != -1) idx = cur.getInt(idxIdx);
                if(numIdx != -1) num = cur.getInt(numIdx);
                if(msgIdx != -1) msg = cur.getString(msgIdx);

                TB1Data data = new TB1Data(idx, num, msg);
                list.add(data);
            }

        }catch (Exception e){
            LogUtil.DLog("selectTb1 error : "+ e);
        }finally {
            if(cur != null) cur.close();
        }

        return list;
    }

}

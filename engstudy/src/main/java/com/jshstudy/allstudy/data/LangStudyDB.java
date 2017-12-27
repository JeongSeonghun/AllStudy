package com.jshstudy.allstudy.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jshstudy.allstudy.data.engdata.EngAllDBHelper;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.common.data.ComDB;
import com.jshstudy.common.util.LogUtil;

import java.util.Locale;

/**
 * Created by EMGRAM on 2017-12-26.
 */

public class LangStudyDB {

    private EngAllDBHelper engHelper;

    public LangStudyDB(Context context){
        engHelper = new EngAllDBHelper(context);
    }

    public void close(){
        if(engHelper != null) engHelper.close();

        engHelper = null;
    }

    public void insertEng(EngData data){

        int checkIdx = selectEngCheck(data);
        if(checkIdx>0){
            EngData data1 = selectEng(checkIdx);

            if(data1.merge(data)){
                updateEng(data1);
            }

        }else{

            String chList = data.getWDataCh();
            String query;
            if(chList != null && chList.isEmpty()){
                query = String.format(EngDataC.EngDB.QUERY_INSERT_ENG
                        , chList, data.getEng(), data.getKor(), chList.replace("ch",""));
            }else{
                query = String.format(EngDataC.EngDB.QUERY_INSERT_ENG_NO_Ch,
                        data.getEng(), data.getKor());
            }

            logExecSQL(query);

        }
    }

    public int selectEngCheck(EngData data){
        SQLiteDatabase db = engHelper.getReadableDatabase();

        String query = String.format(EngDataC.EngDB.QUERY_SELECT_ENG_CHECK, data.getEng());

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);
        int idx  = -1;

        if(cur!=null && cur.moveToNext()){
            idx = cur.getInt(cur.getColumnIndex(ComDB.COL_IDX));
        }

        if(cur !=null) cur.close();

        return idx;
    }

    public EngData selectEng(int idx){
        SQLiteDatabase db = engHelper.getReadableDatabase();

        EngData data = null;

        String query = String.format(Locale.KOREA, EngDataC.EngDB.QUERY_SELECT_ENG, idx);

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);

        if(cur!=null && cur.getCount()>0 && cur.moveToNext()){
            data = new EngData();
            data.setData(cur);
        }

        if(cur !=null) cur.close();

        return data;
    }

    // kor, success, fail
    public void updateEng(EngData data){

        String query = String.format(EngDataC.EngDB.QUERY_UPDATE_ENG, data.getKor(), data.getIdx());

        logExecSQL(query);

    }

    public void updateSuccess(int idx, boolean success){

        EngData data = selectEng(idx);

        data.updateSuccess(success);

        String query;
        if(success){
            query = String.format(EngDataC.EngDB.QUERY_UPDATE_SUCCESS
                    , EngDataC.EngDB.COL_SUCCESS, data.getSuccess(), idx);
        }else{
            query = String.format(EngDataC.EngDB.QUERY_UPDATE_SUCCESS
                    , EngDataC.EngDB.COL_FAIL, data.getFail(), idx);
        }

        logExecSQL(query);

    }

    private void logRawQuery(String query){
        LogUtil.DLog(getClass().getSimpleName(), "raw query : "+query);
    }

    private void logExecSQL(String query){
        SQLiteDatabase db = engHelper.getWritableDatabase();
        LogUtil.DLog(getClass().getSimpleName(), "exec query : "+query);

        db.execSQL(query);
    }
}
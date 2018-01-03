package com.jshstudy.allstudy.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.engdata.EngCheckData;
import com.jshstudy.common.util.LogUtil;

/**
 * Created by shun6 on 2017-09-10.
 */

public class AllStudyDB {
    private Context context;
    private SQLiteDatabase engDB;
    private EngDBHelper engHelper;
    private final String TAG = AllStudyDB.class.getSimpleName();

    public AllStudyDB(Context context){
        this.context = context;

        engHelper = new EngDBHelper(context);
    }

    public void close(){
        engHelper.close();
        engHelper = null;
    }

    // eng check
    public int getEngSampleCnt(){
        engDB = engHelper.getReadableDatabase();

        if(engDB == null) return -1;

        String selQuery = context.getString(R.string.select_cnt_eng_sample);

        int cntRqw =-1;
        Cursor cursor = null;

        try{
            cursor = engDB.rawQuery(selQuery, null);

            cntRqw = cursor.getCount();
        }finally {
            if(cursor != null)cursor.close();
        }

        LogUtil.DLog(TAG, "getEngSampleCnt : "+ cntRqw);

        return cntRqw;
    }

    public EngCheckData getEngCheckData(int idx){
        engDB = engHelper.getReadableDatabase();

        if(engDB == null) return null;

        Cursor cursor = null;
        String selQuery = String.format(context.getString(R.string.select_data_eng_sample), idx);

        EngCheckData engCheckData = new EngCheckData();

        LogUtil.DLog(TAG, "query : "+ selQuery);

        try{
            cursor = engDB.rawQuery(selQuery, null);

            String[] keys = cursor.getColumnNames();

            if(cursor.getCount()<=0) return null;

            int numKey = 0;
            cursor.moveToNext();
            for(String key : keys){
                if (EngDataC.EngSample.KEY_IDX.equals(key)) {
                    engCheckData.setIdx(cursor.getInt(numKey));
                }else if (EngDataC.EngSample.KEY_ENG.equals(key)) {
                    engCheckData.setEng(cursor.getString(numKey));
                }else if (EngDataC.EngSample.KEY_KOR.equals(key)) {
                    engCheckData.setKor(cursor.getString(numKey));
                }else if (EngDataC.EngSample.KEY_TYPE.equals(key)) {
                    engCheckData.setType(cursor.getString(numKey));
                }else if (EngDataC.EngSample.KEY_SUCCESS.equals(key)) {
                    engCheckData.setSuccess(cursor.getInt(numKey));
                }else if (EngDataC.EngSample.KEY_FAIL.equals(key)) {
                    engCheckData.setFail(cursor.getInt(numKey));
                }
                numKey ++;
            }

        }finally {
            if(cursor != null)cursor.close();
        }

        LogUtil.DLog(TAG, "getEngCheckData : "+ engCheckData.toString());

        return engCheckData;
    }

    public void insertEngSample(String eng, String kor, String type){
        engDB = engHelper.getWritableDatabase();

        if(engDB == null) return;

        String insertQuery = String.format(context.getString(R.string.insert_data_eng_sample), eng, kor, type);

        engDB.execSQL(insertQuery);


    }

    public void updateSuccessFail(boolean isSuccess, int cnt, int idx){
        engDB = engHelper.getWritableDatabase();

        if(engDB == null) return;

        String updateQuery;
        if(isSuccess){
            updateQuery = String.format(context.getString(R.string.update_success_eng_sample), cnt, idx);
        }else{
            updateQuery = String.format(context.getString(R.string.update_fail_eng_sample), cnt, idx);
        }

        engDB.execSQL(updateQuery);
    }

}

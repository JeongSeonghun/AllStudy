package com.jshstudy.allstudy.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.engdata.EngCheckData;
import com.jshstudy.allstudy.data.engdata.EngDBHelper;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngWord;
import com.jshstudy.common.util.LogUtil;

/**
 * Created by shun6 on 2017-09-10.
 */

public class AllStudyDB {
    private Context context;
    private SQLiteDatabase engDB;
    private EngDBHelper engHelper;
    private SQLiteDatabase sqDB;
    private SQLiteDBHelper sqHelper;
    private final String TAG = AllStudyDB.class.getSimpleName();

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

    public void insertEngWord(EngWord word){
        engDB = engHelper.getWritableDatabase();

        if(engDB == null) return;

        String insertQuery = String.format(EngDataC.WordDB.QUERY_INSERT, word.getEng(), word.getKor(), word.getJSChapterList().toString());


    }

    // eng search
    public ArrayList<EngCheckData> selectEngSearch(int page){
        engDB = engHelper.getReadableDatabase();

        if(engDB == null) return null;

        String selQuery;
        if(page == 1){
            selQuery = String.format(context.getString(R.string.eng_search_sample_limit), 10);
        }else{
            int param1 = page*10;
            int param2 = (page-1)*10;
            selQuery = String.format(context.getString(R.string.eng_search_sample_offset), 10, param2);
        }

        ArrayList<EngCheckData> engCheckDatas = new ArrayList<>();

        LogUtil.DLog(TAG, "query : "+ selQuery);

        Cursor cursor = null;

        try{
            cursor = engDB.rawQuery(selQuery, null);

            String[] keys = cursor.getColumnNames();
            LogUtil.DLog(TAG, "search cnt : "+ cursor.getCount());
            if(cursor.getCount()<=0) return null;



            while (cursor.moveToNext()){
                int numKey = 0;
                EngCheckData engCheckData = new EngCheckData();
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

                engCheckDatas.add(engCheckData);
            }

            return engCheckDatas;

        }finally {
            if(cursor != null)cursor.close();
        }


    }
}

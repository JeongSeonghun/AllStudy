package com.jshstudy.allstudy.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jshstudy.allstudy.data.common.ChapterData;
import com.jshstudy.allstudy.data.common.SuccessData;
import com.jshstudy.allstudy.data.engdata.EngDBDataC;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngSearchData;
import com.jshstudy.allstudy.manager.EngDataManager;
import com.jshstudy.common.data.ComDB;
import com.jshstudy.common.util.LogUtil;
import com.jshstudy.common.util.StringUtil;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by EMGRAM on 2017-12-26.
 */

public class EngStudyDB {

    private EngAllDBHelper engHelper;
    private SQLiteDatabase db;

    public EngStudyDB(Context context){
        engHelper = new EngAllDBHelper(context);
    }

    public void open(Context context){
        if(engHelper == null) engHelper = new EngAllDBHelper(context);
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

            EngDataManager dataManager = new EngDataManager();
            String query = dataManager.makeEngDataInsertQuery(data);

            logWithExecSQL(query);

        }
    }

    public int selectEngCheck(EngData data){
        db = engHelper.getReadableDatabase();

        String query = String.format(EngDBDataC.EngDB.QUERY_SELECT_ENG_CHECK, data.getEng());

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);
        int idx  = -1;

        if(cur!=null && cur.moveToNext()){
            LogUtil.dLog(getClass().getSimpleName(), "cnt : "+ cur.getCount());
            idx = cur.getInt(cur.getColumnIndex(ComDB.COL_IDX));
        }

        if(cur !=null) cur.close();

        LogUtil.dLog(getClass().getSimpleName(), "check idx : "+idx);
        return idx;
    }

    public EngData selectEng(int idx){
        db = engHelper.getReadableDatabase();

        EngData data = null;

        String query = String.format(Locale.KOREA, EngDBDataC.EngDB.QUERY_SELECT_ENG, idx);

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);

        if(cur == null || cur.getCount()<=0) return null;

        if(cur.getCount()>0 && cur.moveToNext()){
            data = new EngData();
            data.setData(cur);
        }

        cur.close();

        LogUtil.dLog(getClass().getSimpleName(), "select eng : "+(data!=null?data.getEng():"null"));
        return data;
    }

    public ArrayList<EngData> selectEngSearch(int page){
        db = engHelper.getReadableDatabase();

        ArrayList<EngData> engList = new ArrayList<>();

        int param = (page-1)* EngDBDataC.EngDB.LIMIT_SEARCH;

        String query = String.format(Locale.KOREA,
                EngDBDataC.EngDB.QUERY_SELECT_ENG_OFFSET, EngDBDataC.EngDB.LIMIT_SEARCH, param);

        logRawQuery(query);

        Cursor cur = db.rawQuery(query,null);
        if(cur == null) return null;
        LogUtil.dLog(getClass().getSimpleName(), "selectEngSearch cnt : "+cur.getCount());
        while(cur.moveToNext()){
            EngData engData = new EngData();
            engData.setData(cur);

            if(engData.check()) engList.add(engData);
        }

        cur.close();

        LogUtil.dLog(getClass().getSimpleName(), "selectEngSearch cnt : "+engList.size());
        return engList;
    }

    public int selectEngCnt(){
        db = engHelper.getReadableDatabase();

        int cnt = -1;

        String query = EngDBDataC.EngDB.QUERY_SELECT_ENG_CNT;

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);

        if(cur != null){
            cnt = cur.getCount();

            cur.close();
        }

        LogUtil.dLog(getClass().getSimpleName(), "selectEngCnt cnt : "+cnt);
        return cnt;
    }

    public int selectEngSearchCnt(EngSearchData searchData){
        db = engHelper.getReadableDatabase();

        int cnt = -1;

        String query = EngDBDataC.EngDB.QUERY_SELECT_ENG_CNT + getWhereSearchEng(searchData);

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);

        if(cur != null){
            cnt = cur.getCount();

            cur.close();
        }
        LogUtil.dLog(getClass().getSimpleName(), "selectEngCnt cnt : "+cnt);

        return cnt;
    }

    private String getWhereSearchEng(EngSearchData searchData){
        StringBuilder sb = new StringBuilder(" WHERE ");

        boolean isEng = searchData.isEng();

        String word = searchData.getWord();

        if(!StringUtil.isEmpty(word)){
            if(isEng){
                // sb.append("UPPER(");
                sb.append("LOWER(");
                sb.append(EngDBDataC.EngDB.COL_ENG);
                sb.append(")");
            }else{
                sb.append(EngDBDataC.EngDB.COL_KOR);
            }

            sb.append(" LIKE LOWER('%").append(searchData.getWord()).append("%')");
        }

        switch (searchData.getSearchChapType()){
            case EngSearchData.TYPE_CHAPTER_SEARCH_ALL:
                break;
            case EngSearchData.TYPE_CHAPTER_SEARCH_NO:
                if(!StringUtil.isEmpty(word)){
                    sb.append(" AND (");
                }
                sb.append(EngDBDataC.EngDB.COL_NCH).append("=1");

                if(!StringUtil.isEmpty(word)){
                    sb.append(")");
                }
                break;
            case EngSearchData.TYPE_CHAPTER_SEARCH_CONTAIN:
                if(!StringUtil.isEmpty(word)){
                    sb.append(" AND (");
                }
                for(ChapterData chapter : searchData.getChapters()){
                    sb.append(String.format(Locale.KOREA, EngDBDataC.EngDB.COL_CH, chapter.getChapterNum()));
                    sb.append(" IS NOT NULL").append(" OR ");
                }
                sb.delete(sb.lastIndexOf("OR"), sb.length());

                if(!StringUtil.isEmpty(word)){
                    sb.append(")");
                }
                break;
        }

        return sb.toString();
    }

    public ArrayList<EngData> selectEngSearch(int page, EngSearchData searchData){

        db = engHelper.getReadableDatabase();

        ArrayList<EngData> engList = new ArrayList<>();

        int param = (page-1)* EngDBDataC.EngDB.LIMIT_SEARCH;

        String query = String.format(Locale.KOREA,
                EngDBDataC.EngDB.QUERY_SELECT_ENG_OFFSET_WHERE, getWhereSearchEng(searchData), EngDBDataC.EngDB.LIMIT_SEARCH, param);

        logRawQuery(query);

        Cursor cur = db.rawQuery(query,null);
        if(cur == null) return null;
        LogUtil.dLog(getClass().getSimpleName(), "selectEngSearch cnt : "+cur.getCount());
        while(cur.moveToNext()){
            EngData engData = new EngData();
            engData.setData(cur);

            if(engData.check()) engList.add(engData);
        }

        cur.close();

        LogUtil.dLog(getClass().getSimpleName(), "selectEngSearch cnt : "+engList.size());
        return engList;
    }

    public SuccessData selectSuccessSum(){
        db = engHelper.getReadableDatabase();

        String query = EngDBDataC.EngDB.QUERY_SELECT_ENG_SUCCESS_SUM;

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);

        if(cur == null) return null;

        cur.moveToNext();
        SuccessData data = new SuccessData();
        for(String col : cur.getColumnNames()){
            LogUtil.dLog("selectSuccessSum col: "+col);
            if(col.equals(String.format(ComDB.FORMAT_FUNC_FORMAT_SUM, EngDBDataC.EngDB.COL_SUCCESS))){
                data.setCntSuccess(cur.getInt(cur.getColumnIndex(col)));
            }
            if(col.equals(String.format(ComDB.FORMAT_FUNC_FORMAT_SUM, EngDBDataC.EngDB.COL_FAIL))){
                data.setCntFail(cur.getInt(cur.getColumnIndex(col)));
            }
        }
        LogUtil.dLog("selectSuccessSum : "+data.getCntSuccess()+"/"+data.getCntFail());

        cur.close();

        return data;
    }

    public int selectLastWordIdx(){
        db = engHelper.getReadableDatabase();

        String query = EngDBDataC.EngDB.QUERY_SELECT_ENG_LAST_IDX;

        logRawQuery(query);

        Cursor cur = db.rawQuery(query, null);

        if(cur == null || cur.getCount()<=0) return -1;

        cur.moveToNext();

        int lastIdx = cur.getInt(cur.getColumnIndex(ComDB.COL_IDX));

        cur.close();

        return lastIdx;
    }

    // kor, success, fail
    public void updateEng(EngData data){

        String query = String.format(EngDBDataC.EngDB.QUERY_UPDATE_ENG, data.getMean(), data.getIdx());

        logWithExecSQL(query);

    }

    public void updateEngAll(EngData data){

        EngDataManager dataManager = new EngDataManager();

        String query = dataManager.makeUpdateEngDatQuery(data);

        logWithExecSQL(query);

    }

    public void updateSuccess(int idx, boolean success){

        EngData data = selectEng(idx);

        data.updateSuccess(success);

        String query;
        if(success){
            query = String.format(EngDBDataC.EngDB.QUERY_UPDATE_SUCCESS
                    , EngDBDataC.EngDB.COL_SUCCESS, data.getSuccess(), idx);
        }else{
            query = String.format(EngDBDataC.EngDB.QUERY_UPDATE_SUCCESS
                    , EngDBDataC.EngDB.COL_FAIL, data.getFail(), idx);
        }

        logWithExecSQL(query);

    }

    public void updateSuccessInit(){
        String query = EngDBDataC.EngDB.QUERY_UPDATE_SUCCESS_INIT;

        logWithExecSQL(query);
    }

    public void updateWordIdxInit(){
        String query = EngDBDataC.EngDB.QUERY_UPDATE_IDX_INIT;

        logWithExecSQL(query);
    }

    public void deleteWord(int idx){
        String query = String.format(Locale.KOREA, EngDBDataC.EngDB.QUERY_DELETE_WORD, idx);

        logWithExecSQL(query);
    }

    public void deleteWordAll(){
        String query = EngDBDataC.EngDB.QUERY_DELETE_WORD_ALL;

        logWithExecSQL(query);

        updateWordIdxInit();
    }

    private void logRawQuery(String query){
        LogUtil.dLog(getClass().getSimpleName(), "raw query : "+query);
    }

    private void logWithExecSQL(String query){
        SQLiteDatabase db = engHelper.getWritableDatabase();
        LogUtil.dLog(getClass().getSimpleName(), "exec query : "+query);

        db.execSQL(query);
    }
}

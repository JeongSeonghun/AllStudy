package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

import com.jshstudy.allstudy.AppConfig;
import com.jshstudy.allstudy.data.EngDataC;
import com.jshstudy.allstudy.manager.EngDataManager;
import com.jshstudy.common.data.ComDB;
import com.jshstudy.common.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * Created by shun6 on 2017-09-05.
 */

public class EngData {
    private int idx;
    private String mean;
    private String eng;
    private int success = -1;
    private int fail = -1;
    private HashMap<String, EngMeanData> meanMap;
    private HashMap<Integer, JSONArray> chapMap;
    private ArrayList<EngChapterData> chapList;

    // eng, kor, ch, success, fail
    public EngData(){

    }

    public void setIdx(int idx){
        this.idx = idx;
    }

    public int getIdx(){
        return idx;
    }

    public void setMean(String mean){
        this.mean = mean;
    }
    public void setMean(String type, String... kor){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        try {
            for(String mean : kor){
                ja.put(mean);
            }

            jo.put(type, ja);

            this.mean = jo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getMean(){
        return mean;
    }

    public void setEng(String eng){
        this.eng = eng;
    }

    public String getEng(){
        return eng;
    }

    public void setCh(int ch){
        if(ch <= 0) return;

        if(chapMap == null) chapMap = new HashMap<>();

        JSONArray ja = new JSONArray();
        ja.put(String.valueOf(ch));

        chapMap.put(ch, ja);
    }

    // JSONArray text
    public void setCh(int ch, String detailChapter){
        LogUtil.dLog("setCh data : "+ch+" / "+detailChapter);
        if(detailChapter == null || detailChapter.isEmpty()) return;

        if(chapMap == null) chapMap = new HashMap<>();
        if(chapList == null) chapList = new ArrayList<>();

        try {
            JSONArray ja = new JSONArray(detailChapter);
            chapMap.put(ch, ja);

            EngChapterData chapterData = new EngChapterData();
            chapterData.setChapter(ch);
            chapterData.setSubList(ja);
            chapList.add(chapterData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setCh(int ch, JSONArray detailChapter){
        LogUtil.dLog("setChap data : "+ch+" / "+detailChapter);
        if(ch<=0 || detailChapter ==null || detailChapter.length()<=0) return;

        if(chapMap == null) chapMap = new HashMap<>();
        chapMap.put(ch, detailChapter);

        if(chapList == null) chapList = new ArrayList<>();

        EngChapterData chapterData = new EngChapterData();
        chapterData.setChapter(ch);
        chapterData.setSubList(detailChapter);
        chapList.add(chapterData);
    }

    public ArrayList<Integer> getChapList(){
        if(chapMap == null || chapMap.size()<=0) return null;

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(chapMap.keySet());

        return list;
    }

    public ArrayList<EngChapterData> getChapDataList(){
        return chapList;
    }

    public HashMap<Integer, JSONArray> getChapMap(){
        return chapMap;
    }

    public void setSuccess(int success){
        this.success = success;
    }

    public int getSuccess(){
        return success;
    }

    public void setFail(int fail){
        this.fail = fail;
    }

    public int getFail(){
        return fail;
    }

    public void updateSuccess(boolean success){
        if(success){
            this.success += 1;
        }else{
            fail +=1;
        }
    }

    public void setData(Cursor cur){
        String colChMatch = "^ch[0-9]+$";
        for(String col : cur.getColumnNames()){

            if(col.equals(ComDB.COL_IDX)){
                setIdx(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_ENG)){
                setEng(cur.getString(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_KOR)){
                setMean(cur.getString(cur.getColumnIndex(col)));
                setMeanData();
            }

            if(col.equals(EngDataC.EngDB.COL_SUCCESS)){
                setSuccess(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_FAIL)){
                setFail(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.matches(colChMatch)){
                setCh(Integer.valueOf(col.substring(2)), cur.getString(cur.getColumnIndex(col)));
            }
        }
    }

    public boolean merge(EngData data){
        if(mean == null || mean.equals(data.getMean())) return false;

        boolean changeMean = mergeMean(data);
        boolean changeChap = mergeChDetail(data);

        return changeMean || changeChap;
    }

    private boolean mergeMean(EngData data){

        if(meanMap == null || meanMap.size()<=0) setMeanData();

        LogUtil.dLog(getClass().getSimpleName(), "merge comp : "+mean+"/"+data.getMean());

        HashMap<String, EngMeanData> chkMap = data.getMeanMap();
        Set<String> set = chkMap.keySet();

        boolean chkChange = false;

        for(String type : set){
            LogUtil.dLog(getClass().getSimpleName(), "merge contain : "+type+"->"+(meanMap.containsKey(type)));
            if(meanMap.containsKey(type)){
                EngMeanData data1 = meanMap.get(type);
                if(data1.merge(chkMap.get(type))){
                    meanMap.remove(type);
                    meanMap.put(type, data1);
                    chkChange = true;
                }
            }else{
                meanMap.put(type, chkMap.get(type));
                chkChange = true;
            }
        }

        if(chkChange){
            EngDataManager dataManager = new EngDataManager();
            mean = dataManager.makeMeanMapToJSON(meanMap).toString();
        }

        return chkChange;
    }

    public boolean mergeChDetail(EngData data){
        HashMap<Integer, JSONArray> chkMap = data.getChapMap();
        if(chkMap == null || chkMap.size()<=0 || chkMap.equals(chapMap)) return false;
        boolean change = false;

        Set<Integer> set = chkMap.keySet();

        for(int chap : set){
            JSONArray chk = chkMap.get(chap);
            if(chapMap.containsKey(chap)){

                ArrayList<String> chkList = createArrayS(chk);
                JSONArray base = chapMap.get(chap);
                ArrayList<String> baseList = createArrayS(base);

                for(String detail : chkList){
                    if(!baseList.contains(detail)){
                        change = true;
                        base.put(detail);
                    }
                }

                if(change){
                    chapMap.remove(chap);
                    chapMap.put(chap, base);
                }

            }else{
                chapMap.put(chap, chk);
                change = true;
            }
        }

        return change;
    }

    public ArrayList<String> createArrayS(JSONArray ja){
        ArrayList<String> list = new ArrayList<>();
        for(int cnt =0 ; cnt < ja.length() ; cnt++){
            try {
                list.add(ja.getString(cnt));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public HashMap<String, EngMeanData> getMeanMap(){
        if(meanMap == null || meanMap.size()<=0) setMeanData();
        return meanMap;
    }

    // kor {type1:[mean1, mean2...], type2:[mean...]...}

    private void setMeanData(){
        meanMap = new HashMap<>();
        JSONObject jo;
        try {
            jo = new JSONObject(mean);

            Iterator<String> iterator = jo.keys();
            while(iterator.hasNext()){
                String type = iterator.next();
                JSONArray ja = jo.getJSONArray(type);

                EngMeanData meanData = new EngMeanData();
                meanData.setType(type);

                for(int idx = 0; idx<ja.length() ; idx++){
                    meanData.setMean(ja.getString(idx));
                }

                meanMap.put(type, meanData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean check(){
        LogUtil.dLog(getClass().getSimpleName(), "check : "+ (eng != null && !eng.isEmpty() && meanMap != null && meanMap.size()>0));
        return eng != null && !eng.isEmpty() && meanMap != null && meanMap.size()>0;
    }

    public String toString(){
        EngDataManager dataManager = new EngDataManager();
        return "idx : " + idx + "\n" +
                "eng : " + eng + "\n" +
                "kor : " + dataManager.makeMeanMapToJSON(meanMap).toString() + "\n" +
                "chapter : " + chapMap + "\n";
    }
}

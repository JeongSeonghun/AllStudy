package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

import com.jshstudy.allstudy.manager.EngDataManager;
import com.jshstudy.common.data.ComDB;
import com.jshstudy.common.util.LogUtil;
import com.jshstudy.common.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
        eng = "";
        mean = "";
        meanMap = new HashMap<>();
    }

    public void setIdx(int idx){
        this.idx = idx;
    }

    public int getIdx(){
        return idx;
    }

    private void setMean(String mean){
        this.mean = mean;
        setMeanData();
    }
    public void setMean(JSONObject means){
        this.mean = means.toString();
        setMeanData();
    }
    public void setMean(String type, String... means){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        try {
            for(String mean : means){
                ja.put(mean);
            }

            jo.put(type, ja);

            this.mean = jo.toString();
            setMeanData();
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
    private void setCh(int ch, String detailChapter){
        LogUtil.dLog("setCh data : "+ch+" / "+detailChapter);
        if(detailChapter == null || detailChapter.isEmpty()) return;

        if(chapMap == null) chapMap = new HashMap<>();
        if(chapList == null) chapList = new ArrayList<>();

        try {
            JSONArray ja = new JSONArray(detailChapter);

            setCh(ch, ja);
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

            if(col.equals(EngDBDataC.EngDB.COL_ENG)){
                setEng(cur.getString(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDBDataC.EngDB.COL_KOR)){
                setMean(cur.getString(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDBDataC.EngDB.COL_SUCCESS)){
                setSuccess(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDBDataC.EngDB.COL_FAIL)){
                setFail(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.matches(colChMatch)){
                setCh(Integer.valueOf(col.substring(2)), cur.getString(cur.getColumnIndex(col)));
            }
        }
    }

    // not compare eng
    public boolean merge(EngData data){
        LogUtil.dLog("merge()");

        boolean changeMean = mergeMean(data);
        boolean changeChap = mergeChDetail(data);

        return changeMean || changeChap;
    }

    private boolean mergeMean(EngData data){
        LogUtil.dLog(getClass().getSimpleName(), "merge comp : "+mean+"/"+data.getMean());
        HashMap<String, EngMeanData> chkMap = data.getMeanMap();
        if(meanMap == null || meanMap.size()<=0){
            if(chkMap == null || chkMap.size()<=0) return false;
            LogUtil.dLog("merge add");
            meanMap = chkMap;
            EngDataManager dataManager = new EngDataManager();
            mean = dataManager.makeMeanMapToJSON(meanMap).toString();
            return true;
        }else if(meanMap.equals(chkMap)){
            LogUtil.dLog("merge equal");
            return false;
        }

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
        LogUtil.dLog(getClass().getSimpleName(), "merge comp : "+chapMap+"/"+data.getChapMap());
        HashMap<Integer, JSONArray> chkMap = data.getChapMap();
        if(chapMap == null || chapMap.size()<=0){
            if(chkMap == null || chkMap.size()<=0) return false;
            LogUtil.dLog("merge add");
            chapMap = chkMap;
            return true;
        }else if(chapMap.equals(chkMap)){
            LogUtil.dLog("merge equal");
            return false;
        }

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
        if(StringUtil.isEmpty(mean)) return;

        JSONObject jo;
        try {
            LogUtil.dLog("setMeanData mean : "+mean);
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
        JSONObject jo = dataManager.makeMeanMapToJSON(meanMap);
        return "idx : " + idx + "\n" +
                "eng : " + eng + "\n" +
                "mean : " + (jo==null?"":jo.toString()) + "\n" +
                "chapter : " + chapMap + "\n";
    }
}

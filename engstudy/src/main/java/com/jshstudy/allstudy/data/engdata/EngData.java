package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

import com.jshstudy.allstudy.AppConfig;
import com.jshstudy.allstudy.data.EngDataC;
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
    private String kor;
    private String eng;
    private ArrayList<Integer> chList;
    private int success = -1;
    private int fail = -1;
    private HashMap<String, EngMeanData> meanMap;
    private HashMap<Integer, JSONArray> chapMap;

    // eng, kor, ch, success, fail
    public EngData(){

    }

    public void setIdx(int idx){
        this.idx = idx;
    }

    public int getIdx(){
        return idx;
    }

    public void setKor(String kor){
        this.kor = kor;
    }
    public void setKor(String type, String... kor){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();

        try {
            for(String mean : kor){
                ja.put(mean);
            }

            jo.put(type, ja);

            this.kor = jo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getKor(){
        return kor;
    }

    public void setEng(String eng){
        this.eng = eng;
    }

    public String getEng(){
        return eng;
    }

    public void setCh(int ch){
        if(ch <= 0) return;

        if(chList == null) chList = new ArrayList<>();

        chList.add(ch);
    }

    public void setCh(int ch, String detailChapter){
        if(detailChapter == null || detailChapter.isEmpty()) return;

        if(chapMap == null) chapMap = new HashMap<>();

        try {
            chapMap.put(ch, new JSONArray(detailChapter));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setCh(int ch, JSONArray detailChapter){
        if(ch<=0 || detailChapter == null || detailChapter.length()<=0) return;

        chapMap.put(ch, detailChapter);
    }

    public ArrayList<Integer> getCh(){
        return chList;
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
        String colChMatch = "^ch[0-9]$";
        for(String col : cur.getColumnNames()){

            if(col.equals(ComDB.COL_IDX)){
                setIdx(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_ENG)){
                setEng(cur.getString(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_KOR)){
                setKor(cur.getString(cur.getColumnIndex(col)));
                setMeanData();
            }

            if(col.equals(EngDataC.EngDB.COL_SUCCESS)){
                setSuccess(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_FAIL)){
                setFail(cur.getInt(cur.getColumnIndex(col)));
            }

            LogUtil.DLog("setData chk chapter : "+col.matches(colChMatch));
            if(col.matches(colChMatch)){
                if(AppConfig.isPaid){
                    setCh(Integer.valueOf(col.substring(2)), cur.getString(cur.getColumnIndex(col)));
                }else{
                    setCh(cur.getInt(cur.getColumnIndex(col)));
                }

            }
        }
    }

    public boolean merge(EngData data){
        if(kor == null || kor.equals(data.getKor())) return false;

        boolean changeMean = mergeMean(data);
        boolean changeChap = AppConfig.isPaid ? mergeChDetail(data) : mergeCh(data);

        return changeMean || changeChap;
    }

    private boolean mergeMean(EngData data){

        if(meanMap == null || meanMap.size()<=0) setMeanData();

        LogUtil.DLog(getClass().getSimpleName(), "merge comp : "+kor+"/"+data.getKor());

        HashMap<String, EngMeanData> chkMap = data.getMeanMap();
        Set<String> set = chkMap.keySet();

        boolean chkChange = false;

        for(String type : set){
            LogUtil.DLog(getClass().getSimpleName(), "merge contain : "+type+"->"+(meanMap.containsKey(type)));
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
            kor = getWDataKor();
        }

        return chkChange;
    }

    private boolean mergeCh(EngData data){
        if(chList !=null && chList.equals(data.getCh())) return false;

        boolean change = false;
        for(int chapter : data.getCh()){
            if(!chList.contains(chapter)){
                chList.add(chapter);
                change = true;
            }
        }

        return change;
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

    public String getWDataKor(){
        if(meanMap == null || meanMap.size()<=0) return null;

        JSONObject jo = new JSONObject();

        Set<String> set = meanMap.keySet();

        for(String type : set){
            JSONArray ja = new JSONArray();
            try {
                for(String mean : meanMap.get(type).getMeans()){
                    ja.put(mean);
                }
                jo.put(type, ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jo.toString();
    }

    public String getWDataCh(){
        if(chList == null || chList.size()<=0) return null;

        StringBuilder sb = new StringBuilder();
        int lastCh = chList.get(chList.size()-1);

        for(int ch : chList){
            sb.append(String.format(Locale.KOREA, EngDataC.EngDB.COL_CH, ch));

            if(lastCh != ch) sb.append(",");
        }

        return sb.toString();
    }

    // kor {type1:[mean1, mean2...], type2:[mean...]...}

    private void setMeanData(){
        meanMap = new HashMap<>();
        JSONObject jo;
        try {
            jo = new JSONObject(kor);

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
        LogUtil.DLog(getClass().getSimpleName(), "check : "+ (eng != null && !eng.isEmpty() && meanMap != null && meanMap.size()>0));
        return eng != null && !eng.isEmpty() && meanMap != null && meanMap.size()>0;
    }

    public String toString(){
        return "idx : " + idx + "\n" +
                "eng : " + eng + "\n" +
                "kor : " + getWDataKor() + "\n" +
                "chapter : " + getWDataCh() + "\n";
    }
}

package com.jshstudy.allstudy.data.engdata;

import android.database.Cursor;

import com.jshstudy.allstudy.data.EngDataC;

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
    private ArrayList<KorData> korList;
    private String eng;
    private ArrayList<Integer> chList;
    private int success = -1;
    private int fail = -1;

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

    public ArrayList<Integer> getCh(){
        return chList;
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

            if(col.equals(EngDataC.EngDB.COL_ENG)){
                setEng(cur.getString(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_KOR)){
                setKor(cur.getString(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_SUCCESS)){
                setSuccess(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.equals(EngDataC.EngDB.COL_FAIL)){
                setFail(cur.getInt(cur.getColumnIndex(col)));
            }

            if(col.matches(colChMatch)){
                setCh(cur.getInt(cur.getColumnIndex(col)));
            }
        }
    }

    public boolean merge(EngData data){
        if(kor != null && kor.equals(data.getKor())) return false;

        HashMap<String, KorData> chkMap = data.getMeanMap();
        Set<String> set = chkMap.keySet();

        for(String type : set){
            if(meanMap.containsKey(type)){
                KorData data1 = meanMap.get(type);
                if(data1.merge(chkMap.get(type))){
                    meanMap.remove(type);
                    meanMap.put(type, data1);
                }
            }else{
                meanMap.put(type, chkMap.get(type));
            }
        }

        return true;
    }

    public HashMap<String, KorData> getMeanMap(){
        return meanMap;
    }

    public String getWDataKor(){
        return null;
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
    private HashMap<String, KorData> meanMap;
    private void setKorData(){
        korList = new ArrayList<>();
        meanMap = new HashMap<>();
        JSONObject jo;
        try {
            jo = new JSONObject(kor);

            Iterator<String> iterator = jo.keys();
            while(iterator.hasNext()){
                String type = iterator.next();
                JSONArray ja = jo.getJSONArray(type);

                KorData korData = new KorData();
                korData.setType(type);

                for(int idx = 0; idx<ja.length() ; idx++){
                    korData.setMean(ja.getString(idx));
                }

                korList.add(korData);
                meanMap.put(type, korData);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<KorData> getKorData(){
        return korList;
    }

    public class KorData{
        String type = null;
        ArrayList<String> means;

        public void setType(String type){
            this.type = type;
        }

        public String getType(){
            return type;
        }

        public void setMean(String mean){
            if(means == null) means = new ArrayList<>();

            means.add(mean);
        }

        public ArrayList<String> getMeans(){
            return means;
        }

        public boolean merge(KorData data){
            if(this.equals(data)) return false;

            for(String mean : data.getMeans()){
                if(!means.contains(mean)) means.add(mean);
            }

            return true;
        }
    }
}

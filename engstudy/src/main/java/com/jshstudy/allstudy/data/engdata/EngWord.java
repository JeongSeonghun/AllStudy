package com.jshstudy.allstudy.data.engdata;

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

public class EngWord {
    private String eng;
    private HashMap<String, ArrayList<String>> means;
    private ArrayList<Integer> chList;

    public EngWord(){
        means = new HashMap<>();
        chList = new ArrayList<>();
    }

    public void setEng(String eng){
        this.eng = eng;
    }

    public String getEng(){
        return eng;
    }

    public void addKor(String type, String kor){
//        EngData engData = new EngData(type, kor);
//        setKor(engData);

        Set<String> sets = means.keySet();
        if(sets.contains(type)){
            ArrayList<String> korList = means.get(type);

            if(!korList.contains(kor)){
                korList.add(kor);
            }
        }
    }

    // kor {verb: [kor1, kor2 ...]}
    public void setKor(String korDatas){
        parseKor(korDatas);
    }

    public void parseKor(String data){
        try {
            JSONObject object = new JSONObject(data);

            Iterator<String> iterator = object.keys();
            while (iterator.hasNext()){
                String key = iterator.next();
                JSONArray array = object.getJSONArray(key);
                ArrayList<String> list = new ArrayList<>();
                int size = array.length();

                for(int idx = 0 ; idx < size ; idx++){
                    list.add(array.getString(idx));
                }

                means.put(key, list);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getKor(String type){
        Set<String> key = means.keySet();
        if(key.contains(type)){
            return means.get(type);
        }
        return null;
    }

    public String getKor(){
        return changeJSON().toString();
    }

    private JSONObject changeJSON(){
        JSONObject object = new JSONObject();

        Set<String> sets = means.keySet();

        for(String key : sets){
            JSONArray ja = new JSONArray(means.get(key));

            try {
                object.put(key, ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return object;
    }

    public void setChList(ArrayList<Integer> chList){
        this.chList = chList;
    }

    public void setChList(String strChList){
        try {
            JSONArray ja = new JSONArray(strChList);

            int size = ja.length();

            for(int idx = 0; idx < size ; idx ++){
                chList.add(ja.getInt(idx));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getChList(){
        return chList;
    }

    public String getStrChList(){
        JSONArray ja = new JSONArray(chList);
        return ja.toString();
    }


    public class EngType{
        public static final String KEY_ADJ = "adjective";
        public static final String KEY_VERB = "verb";
        public static final String KEY_N = "noun";
        public static final String KEY_ADV = "adverb";
        public static final String KEY_CON = "conjunction";
        public static final String KEY_PHR = "phrase";

        public static final int a = 101; // = adj
        public static final int v = 102;
        public static final int n = 103;
        public static final int adv = 104; // 부사
        public static final int conjunction = 105; // 전치사
        public static final int phr = 106; // 관용구, 구

    }

    public class EngPosType{
        public static final int s = 201;
        public static final int v = 202;
        public static final int s_c = 203;
        public static final int o = 204;
        public static final int d_o = 205;
        public static final int i_o = 206;
        public static final int o_c = 207;
    }

    public class EngTense{
        public static final int current = 301;
        public static final int current_ing = 302;
        public static final int current_have = 303;
        public static final int ed = 304;
        public static final int ed_ing = 305;
        public static final int ed_have = 306;
        public static final int will = 307;
        public static final int will_ing = 308;
        public static final int will_have = 309;
    }

    public class EngChapter{
        public static final int QUANTITY_ADJECTIVES = 1;
        public static final int VERB = 2;
        public static final int TENSE = 3;
        public static final int GERUND = 4;
        public static final int TO_INFINITIVE = 5;
        public static final int PARTICIPLE = 6;
        public static final int CONJUNCTION = 7;
        public static final int RELATIONSHIP_PRONOUN_ADVERB = 8;
        public static final int O_CONJUNCTION = 9;
        public static final int PREPOSITION = 10;
        public static final int INVERSION = 11;
    }
}

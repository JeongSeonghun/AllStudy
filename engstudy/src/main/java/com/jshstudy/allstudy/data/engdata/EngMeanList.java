package com.jshstudy.allstudy.data.engdata;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by shun6 on 2017-12-29.
 */

public class EngMeanList {
    private HashMap<String, EngMeanData> meanMap;

    public void parse(String means){
        meanMap = new HashMap<>();
        JSONObject jo;
        try {
            jo = new JSONObject(means);

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

    public HashMap<String, EngMeanData> getMeanMap(){
        return meanMap;
    }
}

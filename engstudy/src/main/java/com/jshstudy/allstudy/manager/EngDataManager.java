package com.jshstudy.allstudy.manager;

import com.jshstudy.allstudy.data.EngDataC;
import com.jshstudy.allstudy.data.common.EditData;
import com.jshstudy.allstudy.data.engdata.EngChapterData;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngMeanData;
import com.jshstudy.allstudy.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Created by shun6 on 2017-12-30.
 */

public class EngDataManager {

    public String makeEngDataInsertQuery(EngData data){
        String query = null;
        String rowChap = makeInsertChapRow(data);

        if(StringUtil.isEmpty(rowChap)){
            query = String.format(EngDataC.EngDB.QUERY_INSERT_ENG_NO_Ch,
                    data.getEng(), data.getMean());
        }else{
            query = String.format(EngDataC.EngDB.QUERY_INSERT_ENG
                    , rowChap, data.getEng(), data.getMean(), makeInsertChapValue(data));
        }

        return query;
    }

    private String makeInsertChapRow(EngData data){
        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> chapList = data.getChapList();

        if(chapList == null || chapList.size()<=0) return null;

        for(int chap : chapList){
            sb.append(String.format(Locale.KOREA, EngDataC.EngDB.COL_CH, chap)).append(",");
        }

        sb.deleteCharAt(sb.lastIndexOf(","));

        return sb.toString();
    }

    private String makeInsertChapValue(EngData data){
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, JSONArray> chapMap = data.getChapMap();

        Set<Integer> set = chapMap.keySet();
        for(int chap : set){
            sb.append(chapMap.get(chap).toString()).append(",");
        }

        sb.deleteCharAt(sb.lastIndexOf(","));

        return sb.toString();
    }

    public JSONObject makeMeanMapToJSON(HashMap<String, EngMeanData> meanMap){
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

        return jo;
    }

    public JSONObject makeMeanMapToJSON(ArrayList<EditData> meanEditList){
        if(meanEditList == null || meanEditList.size()<=0) return null;

        JSONObject jo = new JSONObject();

        for(EditData data : meanEditList){
            JSONArray ja = new JSONArray(data.getValues());
            try {
                jo.put(data.getParam(), ja);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return jo;
    }


}

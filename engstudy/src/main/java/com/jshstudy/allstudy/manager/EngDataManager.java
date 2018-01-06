package com.jshstudy.allstudy.manager;

import com.jshstudy.allstudy.data.EngDataC;
import com.jshstudy.allstudy.data.common.EditData;
import com.jshstudy.allstudy.data.engdata.EngChapterData;
import com.jshstudy.allstudy.data.engdata.EngCommon;
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

    public String makeUdateEngDatQuery(EngData engData){
        String eng = engData.getEng();
        String mean = engData.getMean();
        HashMap<Integer, JSONArray> chapMap = engData.getChapMap();
        int noChap = 0;
        int idx = engData.getIdx();
        String query;

        StringBuilder sb = new StringBuilder();
        for(EngCommon.Chapter chap : EngCommon.Chapter.values()){
            sb.append(chap.simpleName()).append("=");
            if(chapMap.containsKey(chap.num())){
                JSONArray ja = chapMap.get(chap.num());
                sb.append("'");
                if(ja != null && ja.length()>0){
                    sb.append(ja.toString());
                }else{
                    sb.append(chap.num());
                }
                sb.append("'");
            }else{
                sb.append("null");
            }
            sb.append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));

        if(chapMap != null && chapMap.size()>0){
            noChap = 0;
        }else{
            noChap = 1;
        }

        query=String.format(EngDataC.EngDB.QUERY_UPDATE_ENG_ALL,
                eng, mean, sb.toString(), noChap, idx);

        return query;
    }
}

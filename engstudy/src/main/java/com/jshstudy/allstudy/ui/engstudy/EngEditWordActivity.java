package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.custom.adapter.EditAdapter;
import com.jshstudy.allstudy.data.CommonData;
import com.jshstudy.allstudy.data.EngStudyDB;
import com.jshstudy.allstudy.data.engdata.EditSubData;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngMeanData;
import com.jshstudy.common.util.LogUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class EngEditWordActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isEditMode = true;
    private int idxEngWord = -1;

    private EditText et_value_eng_edit_eng;
    private Button btn_save_edit;
    private ListView lv_mean_edit_eng;
    private ListView lv_chap_edit_eng;

    private EditAdapter meansAdapter;
    private EditAdapter chapsAdapter;

    private EngStudyDB engStudyDB;
    private EngData engData;

    private ArrayList<String> meanParamList;
    private ArrayList<String> chapParamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_edit_word);

        initUi();
        initData();

    }

    private void initUi(){
        et_value_eng_edit_eng = (EditText)findViewById(R.id.et_value_eng_edit_eng);
        btn_save_edit = (Button)findViewById(R.id.btn_save_edit);
        lv_mean_edit_eng = (ListView)findViewById(R.id.lv_mean_edit_eng);
        lv_chap_edit_eng = (ListView)findViewById(R.id.lv_chap_edit_eng);

        btn_save_edit.setOnClickListener(this);

        setAdapter();
    }

    private void setAdapter(){
        meansAdapter = new EditAdapter(this);
        chapsAdapter = new EditAdapter(this);

        meansAdapter.setTextList1(null);
        meansAdapter.setTextList2(null);
        chapsAdapter.setTextList1(null);
        chapsAdapter.setTextList2(null);

        meansAdapter.setListener(meansListener);
        chapsAdapter.setListener(chapsListener);

        lv_mean_edit_eng.setAdapter(meansAdapter);
        lv_chap_edit_eng.setAdapter(chapsAdapter);
    }

    private void setTestData(){
        if(isEditMode){
            ArrayList<String> testMean = new ArrayList<>();
            ArrayList<String> testChap = new ArrayList<>();

            HashMap<String, EngMeanData> meanMap = engData.getMeanMap();
            HashMap<Integer, JSONArray> chapMap = engData.getChapMap();

//            testMean.add(meanMap.toString());
//            meansAdapter.setTextList1(testMean);
//
//            if(chapMap!=null && chapMap.size()>0){
//                testChap.add(chapMap.toString());
//                chapsAdapter.setTextList1(testChap);
//            }
//
            meanParamList = new ArrayList<>();
            ArrayList<String> meanValue = new ArrayList<>();

            chapParamList = new ArrayList<>();
            ArrayList<String> chapValue = new ArrayList<>();

            Set<String> setMean = meanMap.keySet();
            for(String meanP : setMean){
                meanParamList.add(meanP);
                meanValue.add(meanMap.get(meanP).getMeans().toString());
            }

            Set<Integer> setChap = chapMap.keySet();
            for(int chapP : setChap){
                chapParamList.add(String.valueOf(chapP));
                chapValue.add(chapMap.get(chapP).toString());
            }

            meansAdapter.setTextList1(meanParamList);
            meansAdapter.setTextList2(meanValue);

            chapsAdapter.setTextList1(chapParamList);
            chapsAdapter.setTextList2(chapValue);

            meansAdapter.notifyDataSetChanged();
            chapsAdapter.notifyDataSetChanged();

        }
    }

    private void initData(){
        Intent intentRec = getIntent();

        if(intentRec.hasExtra(CommonData.IntentData.KEY_MOD)){
            int mode = intentRec.getIntExtra(CommonData.IntentData.KEY_MOD, -1);
            if(CommonData.IntentData.VALUE_MOD_ADD == mode){
                isEditMode = false;
                engData = new EngData();
            }else{
                idxEngWord = intentRec.getIntExtra(CommonData.IntentData.KEY_IDX, 1);
                engStudyDB = new EngStudyDB(this);
                engData = engStudyDB.selectEng(idxEngWord);

                et_value_eng_edit_eng.setText(engData.getEng());

                setTestData();
            }
        }

    }

    private EditAdapter.EditAdapterListener meansListener = new EditAdapter.EditAdapterListener() {
        @Override
        public void onClick(int pos, boolean isEdit) {
            LogUtil.DLog("mean onClick : "+pos+"/"+isEdit);
            setMeanData(pos, isEdit);
        }

        @Override
        public void onClickPlus() {
            LogUtil.DLog("mean onClickPlus");
            addMean();
        }
    };

    private EditAdapter.EditAdapterListener chapsListener = new EditAdapter.EditAdapterListener() {
        @Override
        public void onClick(int pos, boolean isEdit) {
            LogUtil.DLog("chap onClick : "+pos+"/"+isEdit);
            setChapData(pos, isEdit);
        }

        @Override
        public void onClickPlus() {
            LogUtil.DLog("mean onClickPlus");
            addChapter();
        }
    };

    private void setMeanData(int pos, boolean isEdit){
        EngMeanData meanData = engData.getMeanMap().get(meanParamList.get(pos));

        EditSubData subData = new EditSubData();

        subData.setTitle(meanData.getType());
        subData.setList(meanData.getMeans());

        startSubEdit(subData, isEdit);
    }

    private void setChapData(int pos, boolean isEdit){

    }

    private void addMean(){

    }

    private void addChapter(){

    }

    private void startSubEdit(EditSubData subData, boolean isEdit){
        Intent intentAct = new Intent(this, EngEditSubActivity.class);
        intentAct.putExtra(CommonData.IntentData.KEY_SUB_DATA, subData);

        int reqCode;
        if(isEdit){
            reqCode = CommonData.REQ_CODE_EDIT;
        }else{
            reqCode = CommonData.REQ_CODE_ADD;
        }
        startActivityForResult(intentAct, reqCode);
    }

    public void save(){
        LogUtil.DLog("save");
        String eng = et_value_eng_edit_eng.getText().toString();
        engData.setEng(eng);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_edit:
                save();
                break;
        }
    }

}

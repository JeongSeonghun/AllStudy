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
import com.jshstudy.allstudy.data.common.CommonData;
import com.jshstudy.allstudy.data.EngStudyDB;
import com.jshstudy.allstudy.data.common.EditData;
import com.jshstudy.allstudy.data.common.EditSubData;
import com.jshstudy.allstudy.data.engdata.EngChapterData;
import com.jshstudy.allstudy.data.engdata.EngCommon;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngMeanData;
import com.jshstudy.allstudy.manager.EngDataManager;
import com.jshstudy.common.util.LogUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

public class EngEditWordActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isEditMode = true;
    private int idxEngWord = -1;

    private EditText et_value_eng_edit_eng;
    private Button btn_save_edit;
    private Button btn_reset_edit;
    private ListView lv_mean_edit_eng;
    private ListView lv_chap_edit_eng;

    private EditAdapter meansAdapter;
    private EditAdapter chapsAdapter;

    private EngStudyDB engStudyDB;
    private EngData engData;

    private ArrayList<EditData> meanEditList;
    private ArrayList<EditData> chapEditList;

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
        btn_reset_edit = (Button)findViewById(R.id.btn_reset_edit);
        lv_mean_edit_eng = (ListView)findViewById(R.id.lv_mean_edit_eng);
        lv_chap_edit_eng = (ListView)findViewById(R.id.lv_chap_edit_eng);

        btn_save_edit.setOnClickListener(this);
        btn_reset_edit.setOnClickListener(this);

        setAdapter();
    }

    private void setAdapter(){
        meansAdapter = new EditAdapter(this);
        chapsAdapter = new EditAdapter(this);

        meansAdapter.setListener(meansListener);
        chapsAdapter.setListener(chapsListener);

        lv_mean_edit_eng.setAdapter(meansAdapter);
        lv_chap_edit_eng.setAdapter(chapsAdapter);
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

                setListData();
            }
        }

    }

    private void setListData(){
        if(isEditMode){

            HashMap<String, EngMeanData> meanMap = engData.getMeanMap();
            ArrayList<EngChapterData> chapList = engData.getChapDataList();

            meanEditList = new ArrayList<>();
            chapEditList = new ArrayList<>();

            for(String type : meanMap.keySet()){
                EditData editData = new EditData();
                editData.setData(type, meanMap.get(type).getMeans());
                meanEditList.add(editData);
            }

            for(EngChapterData chapterData : chapList){
                EditData editData = new EditData();
                editData.setParam(String.format(Locale.KOREA
                        , CommonData.Format.FORMAT_SIMPLE_CHAPTER, chapterData.getChapter()));
                editData.setValues(chapterData.getSubList());
                chapEditList.add(editData);
            }

            meansAdapter.setDataList(meanEditList);
            chapsAdapter.setDataList(chapEditList);

            meansAdapter.notifyDataSetChanged();
            chapsAdapter.notifyDataSetChanged();

        }
    }

    private EditAdapter.EditAdapterListener meansListener = new EditAdapter.EditAdapterListener() {
        @Override
        public void onClick(int pos, boolean isEdit) {
            LogUtil.dLog("mean onClick : "+pos+"/"+isEdit);
            editMean(pos);
        }

        @Override
        public void onClickPlus() {
            LogUtil.dLog("mean onClickPlus");
            addMean();
        }
    };

    private EditAdapter.EditAdapterListener chapsListener = new EditAdapter.EditAdapterListener() {
        @Override
        public void onClick(int pos, boolean isEdit) {
            LogUtil.dLog("chap onClick : "+pos+"/"+isEdit);
            editChapter(pos);
        }

        @Override
        public void onClickPlus() {
            LogUtil.dLog("mean onClickPlus");
            addChapter();
        }
    };

    private void editMean(int pos){
        EngMeanData meanData = engData.getMeanMap().get(meanEditList.get(pos).getParam());
        ArrayList<String> meanTypes = getMeanTypes();

        EditSubData subData = new EditSubData();

        subData.setList(meanData.getMeans());
        subData.setTitleList(meanTypes);
        subData.setPostTitle(meanTypes.indexOf(meanData.getType()));
        subData.setPos(pos);

        startSubEdit(subData, CommonData.REQ_CODE_EDIT_MEAN);
    }

    private void editChapter(int pos){
        EngChapterData chapterData = engData.getChapDataList().get(pos);
        ArrayList<String> chapList = getChapList();

        EditSubData subData = new EditSubData();

        subData.setList(chapterData.getSubList());
        subData.setTitleList(chapList);
        subData.setPostTitle(chapList.indexOf(String.valueOf(chapterData.getChapter())));
        subData.setPos(pos);

        startSubEdit(subData, CommonData.REQ_CODE_EDIT_CHAP);
    }

    private void addMean(){
        EditSubData subData = new EditSubData();

        ArrayList<String> types = getMeanTypes();

        if(meanEditList.size() == EngCommon.Type.values().length) return;

        for(EditData data : meanEditList){
            if(types.contains(data.getParam())){
                types.remove(data.getParam());
            }
        }

        subData.setTitleList(types);

        startSubEdit(subData, CommonData.REQ_CODE_ADD_MEAN);
    }

    private void addChapter(){
        EditSubData subData = new EditSubData();

        ArrayList<String> chaps = getChapList();

        if(meanEditList.size() == EngCommon.Type.values().length) return;

        for(EditData data : chapEditList){
            if(chaps.contains(data.getParam())){
                chaps.remove(data.getParam());
            }
        }

        subData.setTitleList(chaps);

        startSubEdit(subData, CommonData.REQ_CODE_ADD_CHAP);
    }

    private ArrayList<String> getMeanTypes(){
        ArrayList<String> types = new ArrayList<>();
        for(EngCommon.Type type : EngCommon.Type.values()){
            types.add(type.name());
        }
        return types;
    }

    private ArrayList<String> getChapList(){
        ArrayList<String> chaps = new ArrayList<>();
        for(EngCommon.Chapter chapter : EngCommon.Chapter.values()){
            chaps.add(chapter.simpleName());
        }
        return chaps;
    }

    private void startSubEdit(EditSubData subData, int reqCode){
        Intent intentAct = new Intent(this, EngEditSubActivity.class);
        intentAct.putExtra(CommonData.IntentData.KEY_SUB_DATA, subData);

        startActivityForResult(intentAct, reqCode);
    }

    public void save(){
        LogUtil.dLog("save");
        String eng = et_value_eng_edit_eng.getText().toString();
        EngDataManager dataManager = new EngDataManager();

        EngData changeData = new EngData();
        changeData.setEng(eng);
        changeData.setMean(dataManager.makeMeanMapToJSON(meanEditList));
        for(EditData editData : chapEditList){
            changeData.setCh(Integer.valueOf(editData.getParam().substring(2)), new JSONArray(editData.getValues()));
        }

        LogUtil.dLog("save before data : "+engData.toString());
        compare(engData, changeData);
        LogUtil.dLog("save after data : "+engData.toString());
        // insert or update
        //finish();
    }

    public boolean compare(EngData base, EngData data){
        boolean change = false;
        if(!base.getEng().equals(data.getEng())){
            change = true;
            base.setEng(data.getEng());
        }
        return change || base.merge(data);
    }

    private void reset(){
        et_value_eng_edit_eng.setText(engData.getEng());
        setListData();
    }

    private void changEditData(int reqCode){
        switch (reqCode){
            case CommonData.REQ_CODE_ADD_MEAN:
            case CommonData.REQ_CODE_EDIT_MEAN:
                meansAdapter.setDataList(meanEditList);
                meansAdapter.notifyDataSetChanged();
                break;
            case CommonData.REQ_CODE_ADD_CHAP:
            case CommonData.REQ_CODE_EDIT_CHAP:
                chapsAdapter.setDataList(chapEditList);
                chapsAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            EditSubData subData = data.getParcelableExtra(CommonData.IntentData.KEY_SUB_DATA);
            LogUtil.dLog("onActivityResult "+requestCode+" subData : "+subData.getTitleList()+"/"+subData.getList());

            EditData editData = new EditData();
            editData.setData(subData);
            LogUtil.dLog("onActivityResult editData : "+editData.getParam());

            switch (requestCode){
                case CommonData.REQ_CODE_ADD_MEAN:
                    meanEditList.add(editData);
                    break;
                case CommonData.REQ_CODE_EDIT_MEAN:
                    meanEditList.set(subData.getPos(), editData);
                    break;
                case CommonData.REQ_CODE_ADD_CHAP:
                    chapEditList.add(editData);
                    break;
                case CommonData.REQ_CODE_EDIT_CHAP:
                    chapEditList.set(subData.getPos(), editData);
                    break;
            }

            changEditData(requestCode);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_edit:
                save();
                break;
            case R.id.btn_reset_edit:
                reset();
                break;
        }
    }

}

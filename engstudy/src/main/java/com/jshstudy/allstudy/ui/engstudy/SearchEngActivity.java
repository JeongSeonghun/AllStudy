package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.custom.adapter.ChapterAdapter;
import com.jshstudy.allstudy.data.common.ChapterData;
import com.jshstudy.allstudy.data.common.CommonData;
import com.jshstudy.allstudy.data.EngStudyDB;
import com.jshstudy.allstudy.data.engdata.EngData;
import com.jshstudy.allstudy.data.engdata.EngSearchData;
import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;

public class SearchEngActivity extends AppCompatActivity implements View.OnClickListener{

    private int page = 1;
    private int totalCnt = 0;
    private int totalPage = 0;

    private ArrayList<EngData> dataList;
    private EngStudyDB engStudyDB;
    private SearchListAdapter listAdapter;

    private ListView list_eng_search;
    private Button pre_eng_search;
    private Button next_eng_search;
    private TextView title_eng_search;
    private TextView page_eng_search;
    private Spinner sel_ch_search;
    private Spinner sel_lang_search;
    private EditText lang_search;
    private Button eng_search;
    private Button reset_search;

    private ArrayAdapter<String> selLangAdapter;
    private ChapterAdapter chapterAdapter;
    private EngSearchData engSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_search);

        initUi();
        initData();
    }

    private void initUi(){
        list_eng_search = (ListView)findViewById(R.id.list_eng_search);
        pre_eng_search = (Button)findViewById(R.id.pre_eng_search);
        next_eng_search = (Button)findViewById(R.id.next_eng_search);
        title_eng_search = (TextView)findViewById(R.id.title_eng_search);
        page_eng_search = (TextView)findViewById(R.id.page_eng_search);
        sel_ch_search = (Spinner)findViewById(R.id.sel_ch_search);
        sel_lang_search = (Spinner)findViewById(R.id.sel_lang_search);
        lang_search = (EditText)findViewById(R.id.lang_search);
        eng_search = (Button)findViewById(R.id.eng_search);
        reset_search = (Button)findViewById(R.id.reset_search);

        pre_eng_search.setOnClickListener(this);
        next_eng_search.setOnClickListener(this);
        eng_search.setOnClickListener(this);
        reset_search.setOnClickListener(this);

        list_eng_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.dLog("click position: "+ position);
                goEdit(position);
            }
        });

        setAdapter();
    }

    private void goEdit(int pos){
        Intent intentAct = new Intent(getApplicationContext(), EngEditWordActivity.class);
        intentAct.putExtra(CommonData.IntentData.KEY_MOD, CommonData.IntentData.VALUE_MOD_EDIT);
        intentAct.putExtra(CommonData.IntentData.KEY_IDX, dataList.get(pos).getIdx());

        startActivityForResult(intentAct, CommonData.REQ_CODE_EDIT);
    }

    private void setAdapter(){
        // result
        listAdapter = new SearchListAdapter();
        list_eng_search.setAdapter(listAdapter);

        // chapter list
        chapterAdapter = new ChapterAdapter(this);
        sel_ch_search.setAdapter(chapterAdapter);

        // selectable word type
        selLangAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        selLangAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        selLangAdapter.addAll(createLangList());

        sel_lang_search.setAdapter(selLangAdapter);
    }

    private ArrayList<String> createLangList(){
        ArrayList<String> langList = new ArrayList<>();
        langList.add("eng");
        langList.add("mean");

        return  langList;
    }

    private void initData(){
        engStudyDB = new EngStudyDB(this);

        totalCnt = engStudyDB.selectEngCnt();
        totalPage = totalCnt/10 + 1;

        String title = "Eng Search -> total : "+totalCnt;
        title_eng_search.setText(title);


        searchEng(page);
    }

    private Handler mHandler = new Handler();

    private void searchEng(final int page){
        if(engSearchData != null){
            dataList = engStudyDB.selectEngSearch(page, engSearchData);
        }else{
            dataList = engStudyDB.selectEngSearch(page);
        }

        LogUtil.dLog("search page : "+page);

        if(dataList != null) LogUtil.dLog("search cnt : "+dataList.size());

        listAdapter.setList(dataList);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                page_eng_search.setText(String.valueOf(page)+"/"+totalPage);
                listAdapter.notifyDataSetChanged();
            }
        });

    }

    private void movePageNext(boolean isNext){

        if(isNext){
            if(page*10>=totalCnt) return;

            page+=1;
        }else{
            if(page <= 1){
                page = 1;
                return;
            }

            page-=1;
        }

        searchEng(page);
    }

    private void searchEng(){
        engSearchData = createEngSearchData();

        if(engSearchData == null){
            searchEng(page);
            return;
        }

        totalCnt = engStudyDB.selectEngSearchCnt(engSearchData);

        totalPage = totalCnt/10 + 1;

        String title = "Eng Search -> total : "+totalCnt;
        title_eng_search.setText(title);

        page = 1;
        searchEng(page);

    }

    private EngSearchData createEngSearchData(){
        String word = null;
        String selLang = null;
        boolean isEng = false;
        ArrayList<ChapterData> chapList = new ArrayList<>();

        word = lang_search.getText().toString();

        selLang = (String)sel_lang_search.getSelectedItem();
        if("eng".equals(selLang)){
            isEng = true;
        }

        for(ChapterData chapter : chapterAdapter.getChapList()){
            if(chapter.isCheck()) chapList.add(chapter);
        }

        LogUtil.dLog("createEngSearchData : "+isEng+"/"+word +"/"+chapList.size());

        EngSearchData searchData = new EngSearchData();
        searchData.setWord(isEng, word);
        searchData.setChapters(chapList);

        if(!searchData.check()) return null;

        return searchData;
    }

    private void reset(){
        chapterAdapter.init();
        chapterAdapter.notifyDataSetChanged();
        sel_lang_search.setSelection(0);
        lang_search.setText("");
        engSearchData = null;
        page = 1;
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            searchEng();

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pre_eng_search:
                movePageNext(false);
                break;
            case R.id.next_eng_search:
                movePageNext(true);
                break;
            case R.id.eng_search:
                searchEng();
                break;
            case R.id.reset_search:
                reset();
                break;
        }
    }

    private class SearchListAdapter extends BaseAdapter{
        private LayoutInflater inflater;
        private ArrayList list;

        public SearchListAdapter(){
            inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            list = new ArrayList();
        }

        public void setList(ArrayList<EngData> list){
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            EngData data = (EngData)getItem(position);
            LogUtil.dLog(getClass().getSimpleName(), "view eng : "+data.toString());
            Holder holder;
            if(convertView == null){
                convertView = inflater.inflate(R.layout.item_eng_search, null);
                holder = new Holder();
                holder.idx_eng_search = (TextView)convertView.findViewById(R.id.idx_eng_search);
                holder.eng_eng_search = (TextView)convertView.findViewById(R.id.eng_eng_search);
                holder.kor_eng_search = (TextView)convertView.findViewById(R.id.kor_eng_search);
                holder.type_eng_search = (TextView)convertView.findViewById(R.id.type_eng_search);
                holder.result_eng_search = (TextView)convertView.findViewById(R.id.result_eng_search);
            }else{
                holder = (Holder)convertView.getTag();
            }

            holder.idx_eng_search.setText(String.valueOf(data.getIdx()));
            holder.eng_eng_search.setText(data.getEng());
            holder.kor_eng_search.setText(data.getMean());
            if(data.getChapMap() !=null && data.getChapMap().size()>0)
            holder.type_eng_search.setText(data.getChapList().toString());

            String result = String.valueOf(data.getSuccess())+":"+String.valueOf(data.getFail());
            holder.result_eng_search.setText(result);

            convertView.setTag(holder);

            return convertView;
        }

        private class Holder{
            public TextView idx_eng_search;
            public TextView eng_eng_search;
            public TextView kor_eng_search;
            public TextView type_eng_search;
            public TextView result_eng_search;

        }

    }



}

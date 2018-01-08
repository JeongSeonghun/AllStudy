package com.jshstudy.allstudy.custom.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.engdata.EngDBDataC;
import com.jshstudy.allstudy.data.common.ChapterData;
import com.jshstudy.allstudy.data.common.CommonData;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by EMGRAM on 2018-01-03.
 */

public class ChapterAdapter extends BaseAdapter{

    private ArrayList<ChapterData> chapList = new ArrayList<>();
    private Context context;

    private boolean isGetView = false;

    public ChapterAdapter(Context context){
        this.context = context;

        init();
    }

    public void init(){
        if(chapList != null) chapList.clear();
        chapList.add(new ChapterData(CommonData.Chapter.chapterAll.code(), CommonData.Chapter.chapterAll.simple()));
        for(int cnt = 1; cnt <= EngDBDataC.EngDB.TOTAL_CH ; cnt++){
            chapList.add(new ChapterData(cnt, String.format(Locale.KOREA, EngDBDataC.EngDB.COL_CH, cnt)));
        }
        chapList.add(new ChapterData(CommonData.Chapter.chapterNo.code(), CommonData.Chapter.chapterNo.simple()));
    }

    public ArrayList<ChapterData> getChapList(){
        return chapList;
    }

    @Override
    public int getCount() {
        return chapList.size()+1;
    }

    @Override
    public ChapterData getItem(int position) {
        return chapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        isGetView = true;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_chapter_eng, null);

            holder = new ViewHolder();
            holder.cb_chap_item_eng = (CheckBox)convertView.findViewById(R.id.cb_chap_item_eng);
            holder.tv_chap_item_eng = (TextView)convertView.findViewById(R.id.tv_chap_item_eng);
            holder.cb_chap_item_eng.setOnCheckedChangeListener(checkedChangeListener);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if(position == 0){
            isGetView = false;
            holder.cb_chap_item_eng.setVisibility(View.INVISIBLE);
            holder.tv_chap_item_eng.setText(R.string.chapter);

        }else{
            ChapterData chapter = getItem(position-1);
            holder.cb_chap_item_eng.setTag(R.string.key_item_cb, position-1);
            holder.cb_chap_item_eng.setVisibility(View.VISIBLE);
            if(holder.cb_chap_item_eng.isChecked() != chapter.isCheck()){
                holder.cb_chap_item_eng.setChecked(chapter.isCheck());
            }else{
                isGetView = false;
            }
            holder.tv_chap_item_eng.setText(chapter.getChapterStr());

        }

        convertView.setTag(holder);

        return convertView;
    }

    private class ViewHolder{
        public CheckBox cb_chap_item_eng;
        public TextView tv_chap_item_eng;
    }

    private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int pos = (int)buttonView.getTag(R.string.key_item_cb);

            if(!isGetView){
                changeChapter(pos, isChecked);
            }else{
                isGetView =false;
            }
        }
    };

    private void changeChapter(int position, boolean isChecked){
        ChapterData chapter = getItem(position);
        chapter.setCheck(isChecked);

        if(chapter.getChapterNum() == -1 || chapter.getChapterNum() == 0){
            init();
        }else{
            if(chapList.get(0).isCheck()||chapList.get(chapList.size()-1).isCheck()){
               init();
            }
        }

        chapList.set(position, chapter);

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });

    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

}

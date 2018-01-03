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
import com.jshstudy.allstudy.data.EngDataC;
import com.jshstudy.common.util.LogUtil;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by EMGRAM on 2018-01-03.
 */

public class ChapterAdapter extends BaseAdapter{

    private ArrayList<Chapter> chapList = new ArrayList<>();
    private Context context;

    private boolean isGetView = false;

    public ChapterAdapter(Context context){
        this.context = context;

        init();
    }

    public void init(){
        if(chapList != null) chapList.clear();
        chapList.add(new Chapter(EngDataC.Chapter.CHAPTER_ALL, "all"));
        for(int cnt = 1; cnt <= EngDataC.EngDB.TOTAL_CH ; cnt++){
            chapList.add(new Chapter(cnt, String.format(Locale.KOREA, EngDataC.EngDB.COL_CH, cnt)));
        }
        chapList.add(new Chapter(EngDataC.Chapter.CHAPTER_NO, "no"));
    }

    public ArrayList<Chapter> getChapList(){
        return chapList;
    }

    @Override
    public int getCount() {
        return chapList.size()+1;
    }

    @Override
    public Chapter getItem(int position) {
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
            Chapter chapter = getItem(position-1);
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
        Chapter chapter = getItem(position);
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

    public class Chapter{
        private int chapterNum = -1;
        private String chapterStr = null;
        private boolean check = false;

        public Chapter(int chapterNum, String chapterStr){
            setChapterNum(chapterNum);
            setChapterStr(chapterStr);
        }

        public void setChapterNum(int chapterNum){
            this.chapterNum = chapterNum;
        }

        public int getChapterNum(){
            return chapterNum;
        }

        public void setChapterStr(String chapterStr){
            this.chapterStr = chapterStr;
        }

        public String getChapterStr(){
            return chapterStr;
        }

        public void setCheck(boolean check){
            this.check = check;
        }

        public boolean isCheck(){
            return check;
        }
    }
}

package com.jshstudy.allstudy.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jshstudy.allstudy.R;

import java.util.ArrayList;

/**
 * Created by shun6 on 2018-01-03.
 */

public class EditAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private EditAdapterListener listener;
    private ArrayList<String> textList1, textList2;

    public EditAdapter(Context context){
        this.context = context;
    }

    public EditAdapter(Context context, EditAdapterListener listener){
        this.context = context;
        setListener(listener);
    }

    public void setTextList1(ArrayList<String> textList1){
        this.textList1 = textList1;
    }

    public void setTextList2(ArrayList<String> textList2){
        this.textList2 = textList2;
    }

    public void setListener(EditAdapterListener listener){
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if(textList1 != null && textList1.size()>0) return textList1.size()+1;
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(position!=getCount()-1){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_edit, null);

            TextView tv_txt1_edit = (TextView)convertView.findViewById(R.id.tv_txt1_edit);
            TextView tv_txt2_edit = (TextView)convertView.findViewById(R.id.tv_txt2_edit);
            Button btn_add_edit = (Button) convertView.findViewById(R.id.btn_add_edit);
            Button btn_del_edit = (Button) convertView.findViewById(R.id.btn_del_edit);

            btn_add_edit.setOnClickListener(this);
            btn_del_edit.setOnClickListener(this);

            tv_txt1_edit.setText(textList1.get(position));

            if(textList2 == null || textList2.size()<=0 || position>textList2.size()-1){
                tv_txt2_edit.setVisibility(View.GONE);
            }else{
                tv_txt2_edit.setVisibility(View.VISIBLE);
                tv_txt2_edit.setText(" : "+textList2.get(position));
            }

            btn_add_edit.setTag(position);
            btn_del_edit.setTag(position);

        }else{
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_edit_plus, null);

            Button btn_add_edit_plus = (Button) convertView.findViewById(R.id.btn_add_edit_plus);

            btn_add_edit_plus.setOnClickListener(this);
        }

        return convertView;
    }

    @Override
    public void onClick(View v) {
        int pos;
        switch (v.getId()){
            case R.id.btn_add_edit:
                pos = (int)v.getTag();
                if(listener != null) listener.onClick(pos, true);
                break;
            case R.id.btn_del_edit:
                pos = (int)v.getTag();
                if(listener != null) listener.onClick(pos, false);
                break;
            case R.id.btn_add_edit_plus:
                if(listener != null) listener.onClickPlus();
                break;
        }
    }

    public interface EditAdapterListener{
        void onClick(int pos, boolean isEdit);
        void onClickPlus();
    }
}

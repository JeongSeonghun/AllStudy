package com.jshstudy.allstudy.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.jshstudy.allstudy.R;
import com.jshstudy.common.util.LogUtil;
import com.jshstudy.common.util.StringUtil;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2018-01-04.
 */

public class EditSubAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private ArrayList<String> list = new ArrayList<>();

    private boolean isGetView = false;

    private boolean isAdd = false;
    private int posAdd = -1;

    public EditSubAdapter(Context context){
        this.context = context;
    }

    public void setList(ArrayList<String> list){
        this.list = list;
    }

    public ArrayList<String> getList(){
        return list;
    }

    @Override
    public int getCount() {
        if(list != null){
            posAdd = isAdd?list.size():-1;
            return isAdd?list.size()+2:list.size()+1;
        }
        return 1;
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
        isGetView = true;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(position==getCount()-1){
            isGetView =false;
            convertView = inflater.inflate(R.layout.item_edit_plus, null);
            Button btn_add_edit_plus = (Button)convertView.findViewById(R.id.btn_add_edit_plus);
            btn_add_edit_plus.setOnClickListener(this);
        }else{
            convertView = inflater.inflate(R.layout.item_edit_sub, null);

            ViewHolder holder = new ViewHolder();
            holder.pos = position;

            holder.lil_sub_edit = (LinearLayout)convertView.findViewById(R.id.lil_sub_edit);
            holder.et_text_sub_edit = (EditText)convertView.findViewById(R.id.et_text_sub_edit);
            holder.lil_btn_sub_edit = (LinearLayout)convertView.findViewById(R.id.lil_btn_sub_edit);
            holder.btn_apply_sub_edit = (Button)convertView.findViewById(R.id.btn_apply_sub_edit);
            holder.btn_cancel_sub_edit = (Button)convertView.findViewById(R.id.btn_cancel_sub_edit);
            holder.btn_del_edit_sub = (Button)convertView.findViewById(R.id.btn_del_edit_sub);

            holder.btn_apply_sub_edit.setOnClickListener(this);
            holder.btn_cancel_sub_edit.setOnClickListener(this);
            holder.btn_del_edit_sub.setOnClickListener(this);

            if(position == posAdd){
                holder.et_text_sub_edit.setText("");
            }else{
                holder.et_text_sub_edit.setText(list.get(position));
            }

            holder.et_text_sub_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!isGetView){
                        ViewHolder holder = (ViewHolder)v.getTag();
                        if(hasFocus){
                            holder.lil_btn_sub_edit.setVisibility(View.VISIBLE);
                            holder.btn_del_edit_sub.setVisibility(View.GONE);
                        }else{
                            holder.lil_btn_sub_edit.setVisibility(View.GONE);
                            holder.btn_del_edit_sub.setVisibility(View.VISIBLE);
                        }
                    }else{
                        isGetView = false;
                    }
                }
            });

            holder.et_text_sub_edit.setTag(holder);
            holder.btn_apply_sub_edit.setTag(holder);
            holder.btn_cancel_sub_edit.setTag(holder);
            holder.btn_del_edit_sub.setTag(position);
        }
        return convertView;
    }

    private class ViewHolder{
        int pos = 0;
        LinearLayout lil_sub_edit;
        EditText et_text_sub_edit;
        LinearLayout lil_btn_sub_edit;
        Button btn_apply_sub_edit;
        Button btn_cancel_sub_edit;
        Button btn_del_edit_sub;
    }

    @Override
    public void onClick(View v) {
        ViewHolder holder;
        int pos;
        switch(v.getId()){
            case R.id.btn_add_edit_plus:
                isAdd = true;
                notifyDataSetChanged();
                break;
            case R.id.btn_apply_sub_edit:
                holder = (ViewHolder)v.getTag();
                String edit = holder.et_text_sub_edit.getText().toString();
                LogUtil.dLog("click "+holder.pos+"/"+isAdd+"/"+posAdd);
                if(holder.pos == posAdd){
                    isAdd = false;
                    if(!StringUtil.isEmpty(edit)){
                        list.add(edit);
                    }
                }else if(StringUtil.isEmpty(edit)){
                    list.remove(holder.pos);
                }else{
                    list.set(holder.pos, edit);
                }
                notifyDataSetChanged();
                break;
            case R.id.btn_cancel_sub_edit:
                holder = (ViewHolder)v.getTag();
                String before = list.get(holder.pos);
                holder.et_text_sub_edit.setText(before);
                break;
            case R.id.btn_del_edit_sub:
                pos = (int)v.getTag();
                if(pos == posAdd){
                    isAdd = false;
                }else{
                    list.remove(pos);
                }
                notifyDataSetChanged();
                break;
        }
    }
}

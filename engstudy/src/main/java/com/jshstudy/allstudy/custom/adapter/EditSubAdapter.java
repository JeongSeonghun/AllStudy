package com.jshstudy.allstudy.custom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jshstudy.allstudy.R;

import java.util.ArrayList;

/**
 * Created by EMGRAM on 2018-01-04.
 */

public class EditSubAdapter extends BaseAdapter implements View.OnClickListener{

    private Context context;
    private ArrayList<String> list = new ArrayList<>();
    private EditSubListener listener;

    private int posFocus = 0;
    private boolean isGetView = false;

    public EditSubAdapter(Context context){
        this.context = context;
    }

    public void setList(ArrayList<String> list){
        this.list = list;
    }

    public void setListener(EditSubListener listener){
        this.listener = listener;
    }

    @Override
    public int getCount() {
        if(list != null) return list.size()+1;
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

            holder.tv_text_sub_edit = (TextView)convertView.findViewById(R.id.tv_text_sub_edit);
            holder.lil_sub_edit = (LinearLayout)convertView.findViewById(R.id.lil_sub_edit);
            holder.et_text_sub_edit = (EditText)convertView.findViewById(R.id.et_text_sub_edit);
            holder.lil_btn_sub_edit = (LinearLayout)convertView.findViewById(R.id.lil_btn_sub_edit);
            holder.btn_apply_sub_edit = (Button)convertView.findViewById(R.id.btn_apply_sub_edit);
            holder.btn_cancel_sub_edit = (Button)convertView.findViewById(R.id.btn_cancel_sub_edit);

            holder.btn_apply_sub_edit.setOnClickListener(this);
            holder.btn_cancel_sub_edit.setOnClickListener(this);

            if(posFocus == position){
                isGetView = false;
                //holder.tv_text_sub_edit.setVisibility(View.GONE);
                holder.lil_btn_sub_edit.setVisibility(View.VISIBLE);
            }else{
                //holder.tv_text_sub_edit.setVisibility(View.VISIBLE);
                holder.lil_btn_sub_edit.setVisibility(View.GONE);

                holder.et_text_sub_edit.setText(list.get(position));
                //holder.et_text_sub_edit.requestFocus();

            }

            holder.et_text_sub_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!isGetView){
                        posFocus = (int)v.getTag();
                        notifyDataSetChanged();
                    }else{
                        isGetView = false;
                    }
                }
            });

            holder.et_text_sub_edit.setTag(position);
            holder.btn_apply_sub_edit.setTag(position);
            holder.btn_cancel_sub_edit.setTag(position);
        }
        return convertView;
    }

    private class ViewHolder{
        TextView tv_text_sub_edit;
        LinearLayout lil_sub_edit;
        EditText et_text_sub_edit;
        LinearLayout lil_btn_sub_edit;
        Button btn_apply_sub_edit;
        Button btn_cancel_sub_edit;
    }

    public interface EditSubListener{
        void onClick(int pos, boolean isEdit);
        void onClickPlus();
    }

    @Override
    public void onClick(View v) {
        if(listener == null) return;
        int pos;
        switch(v.getId()){
            case R.id.btn_add_edit_plus:
                listener.onClickPlus();
                break;
            case R.id.btn_apply_sub_edit:
                pos = (int)v.getTag();
                listener.onClick(pos, true);
                break;
            case R.id.btn_cancel_sub_edit:
                pos = (int)v.getTag();
                listener.onClick(pos, false);
                break;
        }
    }
}

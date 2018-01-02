package com.jshstudy.contentsave.dto;

import android.database.Cursor;

import com.jshstudy.common.util.StringUtil;
import com.jshstudy.contentsave.vo.SaveContracts;

/**
 * Created by EMGRAM on 2017-11-07.
 */

public class TB1Data {
    // DTO(Data Transfer Object) : get, set, toString, equal(property).
    private int idx;
    private int num;
    private String msg;

    public TB1Data(int idx, int num, String msg){
        this.idx = idx;
        this.num = num;
        this.msg = msg;
    }

    public TB1Data(Cursor cursor){
        setData(cursor);
    }

    public void setIdx(int idx){
        this.idx = idx;
    }

    public int getIdx(){
        return idx;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getNum(){
        return num;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    private void setData(Cursor cursor){
        // idx : m
        setIdx(cursor.getInt(cursor.getColumnIndex(SaveContracts.DBtb1Entry.COL_IDX)));
        // num : o, opt
        if(!cursor.isNull(cursor.getColumnIndex(SaveContracts.DBtb1Entry.COL_NUM))){
            setNum(cursor.getInt(cursor.getColumnIndex(SaveContracts.DBtb1Entry.COL_NUM)));
        }
        // String : o, opt
        String msg = cursor.getString(cursor.getColumnIndex(SaveContracts.DBtb1Entry.COL_MESSAGE));
        if(!StringUtil.isEmpty(msg)){
            setMsg(msg);
        }
        
    }

    public String toString(){
        return this.getClass().getSimpleName() + "[idx : "+idx+ ", num : "+ num +", msg : "+ msg+"]";
    }
}

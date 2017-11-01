package com.jshstudy.common.network;

/**
 * Created by EMGRAM on 2017-10-30.
 */

public class NetException extends Exception{

    //... must change content...

    private int httpResCode = -1;
    private String msg;

    protected NetException(int resCode, String msg){
        this.msg = msg;
        httpResCode = resCode;
    }

    protected NetException(int resCode){
        httpResCode = resCode;
        msg = NetException.class.getSimpleName();
    }

    protected NetException(String msg){
        this.msg = msg;
    }

    protected NetException(Throwable cause, String msg){
        super(cause);
        this.msg = msg;
    }

    protected NetException(Throwable cause){
        super(cause);
        msg = NetException.class.getSimpleName();
    }

    public int getResCode(){
        return httpResCode;
    }

    public String getMsg(){
        return msg;
    }



}

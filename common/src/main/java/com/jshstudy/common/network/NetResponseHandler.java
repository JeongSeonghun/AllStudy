package com.jshstudy.common.network;

import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by EMGRAM on 2017-10-31.
 */

public abstract class NetResponseHandler {
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private String responseData;

    // send final result
    public abstract void onResult(EJNetwork network, NetException e);
    // trans responseData to new type
    public abstract void onEncodeResponseData(String strData);
    // give data
    public abstract Object getResponseData();
    // check response data, send result
    protected void responseData(EJNetwork network, HttpURLConnection conn, OutputStream os)throws IOException{

        int resCode = conn.getResponseCode();

        if(os instanceof ByteArrayOutputStream){
            responseData = ((ByteArrayOutputStream) os).toString("utf-8");
        }

        // https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C#2xx_.28.EC.84.B1.EA.B3.B5.29
        // 2xx : success
        if(resCode>=300||resCode<200){
            onSendFail(network, new NetException(resCode));
        }else{
            onEncodeResponseData(responseData);

            onSendSuccess(network);
        }

    }

    public String getResponseStringData(){
        return responseData;
    }

    protected void onSendSuccess(final EJNetwork network){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onResult(network, null);
            }
        });
    }
    protected void onSendFail(final EJNetwork network,final NetException e){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onResult(network, e);
            }
        });
    }
}

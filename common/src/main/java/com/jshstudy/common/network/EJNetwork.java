package com.jshstudy.common.network;

import android.os.Handler;
import android.os.Looper;

import com.jshstudy.common.dialog.JEProgressDialog;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class EJNetwork {

    private final int CONNET_TIMEOUT = 5000;
    private final int REAL_TIMEOUT = 5000;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private NetResponseHandler responseHandler;

    boolean isCancel = false;
    int BUFFER_MAX = 2*1024*8; //2k byte

    private NetProgressListener progressListener;

    public static class RequestMethod{
        public static final String POST = "POST";
        public static final String GET = "GET";
    }

    interface NetProgressListener{
        void onProgress(long readSize, long totalSize);
    }

    private void request(String mathod, NetParams params, NetResponseHandler responseHandler){
        this.responseHandler = responseHandler;

        Request request = new Request(mathod, null, params);
        request.start();
    }

    public void post(NetParams params, NetResponseHandler responseHandler){
        request(RequestMethod.POST, params, responseHandler);
    }

    public void get(NetParams params, NetResponseHandler responseHandler){
        request(RequestMethod.GET, params, responseHandler);
    }

    public void setProgressListener(NetProgressListener progressListener){
        this.progressListener = progressListener;
    }

    public void cancel(){
        isCancel = true;
    }

    private void onProgress(final long readSize, final long totalSize){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if(progressListener != null) progressListener.onProgress(readSize, totalSize);
            }
        });
    }

    private class Request extends Thread{

        String requestMethod;
        String strUrl;
        NetParams params;
        HttpURLConnection connection;
        OutputStream resStream = new ByteArrayOutputStream();

        public Request(String requestMethod, String url, NetParams params){
            this.requestMethod = requestMethod;
            this.strUrl = url;
            this.params = params;
        }

        public void cancel(){
            if(connection != null){
                connection.disconnect();
                connection = null;
            }
        }

        @Override
        public void run() {
            super.run();

            OutputStream out = null;

            try {
                URL url = new URL(strUrl);

                if(url.getProtocol().equals("https")){
                    connection = (HttpsURLConnection)url.openConnection();
                }else{
                    connection = (HttpURLConnection)url.openConnection();
                }

                connection.setRequestMethod(requestMethod);
                connection.setConnectTimeout(CONNET_TIMEOUT);
                connection.setReadTimeout(REAL_TIMEOUT);

                connection.setDoInput(true);

                // --- output ---
                out = connection.getOutputStream();
                if(requestMethod.equals(RequestMethod.POST)){
                    // post value input

                    RequestBuilder requestBuilder = new RequestBuilder();
                    requestBuilder.setPre(connection);
                    requestBuilder.write(out, null);
                }

                if(out != null){
                    out.flush();
                    out.close();
                }

                // --- input(receive) ---
                int statusCode = connection.getResponseCode();

                if(isCancel){
                    cancel();
                }

                // https://ko.wikipedia.org/wiki/HTTP_%EC%83%81%ED%83%9C_%EC%BD%94%EB%93%9C#2xx_.28.EC.84.B1.EA.B3.B5.29
                // 2xx : success
                if(statusCode < 300 && statusCode >= 200){
                    InputStream input = connection.getInputStream();
                    // totalSize, readSize를 통한 progress 표시 가능
                    long totalSize = connection.getContentLength();
                    long readSize = 0;

                    byte[] buffer = new byte[BUFFER_MAX];

                    while (readSize >= totalSize){
                        if(isCancel){
                            input.close();
                            cancel();
                        }

                        readSize += input.read(buffer);

                        onProgress(readSize, totalSize);

                        resStream.write(buffer);
                    }

                }

                responseHandler.responseData(EJNetwork.this,connection, resStream);

            } catch (IOException e) {//include MalformedURLException and responseHandler.responseData
                responseHandler.onSendFail(EJNetwork.this, new NetException(e));
            } finally {
                if(connection != null){
                    connection.disconnect();
                    connection = null;
                }
            }

        }
    }
}

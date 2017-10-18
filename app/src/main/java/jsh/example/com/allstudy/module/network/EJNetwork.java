package jsh.example.com.allstudy.module.network;

import android.os.Handler;
import android.os.Looper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import jsh.example.com.allstudy.module.OnRequestListener;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class EJNetwork {

    Handler mHandler = new Handler(Looper.getMainLooper());
    OnRequestListener listener;

    boolean isCancel = false;
    int BUFFER_MAX = 4*1024*8; //4k byte

    public static class RequestMethod{
        public static final String POST = "POST";
        public static final String GET = "GET";
    }

    private void request(String mathod, NetParams params, OnRequestListener listener){
        this.listener = listener;

        Request request = new Request(mathod, null, params);
        request.start();
    }

    public void post(NetParams params, OnRequestListener listener){
        request(RequestMethod.POST, params, listener);
    }

    public void get(NetParams params, OnRequestListener listener){
        request(RequestMethod.GET, params, listener);
    }

    public void cancel(){
        isCancel = true;
    }

    private void responseMain(){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                listener.OnResult();
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
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);

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

                if(statusCode == HttpURLConnection.HTTP_OK){
                    InputStream input = connection.getInputStream();
                    // totalSize, readSize를 통한 progress 표시 가능
                    long totalSize = connection.getContentLength();
                    int readSize = 0;

                    byte[] buffer = new byte[BUFFER_MAX];

                    while (readSize >= totalSize){
                        if(isCancel){
                            cancel();
                            input.close();
                        }

                        readSize += input.read(buffer);

                        resStream.write(buffer);
                    }


                    // 응답 전달 구현 필요
                    responseMain();
                }else{
                    // connect fail
                    responseMain();
                }

            } catch (IOException e) {//MalformedURLException
                e.printStackTrace();
            }


        }
    }
}

package jsh.example.com.allstudy.module.network;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import jsh.example.com.allstudy.data.NetParams;
import jsh.example.com.allstudy.module.OnRequestListener;

/**
 * Created by EMGRAM on 2017-09-01.
 */

public class EJNetwork {

    Handler mHandler = new Handler(Looper.getMainLooper());
    OnRequestListener listener;

    public static class RequestMethod{
        public static final String POST = "POST";
        public static final String GET = "GET";
    }

    private void request(String mathod, Object param, OnRequestListener listener){
        this.listener = listener;
    }

    public void post(){
        request(RequestMethod.POST, null, null);
    }

    public void get(){
        request(RequestMethod.GET, null, null);
    }

    private class Request extends Thread{

        String requestMethod;
        String strUrl;
        NetParams params;

        public Request(String requestMethod, String url, NetParams params){
            this.requestMethod = requestMethod;
            this.strUrl = url;
            this.params = params;
        }

        @Override
        public void run() {
            super.run();

            HttpURLConnection connection;

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

                if(requestMethod.equals(RequestMethod.POST)){
                    // post value input
                }

            } catch (IOException e) {//MalformedURLException
                e.printStackTrace();
            }


        }
    }
}

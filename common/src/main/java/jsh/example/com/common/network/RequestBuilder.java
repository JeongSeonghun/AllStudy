package jsh.example.com.common.network;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by EMGRAM on 2017-10-17.
 */

public class RequestBuilder {

    public final String CONTENT_TYPE = "Content-Type";
    public final String CONTENT_JSON = "application/json";
    public final String CHAR_SET = "charset";
    public final String CONTENT_LENGTH = "content-length";

    // post value 입력
    public void setPre(HttpURLConnection conn){
        conn.setRequestProperty(CONTENT_TYPE, CONTENT_JSON+";"+CHAR_SET+"=UTF-8");
        conn.setRequestProperty("Accept","application/json");
    }

    public void write(OutputStream os, JSONObject jsData){
        try {
            os.write(jsData.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

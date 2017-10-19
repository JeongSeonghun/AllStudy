package jsh.example.com.common.network;

import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;


/**
 * Created by EMGRAM on 2017-10-17.
 */

public class NetSettings {

    public void setSelfSigned(HttpsURLConnection connection) throws KeyManagementException, NoSuchAlgorithmException {

        AllStudySSLFactory sslFactory = new AllStudySSLFactory();

        connection.setSSLSocketFactory(sslFactory.createSSLContext().getSocketFactory());
    }

    public void ignoreSSL(HttpsURLConnection connection) throws KeyManagementException, NoSuchAlgorithmException {
        AllStudySSLFactory sslFactory = new AllStudySSLFactory();

        connection.setSSLSocketFactory(sslFactory.createIgnore().getSocketFactory());
    }

    public void setHadderProperty(HttpURLConnection connection, String key, String value){
        connection.setRequestProperty(key,value);
    }
}

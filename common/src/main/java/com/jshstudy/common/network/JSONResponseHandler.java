package com.jshstudy.common.network;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by EMGRAM on 2017-10-31.
 */

public abstract class JSONResponseHandler extends NetResponseHandler{
    private JSONObject jsonResponse;

    @Override
    public void onResult(EJNetwork network, NetException e) {
        onResult(this, e);
    }

    public abstract void onResult(JSONResponseHandler responseHandler, Exception e);

    @Override
    public void onEncodeResponseData(String strData) {
        try {
            jsonResponse = new JSONObject(strData);
        } catch (JSONException e) {
            jsonResponse = new JSONObject();
            onSendFail(null, new NetException(e, "encoding data"));
        }
    }

    @Override
    public JSONObject getResponseData() {
        return jsonResponse;
    }
}

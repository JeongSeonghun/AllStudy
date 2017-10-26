package com.jshstudy.sendstudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jshstudy.common.util.LogUtil;
import com.jshstudy.sendstudy.service.AutoSendService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.et_time);

        findViewById(R.id.btn_send_broad).setOnClickListener(this);
        findViewById(R.id.btn_unbind).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_broad:
                bindService();
                break;
            case R.id.btn_unbind:
                unBind();
                break;
        }
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.DLog("service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.DLog("service disconnected");
        }
    };

    private void bindService(){
        long time = 0;
        if(!editText.getText().toString().isEmpty()){
            time = Integer.valueOf(editText.getText().toString());
        }

        Intent autoSend = new Intent(this, AutoSendService.class);
        autoSend.putExtra("time", time);

        LogUtil.DLog("service bind start");
        bindService(autoSend, connection, Context.BIND_AUTO_CREATE);
    }

    private void unBind(){
        unbindService(connection);
    }
}

package com.jshstudy.communicatestudy.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.jshstudy.common.util.LogUtil;
import com.jshstudy.communicatestudy.R;
import com.jshstudy.communicatestudy.service.ComService;

public class ComServActivity extends AppCompatActivity implements View.OnClickListener{

    private ComService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_serv);

        initUi();
    }

    private void initUi(){
        findViewById(R.id.btn_bind_com_serv).setOnClickListener(this);
        findViewById(R.id.btn_unbind_com_serv).setOnClickListener(this);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.DLog("service onServiceConnected()");
            ComService.MyBinder binder = (ComService.MyBinder)service;
            binder.getService().setMsgRecieveListener(listener);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.DLog("service onServiceDisconnected()");
            isFlag = false;
        }
    };

    private ComService.onMsgReceiveListener listener = new ComService.onMsgReceiveListener() {
        @Override
        public void receiveMessage(String msg) {
            LogUtil.DLog("receiveMassage : "+msg);
        }
    };

    private void bind(){
        LogUtil.DLog("bind()");
        Intent intentServie = new Intent(this, ComService.class);

        bindService(intentServie, connection, Context.BIND_AUTO_CREATE);
    }

    private void unBind(){
        LogUtil.DLog("unBind()");
        unbindService(connection);
    }

    boolean isFlag = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bind_com_serv:
                if(!isFlag){
                    bind();
                    isFlag = true;
                }
                break;
            case R.id.btn_unbind_com_serv:
                if(isFlag){
                    unBind();
                }
                break;
        }
    }
}

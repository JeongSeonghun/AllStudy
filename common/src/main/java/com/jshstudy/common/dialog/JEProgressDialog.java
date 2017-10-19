package com.jshstudy.common.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jshstudy.common.R;

/**
 * Created by EMGRAM on 2017-10-18.
 */

public class JEProgressDialog extends ProgressDialog{

    private String msg;
    private String title;
    private boolean isRound;
    private boolean isBasic = false;
    private TextView tv_title;
    private TextView tv_msg;
    private ProgressBar progress;
    private final int defaultMax = 100;

    public JEProgressDialog(Context context) {
        super(context);
        msg = context.getString(R.string.default_progress_msg);
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public void setTypeRound(boolean isRound){
        this.isRound = isRound;
    }

    public void setProgressLevel(int progressLevel){
        if(isRound){
            return;
        }
        progress.setProgress(progressLevel);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private void init(){

        if(isRound){
            setContentView(R.layout.layout_jeprogress_dialog);

            tv_title = (TextView)findViewById(R.id.tv_je_progress_title);
            tv_msg = (TextView)findViewById(R.id.tv_je_progress_msg);
            progress = (ProgressBar)findViewById(R.id.progress_je);

        }else{
            setContentView(R.layout.layout_jeprogress_hdialog);

            tv_title = (TextView)findViewById(R.id.tv_je_hprogress_title);
            tv_msg = (TextView)findViewById(R.id.tv_je_hprogress_msg);
            progress = (ProgressBar)findViewById(R.id.hprogress_je);

            progress.setMax(defaultMax);
        }

        if(!title.isEmpty()){
            tv_title.setText(title);
        }else{
            tv_title.setVisibility(View.GONE);
        }

        tv_msg.setText(msg);
    }
}

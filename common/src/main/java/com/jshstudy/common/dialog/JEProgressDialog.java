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

import java.text.Format;
import java.util.Formattable;

/**
 * Created by EMGRAM on 2017-10-18.
 */

public class JEProgressDialog extends ProgressDialog{

    private String msg;
    private String title;
    private boolean isRound;
    private TextView tv_title;
    private TextView tv_msg;
    private ProgressBar progress;
    private final int defaultMax = 100;
    private int max=defaultMax;
    private String progressForm;

    public JEProgressDialog(Context context) {
        super(context);
        msg = context.getString(R.string.default_progress_msg);
    }

    public void setTypeRound(boolean isRound){
        this.isRound = isRound;
    }

    private void setProgressText(int progressLevel){
        if(progressForm.isEmpty()) progressForm = getContext().getString(R.string.default_hprogress_msg);

        tv_msg.setText(String.format(progressForm, progressLevel));

    }

    public void setMax(int max){
        this.max = max;
    }

    public void setProgressFormat(String progressForm){
        this.progressForm = progressForm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    public void setProgress(int value) {
        //super.setProgress(value);
        if(isRound){
            return;
        }
        progress.setProgress(value);

        setProgressText(value);
    }

    @Override
    public void setTitle(CharSequence title) {
        //super.setTitle(title);
        this.title = title.toString();
    }

    @Override
    public void setMessage(CharSequence message) {
        //super.setMessage(message);
        this.msg = message.toString();
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

            progress.setMax(max);
        }

        if(!title.isEmpty()){
            tv_title.setText(title);
        }else{
            tv_title.setVisibility(View.GONE);
        }

        tv_msg.setText(msg);
    }
}

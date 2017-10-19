package com.jshstudy.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;

/**
 * Created by EMGRAM on 2017-10-18.
 */

public class JEAlertDialog extends Dialog{

    private static JEAlertDialog alertDialog;

    private DialogListener listener;

    public JEAlertDialog(@NonNull Context context) {
        super(context);
    }

    public static JEAlertDialog instance(Context context){
        if(alertDialog == null){
            alertDialog = new JEAlertDialog(context);
        }
        return alertDialog;
    }

    public void setCustom(Context ctx, int layout_id){
        LayoutInflater inflater;

    }

    public void showDialog(String title){
        if(isShowing()){
            return;
        }

        setTitle(title);

        show();
    }

    public void showDialog(String title, DialogListener listener){
        this.listener = listener;

        if(isShowing()){
            listener.onResponse(DialogListener.DIAL_FAIL_SHOW);
            return;
        }

        setTitle(title);

        show();
    }

    @Override
    public void setTitle(@Nullable CharSequence title) {
        super.setTitle(title);
    }

    @Override
    public void setTitle(@StringRes int titleId) {
        super.setTitle(titleId);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void hide() {
        super.hide();
        if(listener != null){
            listener.onResponse(DialogListener.DIAL_CANCEL);
        }
    }

    public interface DialogListener{
        int DIAL_BACKPRESS = 1;
        int DIAL_OK = 2;
        int DIAL_CANCEL =3;
        int DIAL_FAIL_SHOW = 4;

        void onResponse(int response);
    }
}

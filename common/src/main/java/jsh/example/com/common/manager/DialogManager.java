package jsh.example.com.common.manager;

import android.app.Dialog;
import android.content.Context;

import java.util.HashMap;

import jsh.example.com.common.dialog.JEAlertDialog;
import jsh.example.com.common.dialog.JEProgressDialog;

/**
 * Created by EMGRAM on 2017-10-18.
 */

public class DialogManager {
    public final static int TYPE_ALERT = 1;
    public final static int TYPE_PROGRESS_ROUND = 2;
    public final static int TYPE_PROGRESS_HORIZONTAL = 3;

    private static DialogManager manager;

    private JEAlertDialog alertDialog;
    private JEProgressDialog progressDialog;
    private JEProgressDialog hProgressDialog;

    // manage single dialog

    private DialogManager(){}

    public static DialogManager getInstance(){
        if(manager == null){
            manager = new DialogManager();
        }

        return manager;
    }

    public JEAlertDialog getAlertDialog(Context ctx){

        if(alertDialog == null){
            alertDialog = new JEAlertDialog(ctx);
        }

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    private JEProgressDialog createProgessDialog(Context ctx, boolean isRound){
        JEProgressDialog temp;

        temp = new JEProgressDialog(ctx);
        temp.setTypeRound(isRound);
        temp.setCanceledOnTouchOutside(false);
        temp.setCancelable(false);

        return temp;
    }

    public JEProgressDialog getProgressDialog(Context ctx, boolean isRound){

        if(isRound){
            if(progressDialog == null) progressDialog = createProgessDialog(ctx, isRound);

            return progressDialog;
        }else{
            if(hProgressDialog == null) hProgressDialog = createProgessDialog(ctx, isRound);

            return hProgressDialog;
        }
    }

    public HashMap<Integer, Dialog> getShowingDialog(){
        HashMap<Integer, Dialog> showingMap = new HashMap<>();

        if(alertDialog != null && alertDialog.isShowing()) showingMap.put(TYPE_ALERT, alertDialog);
        if(progressDialog != null && progressDialog.isShowing()) showingMap.put(TYPE_PROGRESS_ROUND, progressDialog);
        if(hProgressDialog != null && hProgressDialog.isShowing()) showingMap.put(TYPE_PROGRESS_HORIZONTAL, hProgressDialog);

        return showingMap;
    }

    public void allHide(){
        if(alertDialog != null) alertDialog.hide();
        if(progressDialog != null) progressDialog.hide();
        if(hProgressDialog != null) hProgressDialog.hide();
    }
}

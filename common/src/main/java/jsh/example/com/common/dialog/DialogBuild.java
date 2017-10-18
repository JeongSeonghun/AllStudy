package jsh.example.com.common.dialog;

import android.content.Context;

/**
 * Created by EMGRAM on 2017-10-18.
 */

public class DialogBuild {
    public final static int TYPE_ALERT = 1;
    public final static int TYPE_PROGRESS_ROUND = 2;
    public final static int TYPE_PROGRESS_HORIZONTAL = 3;

    private static JEAlertDialog alertDialog;
    private static JEProgressDialog progressDialog;

    private boolean isSingle = true;

    public JEAlertDialog getAlertDialog(Context ctx){
        if(alertDialog == null || !isSingle){
            alertDialog = new JEAlertDialog(ctx);
        }

        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        return alertDialog;
    }

    public JEProgressDialog getProgressDialog(Context ctx, boolean isRound){

        if(progressDialog == null || !isSingle){
            progressDialog = new JEProgressDialog(ctx);
        }

        progressDialog = new JEProgressDialog(ctx);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);

        progressDialog.setTypeRound(isRound);

        return progressDialog;
    }

    public void setIsSingle(boolean isSingle){
        this.isSingle = isSingle;
    }
}

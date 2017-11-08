package com.jshstudy.allstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jshstudy.allstudy.R;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private Context context;

    private EditText et_login_id;
    private EditText et_login_pw;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;

        et_login_id = (EditText)findViewById(R.id.et_login_id);
        et_login_pw = (EditText)findViewById(R.id.et_login_pw);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(loginListener);

        setTestAccount();
    }

    private void goToMain(){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void login(String id, String pw){
        goToMain();
    }

    private void setTestAccount(){
        et_login_id.setText("pass");
        et_login_pw.setText("pass");
    }

    View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String id = et_login_id.getText().toString();
            String pw = et_login_pw.getText().toString();

            login(id, pw);
        }
    };
}

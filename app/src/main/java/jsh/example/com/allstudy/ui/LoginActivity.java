package jsh.example.com.allstudy.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import jsh.example.com.allstudy.R;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = this.getLocalClassName();

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
    }

    private void goToMain(){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

package jsh.example.com.allstudylauncher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NativeLauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native_launcher);
    }

    private void startNativeApp(String packageName){
        Intent appIntent = getPackageManager().getLaunchIntentForPackage(packageName);

        if(appIntent != null){
            appIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(appIntent);
        }
    }
}

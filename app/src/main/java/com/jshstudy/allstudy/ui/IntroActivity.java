package com.jshstudy.allstudy.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.jshstudy.allstudy.R;
import com.jshstudy.common.util.LogUtil;

public class IntroActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.DLog(TAG, "onCreate()");

        // 잠금화면 만들기 에도 사용??
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED // 잠금화면 위에 표시, full 스크린만 지원
//                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD    // 잠금화면 해제
//                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON    // 스크린 켜진 상태 유지, layout android:keepScreenOn="true"
//                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);  // 화면 켜기

        setContentView(R.layout.activity_intro);

        // root Activity check
        if(!isTaskRoot()) finish();

        context = this;

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
//            permissionCheck();
        }
        //goToLogin();
        IntroTimerStart();
    }

    // 요청 권한 구분용, 권한 필요한 부분에서만 부분적으로 사용 가능하게 함
    public final int MY_PERMISSIONS_REQUEST = 100;

    private void permissionCheck(){
        // 권한 확인
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(permissionCheck== PackageManager.PERMISSION_DENIED){ // 권한 없음

            // 권한 필요 이유 설명, FragmentCompat와 ActivityCompat 클래스에서 사용가능
            // 필요시 true, 다시 묻지 않기 클릭 시 항상 false
            // 처음 권한 요청시 false 이슈
            //shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }else{                                                  // 권한 있음

            // 권한 요청
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 이슈가 있었던 것 같은데....
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST);
            }

        }
        
    }

    // 권한 요청 결과 수신
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case MY_PERMISSIONS_REQUEST:
                if(grantResults.length>0){
                    if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        goToLogin();
                    }
                }
                break;
        }

    }

    private void goToLogin(){
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);

        finish();
    }

    private final long waitTime = 2*1000;
    private void IntroTimerStart(){
        Thread introTimer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(waitTime);

                    goToLogin();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        introTimer.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.DLog(TAG, "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.DLog(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.DLog(TAG, "onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.DLog(TAG, "onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.DLog(TAG, "onResume()");
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        LogUtil.DLog(TAG, "onUserLeaveHint()");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        LogUtil.DLog(TAG, "onBackPressed()");
    }
}

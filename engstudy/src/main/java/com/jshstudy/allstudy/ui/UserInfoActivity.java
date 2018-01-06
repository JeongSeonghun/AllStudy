package com.jshstudy.allstudy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.EngStudyDB;
import com.jshstudy.allstudy.data.SuccessData;
import com.jshstudy.allstudy.data.common.CommonData;

import java.util.Locale;

public class UserInfoActivity extends AppCompatActivity {

    private TextView tv_val_cnt_success_info;
    private EngStudyDB engStudyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initUi();
        initData();
    }

    private void initUi(){
        tv_val_cnt_success_info = (TextView)findViewById(R.id.tv_val_cnt_success_info);
    }

    private void initData(){
        engStudyDB = new EngStudyDB(this);

        setEngStudyData();
    }

    private void setEngStudyData(){
        SuccessData successData = engStudyDB.selectSuccessSum();

        tv_val_cnt_success_info.setText(String.format(Locale.KOREA, CommonData.Format.FORMAT_WORD_SUCCESS_RATE_TOTAL,
                successData.getRateSuccess100(), successData.getTotal()));
    }
}

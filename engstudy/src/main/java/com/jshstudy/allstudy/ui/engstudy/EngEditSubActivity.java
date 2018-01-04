package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.custom.adapter.EditSubAdapter;
import com.jshstudy.allstudy.data.CommonData;
import com.jshstudy.allstudy.data.engdata.EditSubData;
import com.jshstudy.common.util.LogUtil;

public class EngEditSubActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_sub_edit;
    private ListView lv_text_sub_edit;
    private Button btn_apply_sub_edit;
    private EditSubData subData;
    private EditSubAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_edit_sub);

        initUi();
        initData();
    }

    private void initUi(){
        tv_sub_edit = (TextView) findViewById(R.id.tv_sub_edit);
        lv_text_sub_edit = (ListView) findViewById(R.id.lv_text_sub_edit);
        btn_apply_sub_edit = (Button) findViewById(R.id.btn_apply_sub_edit);
    }

    private void initData(){
        Intent intentRec = getIntent();
        if(intentRec.hasExtra(CommonData.IntentData.KEY_SUB_DATA)){
            subData = intentRec.getParcelableExtra(CommonData.IntentData.KEY_SUB_DATA);

            tv_sub_edit.setText(subData.getTitle());

            setAdapter();
        }
    }

    private void setAdapter(){
        adapter = new EditSubAdapter(this);
        adapter.setList(subData.getList());
        adapter.setListener(listener);

        lv_text_sub_edit.setAdapter(adapter);
    }

    private EditSubAdapter.EditSubListener listener = new EditSubAdapter.EditSubListener() {
        @Override
        public void onClick(int pos, boolean isEdit) {
            LogUtil.DLog("sub onClick : "+pos+"/"+isEdit);
        }

        @Override
        public void onClickPlus() {
            LogUtil.DLog("sub onClickPlus");
        }
    };

    @Override
    public void finish() {

        Intent intentResult = new Intent();
        intentResult.putExtra(CommonData.IntentData.KEY_SUB_DATA, subData);
        setResult(RESULT_OK, intentResult);

        super.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_apply_sub_edit:
                LogUtil.DLog("sub apply");
                finish();
                break;
        }
    }
}

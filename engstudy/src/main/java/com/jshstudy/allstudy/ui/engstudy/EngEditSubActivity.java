package com.jshstudy.allstudy.ui.engstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.custom.adapter.EditSubAdapter;
import com.jshstudy.allstudy.data.common.CommonData;
import com.jshstudy.allstudy.data.common.EditSubData;
import com.jshstudy.common.util.LogUtil;

public class EngEditSubActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_text_sub_edit;
    private Button btn_save_sub_edit;
    private Spinner sp_title_sub_edit;
    private EditSubData subData;
    private EditSubAdapter adapter;
    private ArrayAdapter<String> titleAdapter;

    private int posTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eng_edit_sub);

        initUi();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sp_title_sub_edit.setSelection(posTitle);
//        titleAdapter.notifyDataSetChanged();
    }

    private void initUi(){
        lv_text_sub_edit = (ListView) findViewById(R.id.lv_text_sub_edit);
        btn_save_sub_edit = (Button) findViewById(R.id.btn_save_sub_edit);
        sp_title_sub_edit = (Spinner) findViewById(R.id.sp_title_sub_edit);

        btn_save_sub_edit.setOnClickListener(this);
    }

    private void initData(){
        Intent intentRec = getIntent();
        if(intentRec.hasExtra(CommonData.IntentData.KEY_SUB_DATA)){
            subData = intentRec.getParcelableExtra(CommonData.IntentData.KEY_SUB_DATA);

            posTitle = subData.getPostTitle();

            setAdapter();
        }
    }

    private void setAdapter(){
        adapter = new EditSubAdapter(this);
        adapter.setList(subData.getList());

        lv_text_sub_edit.setAdapter(adapter);

        titleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subData.getTitleList());

        sp_title_sub_edit.setAdapter(titleAdapter);
        sp_title_sub_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posTitle = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_title_sub_edit.setSelection(posTitle);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_sub_edit:
                subData.setList(adapter.getList());
                subData.setPostTitle(posTitle);
                LogUtil.dLog("sub save");
                Intent intentResult = new Intent();
                intentResult.putExtra(CommonData.IntentData.KEY_SUB_DATA, subData);
                setResult(RESULT_OK, intentResult);
                finish();
                break;
        }
    }
}

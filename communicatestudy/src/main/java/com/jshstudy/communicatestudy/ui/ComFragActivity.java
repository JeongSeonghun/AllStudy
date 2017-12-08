package com.jshstudy.communicatestudy.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jshstudy.communicatestudy.R;
import com.jshstudy.communicatestudy.ui.fragment.ComFrag1Fragment;
import com.jshstudy.communicatestudy.ui.fragment.TestFragment;

public class ComFragActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_frag);

        if (savedInstanceState == null) {//새로 생성하는 거라면

            //ComFrag1Fragment 생성한다.
            ComFrag1Fragment details = new ComFrag1Fragment();

            //현재 글목록에서 선택된 항목의 인덱스를 가져와 설정한다.
            //ComFragActivity 시작시 인덱스가 intent로 전달됨
            details.setArguments(getIntent().getExtras());

            //현재 Activity에 DetailsFragment 추가

            FragmentManager fm = getFragmentManager();

            FragmentTransaction ft = fm.beginTransaction();

            ft.add(R.id.frag_frl, new TestFragment());


            //기본 fragment와 supportv4 fragment차이 확인 필요...

        }

    }
}

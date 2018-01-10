package com.jshstudy.allstudy.ui.engstudy.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.data.common.CommonData;
import com.jshstudy.allstudy.ui.engstudy.EngChkWordActivity;
import com.jshstudy.allstudy.ui.engstudy.EngEditWordActivity;
import com.jshstudy.allstudy.ui.engstudy.SearchEngActivity;

public class EngWordFragment extends Fragment implements View.OnClickListener{
    private Button add_word_btn;
    private Button chk_word_btn;
    private Button search_word_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EngWordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eng_word, container, false);

        add_word_btn = (Button)view.findViewById(R.id.add_word_btn);
        chk_word_btn = (Button)view.findViewById(R.id.chk_word_btn);
        search_word_btn = (Button)view.findViewById(R.id.search_word_btn);

        add_word_btn.setOnClickListener(this);
        chk_word_btn.setOnClickListener(this);
        search_word_btn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void goToAct(Class act){
        Intent intentAct = new Intent(getContext(), act);
        startActivity(intentAct);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_word_btn:
                Intent intentAct = new Intent(getContext(), EngEditWordActivity.class);
                intentAct.putExtra(CommonData.IntentData.KEY_MOD, CommonData.IntentData.VALUE_MOD_ADD);
                startActivity(intentAct);
                break;
            case R.id.chk_word_btn:
                goToAct(EngChkWordActivity.class);
                break;
            case R.id.search_word_btn:
                goToAct(SearchEngActivity.class);
                break;
        }
    }
}

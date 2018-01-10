package com.jshstudy.allstudy.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jshstudy.allstudy.R;
import com.jshstudy.allstudy.ui.engstudy.Fragment.EngChapterFragment;
import com.jshstudy.allstudy.ui.engstudy.Fragment.EngWordFragment;

import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    // can occur exception when use commit after saving activity status
    // onSaveInstanceState(Bundle) is guaranteed to be called before…
    // honeycomb < api => onPause, honeycomb > api => onStop
    // https://www.androiddesignpatterns.com/2013/08/fragment-transaction-commit-state-loss.html

    private final int totalCnt = 2;
    private Button[] allMenuButtons = new Button[totalCnt];
    private Class[] allFragmentClass = new Class[]{
            EngChapterFragment.class, EngWordFragment.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        allMenuButtons[0] = (Button)findViewById(R.id.chap_menu_btn);
        allMenuButtons[1] = (Button)findViewById(R.id.word_menu_btn);

        for(Button button : allMenuButtons){
            button.setOnClickListener(this);
        }

        changeFragment(0);

    }

    public Fragment getCurrentFragment() {

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment frag : fragments) {
                if (!frag.isHidden()) {
                    return frag;
                }
            }
        }

        return null;
    }

    private void changeFragment(int tabIndex){

        Fragment currentFragment = getCurrentFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }

        Fragment fragment = getFragment(tabIndex);
        if (fragment == null) {
            fragment = createFragment(tabIndex);
            if (fragment == null) {
                return;
            }
            transaction.add(R.id.fragment_fl, fragment, allFragmentClass[tabIndex].getName());
        } else {
            transaction.show(fragment);
        }

        transaction.commitAllowingStateLoss();

    }

    private Fragment getFragment(int tabIndex) {
        return getSupportFragmentManager().findFragmentByTag(allFragmentClass[tabIndex].getName());
    }

    private Fragment createFragment(int tabIndex) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) allFragmentClass[tabIndex].newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.chap_menu_btn:
                changeFragment(0);
                break;
            case R.id.word_menu_btn:
                changeFragment(1);
                break;
            case R.id.my_menu_btn:
                break;
            case R.id.set_menu_btn:
                break;
        }
    }
}

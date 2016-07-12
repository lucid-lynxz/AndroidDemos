package org.lynxz.robolectricdemo;

import android.support.v4.app.FragmentTransaction;

import org.lynxz.robolectricdemo.fragment.FragmentDemo;

/**
 * Created by Lynxz on 2016/7/11.
 */
public class FragmentActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_third;
    }

    @Override
    protected void initData() {
    }


    @Override
    protected void initView() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rl_container, new FragmentDemo());
        fragmentTransaction.commit();

    }

    @Override
    protected void initListener() {

    }
}

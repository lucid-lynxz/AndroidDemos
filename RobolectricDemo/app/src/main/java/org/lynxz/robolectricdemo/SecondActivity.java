package org.lynxz.robolectricdemo;

import android.widget.EditText;

public class SecondActivity extends BaseActivity {


    private EditText mEdt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mEdt = findView(R.id.edt_second);
    }

    @Override
    protected void initListener() {

    }

    public void setTvText(String txt) {
        mEdt.setText(txt);
    }

}

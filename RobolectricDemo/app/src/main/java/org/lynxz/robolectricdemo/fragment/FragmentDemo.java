package org.lynxz.robolectricdemo.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.lynxz.robolectricdemo.R;

/**
 * Created by Lynxz on 2016/7/12.
 */
public class FragmentDemo extends BaseFragment {
    int count = 0;
    private TextView mTvTip;

    @Override
    protected int getLayoutId() {
        return R.layout.fragmetn_demo;
    }

    @Override
    protected void initData() {
        loadData();
    }

    @Override
    protected void initView() {
        mTvTip = findView(R.id.tv_frag);
        mTvTip.setText(count + "");
        Button btnToast = findView(R.id.btn_frag_toast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToat("fragment");
            }
        });

    }

    @Override
    protected void initEvent() {

    }

    public void loadData() {
        count++;
        if (mTvTip != null) {
            mTvTip.setText(count + "");
        }
    }
}

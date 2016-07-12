package org.lynxz.robolectricdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Lynxz on 2016/7/12.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        initView();
        initEvent();
    }

    public <T extends View> T findView(int id) {
        return (T) getView().findViewById(id);
    }

    public void showToat(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();
}

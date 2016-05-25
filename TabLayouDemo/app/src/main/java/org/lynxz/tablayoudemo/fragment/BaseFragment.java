package org.lynxz.tablayoudemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zxz on 2016/5/24.
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

    protected void initEvent() {
    }

    protected void initView() {
    }

    protected void initData() {
    }

    /**
     * 获取布局id
     */
    protected abstract int getLayoutId();

    protected <T extends View> T findView(int id) {
        if (getView() == null) {
            return null;
        }
        return (T) (getView().findViewById(id));
    }
}

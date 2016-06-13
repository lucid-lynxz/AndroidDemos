package org.lynxz.listdemos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.lynxz.listdemos.adapter.RvTimeLineAdapter;

import java.util.ArrayList;

public class TimeLineActivity extends BaseActivity {

    private RecyclerView mRv;
    private ArrayList<String> mData;
    private RvTimeLineAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_line);

        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("step " + i);
        }
    }

    private void initView() {
        mRv = findView(R.id.rv_time_line);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        mRv.setLayoutManager(layout);
        mAdapter = new RvTimeLineAdapter(this, mData);
        mRv.setAdapter(mAdapter);

        mAdapter.setCompletePos(3);
    }
}

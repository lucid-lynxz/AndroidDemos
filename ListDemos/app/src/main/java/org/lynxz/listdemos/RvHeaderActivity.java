package org.lynxz.listdemos;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import org.lynxz.listdemos.adapter.RvHeaderAdapter;

import java.util.ArrayList;

/**
 * 给RecycleView添加header
 */
public class RvHeaderActivity extends BaseActivity {

    private RecyclerView mRv;
    private ArrayList<String> mData;
    private RvHeaderAdapter mAdapter;
    private GridLayoutManager mLayoutMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("" + i);
        }
    }

    private void initView() {
        mRv = findView(R.id.rv_header);

        mLayoutMgr = new GridLayoutManager(this, 3);
        mRv.setLayoutManager(mLayoutMgr);

        mAdapter = new RvHeaderAdapter(this, mData);
        mRv.setAdapter(mAdapter);

        mLayoutMgr.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? mLayoutMgr.getSpanCount() : 1;
            }
        });


    }

}

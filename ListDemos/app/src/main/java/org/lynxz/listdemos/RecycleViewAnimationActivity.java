package org.lynxz.listdemos;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import org.lynxz.listdemos.adapter.RvAnimationAdapter;

import java.util.ArrayList;

public class RecycleViewAnimationActivity extends BaseActivity {

    private RecyclerView mRvAnimation;
    private ArrayList<String> mData;
    private RvAnimationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_animation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        initView();
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mData.add("pos" + i);
        }
    }

    private void addData(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "@insert";
        }
        int index = 0;
        if (mData.size() > 0) {
            index = mData.size() / 2;
        }
        mData.add(index, str);
        mAdapter.notifyItemInserted(index);
    }

    private void removeData(int position) {
        if (mData.size() == 0) {
            return;
        }

        if (position < 0) {
            position = mData.size() / 2;
        }
        mData.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private void initView() {
        mRvAnimation = findView(R.id.rv_animation);
        mRvAnimation.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RvAnimationAdapter(this, mData);
        mRvAnimation.setAdapter(mAdapter);

        findView(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(null);
            }
        });

        findViewById(R.id.tv_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(-1);
            }
        });
    }


}

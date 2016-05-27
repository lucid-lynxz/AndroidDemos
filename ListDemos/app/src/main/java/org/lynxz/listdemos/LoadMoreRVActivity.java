package org.lynxz.listdemos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.lynxz.listdemos.adapter.RvLoadMoreAdapter;

import java.util.ArrayList;

public class LoadMoreRVActivity extends BaseActivity {

    private SwipeRefreshLayout mSrl;
    private RecyclerView mRv;
    private ArrayList<String> mData;
    private RvLoadMoreAdapter mAdapter;
    private LinearLayoutManager mLayoutMgr;

    private boolean isLoading = false;
    private int lastVisibleItemPos;
    private int totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more_rv);

        initData();
        initView();

    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("pos: " + i);
        }
    }

    private void initView() {
        mSrl = findView(R.id.srl_refresh);
        mRv = findView(R.id.rv_load_more);

        mLayoutMgr = new LinearLayoutManager(this);
        mRv.setLayoutManager(mLayoutMgr);
        mAdapter = new RvLoadMoreAdapter(this, mData);
        mRv.setAdapter(mAdapter);

        // 这里通过判断最后滚动位置,确定是否已经发动到底部,按需显示laoding动画
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // 注意,这里getItemCount不是adapter中的总数据条数
                totalItemCount = mLayoutMgr.getItemCount();
                lastVisibleItemPos = mLayoutMgr.findLastVisibleItemPosition();

                if (!isLoading && totalItemCount <= (lastVisibleItemPos + 1)) {
                    loadMoreData();
                    isLoading = true;
                }
            }
        });


        // 使用系统控件来监听刷新,记得数据更新后要取消刷新动画
        mSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    }
                }, 1000);
            }
        });
    }

    private void loadMoreData() {
        mData.add(null);
        mAdapter.notifyItemInserted(mData.size() - 1);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 清除最后一个标志位
                mData.remove(mData.size() - 1);
                mAdapter.notifyItemRemoved(mData.size());

                // 获取新增数据
                int start = mData.size();
                int end = start + 10;
                for (int i = start; i < end; i++) {
                    mData.add("added pos: " + i);
                }

                // 更新列表
                mAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what == 0) {//stop refresh
                mSrl.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
            } else if (what == 1) {

            }
        }
    };

}

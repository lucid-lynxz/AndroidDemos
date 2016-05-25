package org.lynxz.listdemos;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.lynxz.listdemos.adapter.RvAdapter;
import org.lynxz.listdemos.widget.ScrollSpeedLinearLayoutManger;
import org.lynxz.listdemos.widget.WrapGridLayoutManager;

import java.util.ArrayList;

/**
 * recycleView的基本使用,横竖方向的使用
 * 跳转时缓慢运动效果
 */
public class RecyclerViewDemo extends BaseActivity {

    private RecyclerView mRv;
    private RvAdapter mAdapter;
    private ScrollSpeedLinearLayoutManger mLinearLayoutMgr;
    private ArrayList<Integer> mData = new ArrayList<>();
    private GridLayoutManager mGridLayoutMgr;
    private StaggeredGridLayoutManager mStaggedGridLayoutMgr;
    private Toolbar mTb;
    private WrapGridLayoutManager mWrapGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        initData();
        initView();
    }

    private void initView() {
        mTb = findView(R.id.tb_rv);
        setSupportActionBar(mTb);

        ActionBar supportActionBar = getSupportActionBar();
        /*
         * 设置左侧返回图标,默认是个左箭头←,可自定义图片
         * 使用该种方式时,通过 onOptionsItemSelected(MenuItem item) 来监听,
         * 其MenuItemId是 android.R.id.home
         * */
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        //        supportActionBar.setHomeAsUpIndicator(R.drawable.back);

        /*
         *返回按钮也可以使用导航图标替代
         * 但需要自己设置监听,代码如下
         * */
        //        mTb.setNavigationIcon(R.drawable.back);
        //        mTb.setNavigationOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                RecyclerViewDemo.this.finish();
        //            }
        //        });

        supportActionBar.setDisplayShowTitleEnabled(false);
        mTb.setTitle("RecycleView");
        //        mTb.setSubtitle("副标题");
        mTb.setLogo(R.drawable.grid_view);


        mRv = findView(R.id.rv);
        mAdapter = new RvAdapter(this, mData);
        mRv.setAdapter(mAdapter);

        // LinearLayout布局
        mLinearLayoutMgr = new ScrollSpeedLinearLayoutManger(this);
        mLinearLayoutMgr.setOrientation(LinearLayoutManager.HORIZONTAL);

        // 常规Grid布局
        mGridLayoutMgr = new GridLayoutManager(this, 3);

        // 自适应item高度的grid布局管理器
        mWrapGridLayoutManager = new WrapGridLayoutManager(this, 3);

        // 瀑布流
        mStaggedGridLayoutMgr = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);


        mRv.setLayoutManager(mLinearLayoutMgr);

        mAdapter.notifyDataSetChanged();
        mRv.smoothScrollToPosition(100);
        //        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
        //            @Override
        //            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        //                super.onScrollStateChanged(recyclerView, newState);
        //            }
        //
        //            @Override
        //            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        //                super.onScrolled(recyclerView, dx, dy);
        //                int lastVisibleItem = mLinearLayoutMgr.findLastVisibleItemPosition();
        //                int totalItemCount = mLinearLayoutMgr.getItemCount();
        //                if (lastVisibleItem >= totalItemCount - 1 && (dy > 0 || dx < 0)) {
        //                    loadMore();
        //                }
        //            }
        //        });

        TextView btnGoto = findView(R.id.btn_goto);
        final EditText edtIndex = findView(R.id.edt_index);


        btnGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 切换recycleView
                int pos = Integer.parseInt(edtIndex.getText().toString());
                if (pos >= mData.size()) {
                    pos = mData.size() - 1;
                } else if (pos < 0) {
                    pos = 0;
                }
                edtIndex.setText(pos + "");
                mRv.smoothScrollToPosition(pos);
            }
        });
    }

    private void loadMore() {
        mData.add(R.drawable.test);
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mData.add(R.drawable.iconfont_dl);
        mData.add(R.drawable.iconfont_hx);
        mData.add(R.drawable.iconfont_ls);
        mData.add(R.drawable.iconfont_math);
        mData.add(R.drawable.iconfont_sw);
        mData.add(R.drawable.iconfont_wl);
        mData.add(R.drawable.iconfont_yw);
        mData.add(R.drawable.iconfont_yy);
        mData.add(R.drawable.iconfont_zz);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.orientation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mLinearLayoutMgr != null) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    break;
                case R.id.it_horizontal:
                    mLinearLayoutMgr.setOrientation(LinearLayoutManager.HORIZONTAL);
                    mRv.setLayoutManager(mLinearLayoutMgr);
                    //                    showToast("横向排列布局");
                    break;
                case R.id.it_vertical:
                    mLinearLayoutMgr.setOrientation(LinearLayoutManager.VERTICAL);
                    mRv.setLayoutManager(mLinearLayoutMgr);
                    //                    showToast("竖向单列布局");
                    break;
                case R.id.it_grid:
                    mLinearLayoutMgr.setOrientation(LinearLayoutManager.VERTICAL);
                    mRv.setLayoutManager(mGridLayoutMgr);
                    //                    showToast("普通Grid布局");
                    break;
                case R.id.it_wrap_grid:
                    mRv.setLayoutManager(mWrapGridLayoutManager);
                    //                    showToast("自适应Grid布局");
                    break;
                default:
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

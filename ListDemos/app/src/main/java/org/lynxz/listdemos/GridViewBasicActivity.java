package org.lynxz.listdemos;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.lynxz.listdemos.adapter.GvAdapter;

import java.util.ArrayList;

/**
 * gridView的基本使用
 */
public class GridViewBasicActivity extends BaseActivity {

    private GridView mGv;
    private ArrayList<String> mData;
    private static final String TAG = "GridViewBasicActivity";
    private GvAdapter mGvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_basic);

        initData();


        Toolbar tb = findView(R.id.tb_gv);
        setSupportActionBar(tb);

        mGv = findView(R.id.gv_basic);
        mGv.setNumColumns(3);

        mGvAdapter = new GvAdapter(this, mData);
        // 固定item高度则使用
        // mGvAdapter = new GvAdapter(this, mData, true);
        mGv.setAdapter(mGvAdapter);

        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick 您点击了第 position: " + position + " 个item");
            }
        });
    }

    private void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mData.add("数据" + i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gv_in_lv_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.gv_fix_height:
                mGvAdapter.setFixItemHeight(true);
                break;
            case R.id.gv_basic:
                mGvAdapter.setFixItemHeight(false);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

package org.lynxz.listdemos;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.lynxz.listdemos.adapter.LvAdapter;

/**
 * 测试ListView的item中嵌套有GridView的情况
 * 得重写GridView的onMeasure方法,以便自适应高度
 * 可以修改FixGridView为普通GridView查看效果
 *
 * ListView的点击事件会被GridView给抢走,需要在lv的item布局顶层添加属性:
 * android:descendantFocusability="blocksDescendants"
 */
public class GridViewInListViewActivity extends BaseActivity {

    private ListView mLv;
    private LvAdapter mAdapter;
    private static final String TAG = "GVInLV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_in_list_view);

        mLv = findView(R.id.lv_grid);
        mAdapter = new LvAdapter(this);
        mLv.setAdapter(mAdapter);

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick position:" + position);
            }
        });
    }
}

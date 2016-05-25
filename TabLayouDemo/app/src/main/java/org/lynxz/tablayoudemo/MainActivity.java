package org.lynxz.tablayoudemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import org.lynxz.tablayoudemo.adapter.VpBaseAdapter;


/**
 * 使用tabLayout与ViewPager配合,常规属性设置请参考xml,主要有:
 * app:tabBackground  设置tab表填背景色,建议设置这个tabLayout的background颜色,不然tab页不够时,会有部分区域颜色不一致
 * app:tabIndicatorColor  标签下方提示条颜色,可以设置为transparent透明
 * app:tabMode="scrollable"  tab标签放置方式,固定模式下所有tab都挤在一个页面内
 * app:tabSelectedTextColor 选中时的文本颜色
 * app:tabTextColor tab文字普通颜色
 */
public class MainActivity extends BaseActivity {

    private TabLayout mTl;
    private TabLayout mTlBottom;
    private ViewPager mVp;
    private VpBaseAdapter mAdapter;
    private int[] mIconIds;
    private String[] mTabTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        mTl = findView(R.id.tl_vp);
        mVp = findView(R.id.vp_tab);
        mTlBottom = findView(R.id.tl_vp_bottom);

        mAdapter = new VpBaseAdapter(this, getSupportFragmentManager(), mTabTitles, mIconIds);
        mVp.setAdapter(mAdapter);

        mTl.setupWithViewPager(mVp);
        //所有tab上的字母都大写了,暂时不知道怎么处理
        mTl.getTabAt(0).setText("frag1@").setIcon(R.drawable.icon_x);

        //关联ViewPager,这样就不需要手动去创建tab,也不需要去处理点击监听了
        mTlBottom.setupWithViewPager(mVp);

        for (int i = 0; i < mTlBottom.getTabCount(); i++) {
            TabLayout.Tab tabAt = mTlBottom.getTabAt(i);
            if (tabAt != null) {
                //                tabAt.setIcon(mIconIds[i]);
                // tabAt.setText(mTabTitles[i]);
                tabAt.setCustomView(mAdapter.getTabView(i));
            }
        }

        // xml中设置tabLayout高度为wrap_content时,无法自动适应自定义布局大小

        // 选中指定的tab
        mTl.getTabAt(2).select();
    }

    private void initData() {
        mIconIds = new int[]{R.drawable.icon_z, R.drawable.icon_x, R.drawable.icon_z};
        mTabTitles = new String[]{"地理", "化学", "物理"};
    }
}

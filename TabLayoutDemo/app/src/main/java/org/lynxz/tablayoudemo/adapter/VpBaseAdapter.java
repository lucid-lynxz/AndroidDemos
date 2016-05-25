package org.lynxz.tablayoudemo.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.lynxz.tablayoudemo.R;
import org.lynxz.tablayoudemo.fragment.BaseFragment;
import org.lynxz.tablayoudemo.fragment.TabLayoutItemFragment;


/**
 * Created by zxz on 2016/5/24.
 */
public class VpBaseAdapter extends FragmentStatePagerAdapter {

    private int[] mIconIds;
    private String[] mTitles;
    private LayoutInflater mInflater;
    private static final String TAG = "VpBaseAdapter";

    public VpBaseAdapter(Context cxt, FragmentManager fm, String[] titles, int[] iconIds) {
        super(fm);
        mTitles = titles;
        mIconIds = iconIds;
        mInflater = LayoutInflater.from(cxt);
    }

    @Override
    public BaseFragment getItem(int position) {
        TabLayoutItemFragment baseFragment = new TabLayoutItemFragment();
        return baseFragment;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }


    /**
     * 用于TabLayout显示标题名称
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "frag" + position;
    }

    /**
     * 用于TabLayout显示自定义布局
     *
     * @param pos
     * @return
     */
    public View getTabView(int pos) {
        View tabView = mInflater.inflate(R.layout.view_tab_layout, null, false);
        TextView tvTab = (TextView) tabView.findViewById(R.id.tv_tab);
        ImageView ivTab = (ImageView) tabView.findViewById(R.id.iv_tab);
        //设置具体文本图案等
        tvTab.setText(mTitles[pos]);
        ivTab.setImageResource(mIconIds[pos]);
        //        Log.i(TAG, "getTabView tabViewHeight " + tabView.getMeasuredHeight());
        return tabView;
    }
}

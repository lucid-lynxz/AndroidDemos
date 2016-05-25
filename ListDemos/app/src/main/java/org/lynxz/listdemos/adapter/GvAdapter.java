package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.lynxz.listdemos.R;
import org.lynxz.listdemos.utils.MixedUtils;

import java.util.List;

/**
 * Created by zxz on 2016/5/19.
 * GridView的基本使用
 */
public class GvAdapter extends BaseAdapter {

    private static final String TAG = "GvAdapter";
    private List<String> mData;
    private final LayoutInflater mInflater;
    private boolean mFixitemHeight;//固定item高度,比如三行填满屏幕
    private int mScreenHeight;
    private int mScreenWidth;

    public GvAdapter(Context cxt, List<String> data) {
        this(cxt, data, false);
    }

    public GvAdapter(Context cxt, List<String> data, boolean fixHeight) {
        mData = data;
        mFixitemHeight = fixHeight;
        mInflater = LayoutInflater.from(cxt.getApplicationContext());
        mScreenWidth = MixedUtils.getScreenDimention(cxt)[0];
        mScreenHeight = MixedUtils.getScreenDimention(cxt)[1];
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 创建item视图,复用
        GvViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gv_basic, null, false);
            viewHolder = new GvViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GvViewHolder) convertView.getTag();
        }

        if (mFixitemHeight) {
            //固定item高度,这里使用3*3填满整个屏幕/gridView
            convertView.setLayoutParams(new AbsListView.LayoutParams(parent.getWidth() / 3, parent.getHeight() / 3));
            // convertView.setLayoutParams(new AbsListView.LayoutParams(parent.getWidth() / 3, mScreenHeight / 3));
        } else {
            convertView.setLayoutParams(new AbsListView.LayoutParams(parent.getWidth() / 3,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        viewHolder.tv.setText(mData.get(position));
        return convertView;
    }

    public void setFixItemHeight(boolean fix) {
        mFixitemHeight = fix;
        notifyDataSetChanged();
    }

    class GvViewHolder {
        ImageView iv;
        TextView tv;
        View item;

        public GvViewHolder(View itemView) {
            item = itemView;
            iv = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}

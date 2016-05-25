package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.lynxz.listdemos.R;


/**
 * Created by zxz on 2016/5/19.
 * ListView的基本适配器,本例用于处理item中包含GridView的情形
 */
public class LvAdapter extends BaseAdapter {

    private static final String TAG = "LvAdapter";
    private final LayoutInflater mInflater;

    public LvAdapter(Context cxt) {
        mInflater = LayoutInflater.from(cxt);
    }

    @Override
    public int getCount() {
        return 100;
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
        LvGridHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_lv_grid, null, false);
            holder = new LvGridHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (LvGridHolder) convertView.getTag();
        }

        holder.tv.setText("Lv Item: " + position);
        GvInListAdapter gvInListAdapter = new GvInListAdapter(mInflater);
        holder.gv.setAdapter(gvInListAdapter);
        holder.gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick position - " + position);
            }
        });
        return convertView;
    }


    class LvGridHolder {

        TextView tv;
        GridView gv;

        public LvGridHolder(View itemView) {
            tv = (TextView) itemView.findViewById(R.id.tv_lv);
            gv = (GridView) itemView.findViewById(R.id.gv_item);
        }
    }
}

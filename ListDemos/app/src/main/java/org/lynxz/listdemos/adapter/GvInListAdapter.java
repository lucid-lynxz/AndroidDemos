package org.lynxz.listdemos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.lynxz.listdemos.R;


/**
 * Created by zxz on 2016/5/19.
 */
public class GvInListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    public GvInListAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    @Override
    public int getCount() {
        return 4;
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
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_gv_in_lv, null, false);
        }
        return convertView;
    }
}

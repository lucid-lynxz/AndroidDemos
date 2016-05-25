package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.lynxz.listdemos.R;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private final ArrayList<Integer> data;
    private final LayoutInflater mInflater;
    private static final String TAG = "RvAdapter";

    public RvAdapter(Context cxt, ArrayList<Integer> picList) {
        this.data = picList;
        mInflater = LayoutInflater.from(cxt);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder viewHolder = new MyViewHolder(mInflater.inflate(R.layout.item_rv, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // 设置数据
        holder.iv.setBackgroundResource(data.get(position));
        holder.tv.setText(position + "");

        // 设置事件
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick pos:" + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_item);
            tv = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }
}



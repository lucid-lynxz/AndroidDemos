package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lynxz.listdemos.R;

import java.util.List;

/**
 * Created by zxz on 2016/5/23.
 * RecycleView item动画示例
 */
public class RvAnimationAdapter extends RecyclerView.Adapter<RvAnimationAdapter.RvAnimViewHolder> {

    private List<String> mData;
    private final LayoutInflater mInflater;

    public RvAnimationAdapter(Context cxt, List<String> data) {
        mData = data;
        mInflater = LayoutInflater.from(cxt);
    }

    @Override
    public RvAnimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_rv, parent, false);
        return new RvAnimViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RvAnimViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class RvAnimViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public RvAnimViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.iv_item).setVisibility(View.GONE);
            tv = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }
}

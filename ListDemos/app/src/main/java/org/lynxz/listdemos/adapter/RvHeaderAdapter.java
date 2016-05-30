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
 * Created by zxz on 2016/5/30.
 */
public class RvHeaderAdapter extends RecyclerView.Adapter<RvHeaderAdapter.HeaderHolder> {

    private final LayoutInflater mInflater;
    private final List<String> mData;

    public RvHeaderAdapter(Context cxt, List<String> data) {
        mInflater = LayoutInflater.from(cxt);
        mData = data;
    }

    @Override
    public HeaderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_rv, null);
        return new HeaderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeaderHolder holder, int position) {
        holder.tvIndex.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class HeaderHolder extends RecyclerView.ViewHolder {
        TextView tvIndex;

        public HeaderHolder(View itemView) {
            super(itemView);
            tvIndex = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }
}

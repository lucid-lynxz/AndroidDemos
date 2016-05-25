package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lynxz.listdemos.R;


/**
 * Created by zxz on 2016/5/5.
 */
public class RvGridAdapter extends RecyclerView.Adapter<RvGridAdapter.RvGridViewHolder> {

    private final LayoutInflater mInflater;

    public RvGridAdapter(Context cxt) {
        mInflater = LayoutInflater.from(cxt);
    }

    @Override
    public RvGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_rv_grid, parent, false);
        RvGridViewHolder holder = new RvGridViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RvGridViewHolder holder, int position) {
        holder.tv.setText("" + position);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RvGridViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public RvGridViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("rvGird", "click " + getAdapterPosition() + " - " + getLayoutPosition() + " - " + getPosition());
                }
            });
        }
    }
}



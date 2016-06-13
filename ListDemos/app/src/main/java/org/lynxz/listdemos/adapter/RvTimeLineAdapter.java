package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.lynxz.listdemos.R;

import java.util.List;

/**
 * Created by zxz on 2016/6/13.
 */
public class RvTimeLineAdapter extends RecyclerView.Adapter<RvTimeLineAdapter.TimeLineViewHolder> {

    private final List<String> mData;
    private final LayoutInflater mInflater;
    private int mCompletePos = -1;
    private final int mLineColorNormal;
    private final int mLineColorFinish;

    public RvTimeLineAdapter(Context cxt, List<String> data) {
        mData = data;
        mInflater = LayoutInflater.from(cxt);

        mLineColorNormal = cxt.getResources().getColor(R.color.time_line_normal);
        mLineColorFinish = cxt.getResources().getColor(R.color.time_line_finish);
    }

    public void setCompletePos(int completePos) {
        mCompletePos = completePos;
        notifyDataSetChanged();
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_rv_time_line, parent, false);
        return new TimeLineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        if (position <= mCompletePos) {
            holder.ivPoint.setImageResource(R.drawable.point_finish);
            holder.line.setBackgroundColor(mLineColorFinish);
        } else {
            holder.ivPoint.setImageResource(R.drawable.point_normal);
            holder.line.setBackgroundColor(mLineColorNormal);
        }

        if (position == mCompletePos) {
            holder.line.setBackgroundColor(mLineColorNormal);
        }
        if (position == mData.size() - 1) {
            holder.line.setVisibility(View.GONE);
        } else {
            holder.line.setVisibility(View.VISIBLE);
        }

        holder.tvContent.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class TimeLineViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPoint;
        View line;
        TextView tvContent;

        public TimeLineViewHolder(View itemView) {
            super(itemView);
            ivPoint = (ImageView) itemView.findViewById(R.id.iv_point);
            line = itemView.findViewById(R.id.view_line);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}

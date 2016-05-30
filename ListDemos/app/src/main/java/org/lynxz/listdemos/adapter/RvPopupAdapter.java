package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.graphics.Color;
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
public class RvPopupAdapter extends RecyclerView.Adapter<RvPopupAdapter.PopupHolder> {

    private final List<String> mData;
    private final LayoutInflater mInflater;
    private int mCurrentPosition = -1;

    public RvPopupAdapter(Context cxt, List<String> data) {
        mInflater = LayoutInflater.from(cxt);
        mData = data;
    }

    @Override
    public PopupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_popup_rv, null);
        return new PopupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PopupHolder holder, final int position) {
        holder.tvMenu.setText(mData.get(position));

        if (position == mCurrentPosition) {
            holder.tvMenu.setTextColor(Color.RED);
        } else {
            holder.tvMenu.setTextColor(Color.BLACK);
        }

        holder.tvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPosition(position);
            }
        });
    }

    private void selectPosition(int position) {
        mCurrentPosition = position;
        notifyDataSetChanged();

        // 使用自定义布局后,通过下面的方式来修改处理点击事件的话会FC
        //        notifyItemChanged(mCurrentPosition);
        //        mCurrentPosition = position;
        //        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class PopupHolder extends RecyclerView.ViewHolder {
        TextView tvMenu;

        public PopupHolder(View itemView) {
            super(itemView);
            tvMenu = (TextView) itemView.findViewById(R.id.tv_menu);
        }
    }
}

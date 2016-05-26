package org.lynxz.popupwindowdemo.adapter;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.lynxz.popupwindowdemo.R;

import java.util.List;

public class ReportCenterChooseCourseRVAdapter extends RecyclerView.Adapter<ReportCenterChooseCourseRVAdapter.RvReportCenterViewHolder> {

    private LayoutInflater mLayoutInflater;
    private List<String> mTitleList;
    private int mLastSelectPos = -1;
    private int mCurrentSelectPos = -1;
    private int mSelectColor;
    private int mNormalColor;

    public ReportCenterChooseCourseRVAdapter(FragmentActivity act, List<String> list) {
        mSelectColor = Color.BLUE;
        mNormalColor = Color.BLACK;
        mLayoutInflater = LayoutInflater.from(act);
        mTitleList = list;
    }

    @Override
    public RvReportCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_report_center_course, null, false);
        return new RvReportCenterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RvReportCenterViewHolder holder, final int position) {
        holder.tvCourse.setText(mTitleList.get(position));
        if (position == mCurrentSelectPos) {
            holder.tvCourse.setTextColor(mSelectColor);
        } else {
            holder.tvCourse.setTextColor(mNormalColor);
        }

        holder.rlView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class RvReportCenterViewHolder extends RecyclerView.ViewHolder {

        TextView tvCourse;
        View rlView;

        public RvReportCenterViewHolder(View itemView) {
            super(itemView);
            tvCourse = (TextView) itemView.findViewById(R.id.tv_course_name);
            rlView = itemView.findViewById(R.id.rl_item_rv_report);
        }
    }

    public interface OnCourseSelected {
        void onSelect(int position);
    }

    /**
     * 选中具体项
     *
     * @param position
     */
    public void selectItem(int position) {
        mCurrentSelectPos = position;
        notifyDataSetChanged();

        //        notifyItemChanged(position);
        /*
        * 这里如果使用 notifyItemChanged(position);只修改部分内容的话,多尝试几次会有FC
        * java.lang.IllegalArgumentException: Tmp detached view should be removed from RecyclerView before it can be recycled:
        *   ViewHolder{c7a0578 position=3 id=-1, oldPos=-1, pLpos:-1 update tmpDetached no parent}
        * */
    }
}

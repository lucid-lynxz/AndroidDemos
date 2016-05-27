package org.lynxz.listdemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.lynxz.listdemos.R;
import org.lynxz.listdemos.listener.OnLoadMoreListener;

import java.util.List;

/**
 * Created by zxz on 2016/5/27.
 * 下拉刷新使用 系统控件 swipeRefreshLayout
 * <p/>
 * 上拉加载更多 通过判断当前可见item位置,在数据集末尾添加标志数据后,返回带有进度条的footItem
 * 在加载完成后,清除数据集标志数据即可
 */
public class RvLoadMoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_LOADING = 1;

    private final List<String> mData;
    private final LayoutInflater mInflater;

    private OnLoadMoreListener mOnLoadMoreListener;

    public RvLoadMoreAdapter(Context cxt, List<String> data) {
        mInflater = LayoutInflater.from(cxt);
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = mInflater.inflate(R.layout.item_rv_load_more, null, false);
            return new NormalHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.item_loading, null, false);
            return new LoadMoreHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {
            ((NormalHolder) holder).tvName.setText(mData.get(position));
        } else if (holder instanceof LoadMoreHolder) {
            ((LoadMoreHolder) holder).pbMore.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * 通过判断最后一个数据的特殊性来区分是否是加载更多item
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (TextUtils.isEmpty(mData.get(position))) {
            return TYPE_LOADING;
        } else {
            return TYPE_NORMAL;
        }
    }

    class LoadMoreHolder extends RecyclerView.ViewHolder {
        ProgressBar pbMore;

        public LoadMoreHolder(View itemView) {
            super(itemView);
            pbMore = (ProgressBar) itemView.findViewById(R.id.pb_load_more);
        }
    }

    class NormalHolder extends RecyclerView.ViewHolder {
        TextView tvName;

        public NormalHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}

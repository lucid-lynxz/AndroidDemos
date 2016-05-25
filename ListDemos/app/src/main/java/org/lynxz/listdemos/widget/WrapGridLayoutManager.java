package org.lynxz.listdemos.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * RecycleView 高度自适应item高度
 */
public class WrapGridLayoutManager extends GridLayoutManager {
    private static final String TAG = "WrapGridLayoutManager";

    public WrapGridLayoutManager(Context context, int spanCount) {
        //默认方向是VERTICAL
        super(context, spanCount);
    }

    public WrapGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        int height = 0;
        int childCount = getItemCount();
        for (int i = 0; i < childCount; i++) {
            View child = recycler.getViewForPosition(i);
            measureChild(child, widthSpec, heightSpec);
            if (i % getSpanCount() == 0) {
                int measuredHeight = child.getMeasuredHeight() + getDecoratedBottom(child);
                height += measuredHeight;
            }
        }
        Log.i(TAG, "onMeasure height: " + height);
        setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);

    }

}
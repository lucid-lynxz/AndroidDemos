package org.lynxz.popupwindowdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.lynxz.popupwindowdemo.adapter.ReportCenterChooseCourseRVAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 调用popupWindow显示选项列表菜单
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick(R.id.btn_popup)
    void getPopupWindwow() {
        if (mPopupWindow == null) {
            //创建自定义布局
            View popView = getLayoutInflater().inflate(R.layout.popup_report_center_choose_page, null,
                    false);

            //设置内容，主要是布局管理器要根据item内容高度进行适配
            RecyclerView mRvCourse = (RecyclerView) popView.findViewById(R.id.rv_course);
            mRvCourse.setLayoutManager(new MyGridLayoutManager(this, 5));
            ReportCenterChooseCourseRVAdapter mRvAdapter = new ReportCenterChooseCourseRVAdapter(this, mTitleList);
            mRvCourse.setAdapter(mRvAdapter);

            // 创建PopupWindow实例
            mPopupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            // 设置动画效果
            //        mPopupWindow.setAnimationStyle(R.style.reportChoosePageAnimation);
            // 点击其他地方消失
            mPopupWindow.setOutsideTouchable(true);
            //得设置背景图案,不然在android6.0以下机子中,点击内容外部区域无法关闭
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.shape_bg);
            Drawable drawable = new BitmapDrawable(getResources(), bmp);
            mPopupWindow.setBackgroundDrawable(drawable);
            popView.findViewById(R.id.rl_close_choose_course_page).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                        mPopupWindow = null;
                    }
                }
            });
        }

        // 显示在基准控件下方,用于代表下拉列表
        mPopupWindow.showAsDropDown(tvTitle);
    }


    /**
     * 根据item高度wrap
     */
    class MyGridLayoutManager extends GridLayoutManager {
        public MyGridLayoutManager(Context context, int spanCount) {
            //默认方向是VERTICAL
            super(context, spanCount);
        }

        public MyGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        @Override
        public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
            int height = 0;
            int childCount = getItemCount();
            for (int i = 0; i < childCount; i++) {
                View child = recycler.getViewForPosition(i);
                measureChild(child, widthSpec, heightSpec);
                ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
                if (i % getSpanCount() == 0) {
                    int measuredHeight = child.getMeasuredHeight() + getDecoratedBottom(child) + lp.topMargin + lp.bottomMargin;
                    height += measuredHeight;
                }
            }
            setMeasuredDimension(View.MeasureSpec.getSize(widthSpec), height);
        }
    }

    private ArrayList<String> mTitleList;

    private void initData() {
        mTitleList = new ArrayList<>();
        mTitleList.add("所有学科");
        mTitleList.add("语文");
        mTitleList.add("数学");
        mTitleList.add("英语");
        mTitleList.add("思想品德");
        mTitleList.add("历史");
        mTitleList.add("地理");
        mTitleList.add("物理");
        mTitleList.add("化学");
        mTitleList.add("生物");

    }
}

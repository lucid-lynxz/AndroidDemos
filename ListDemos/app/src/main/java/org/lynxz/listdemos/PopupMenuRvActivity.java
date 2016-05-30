package org.lynxz.listdemos;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import org.lynxz.listdemos.adapter.RvPopupAdapter;
import org.lynxz.listdemos.widget.FixGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class PopupMenuRvActivity extends BaseActivity {

    private List<String> mTitleList;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu_rv);

        initView();
    }

    private void initView() {
        final TextView tvPopup = findView(R.id.tv_popup);
        tvPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPopupWindow().showAsDropDown(tvPopup);
            }
        });

    }

    public PopupWindow getPopupWindow() {
        if (mPopupWindow == null) {
            initPopupWindow();
        }
        return mPopupWindow;
    }

    public void initPopupWindow() {
        /**
         * 创建PopupWindow
         */
        View popView = getLayoutInflater().inflate(R.layout.popup_rv_demo, null, false);

        RecyclerView mRvCourse = (RecyclerView) popView.findViewById(R.id.rv_menu);

        //使用默认的布局适配器的话,列表高度会match
        //        mRvCourse.setLayoutManager(new GridLayoutManager(this, 5));
        // 自定义管理器,重新计算item总高度
        mRvCourse.setLayoutManager(new FixGridLayoutManager(this, 5));

        RvPopupAdapter mRvAdapter = new RvPopupAdapter(this, getTitleList());
        mRvCourse.setAdapter(mRvAdapter);

        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
        mPopupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果
        //        mPopupWindow.setAnimationStyle(R.style.reportChoosePageAnimation);
        // 点击其他地方消失
        mPopupWindow.setOutsideTouchable(true);
    }

    public List<String> getTitleList() {
        mTitleList = new ArrayList<>();
        mTitleList.add("语文");
        mTitleList.add("数学");
        mTitleList.add("英语");
        mTitleList.add("物理");
        mTitleList.add("化学");
        mTitleList.add("生物");
        mTitleList.add("地理");
        mTitleList.add("历史");
        return mTitleList;
    }
}

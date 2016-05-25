# TabLayout
通常跟ViewPager配套使用,可用于替代顶部导航条,或者底部页面切换功能条:

![tabLayout顶部底图使用示例](http://upload-images.jianshu.io/upload_images/947286-07f20470004cc83e.gif?imageMogr2/auto-orient/strip)

### xml使用
```xml
<android.support.design.widget.TabLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:background="@color/div_line"
    android:textAlignment="center"
    
    app:tabIndicatorColor="@color/theme_color"  // 底部滑动条颜色
    app:tabIndicatorHeight="1dp"  //滑动条高度,一般使用默认值即可,不需要另外设定
    app:tabMode="scrollable"  // tab模式,fixed为固定模式,所有tab显示在一屏中,使用于少量标签
    app:tabSelectedTextColor="@color/theme_color"  // tab选中时,文本颜色
    app:tabTextColor="@color/dialog_msg_color"/>  // tab常规文本颜色
```

### 设定ViewPager适配器,提供TabLayout标题和自定义布局
```java
public class VpBaseAdapter extends FragmentStatePagerAdapter {
    ......

    /**
     * 用于TabLayout显示标题名称
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return "frag" + position;
    }

    /**
     * 用于TabLayout显示自定义布局
     */
    public View getTabView(int pos) {
        View tabView = mInflater.inflate(R.layout.customer_layout, null, false);
        TextView tvTab = (TextView) tabView.findViewById(R.id.tv_tab);
        ImageView ivTab = (ImageView) tabView.findViewById(R.id.iv_tab);
        //设置具体文本图案等
        tvTab.setText(mTitles[pos]);
        ivTab.setImageResource(mIconIds[pos]);
        return tabView;
    }
}
```

### 使TabLayout与ViewPager关联
```java
mTl.setupWithViewPager(mVp);
```

### 个性化tab标签
```java
// 设置标题文字和图标
mTl.getTabAt(0).setText("frag1@").setIcon(R.drawable.icon_x);

// 自定义布局
for (int i = 0; i < mTlBottom.getTabCount(); i++) {
    TabLayout.Tab tabAt = mTlBottom.getTabAt(i);
    if (tabAt != null) {
        tabAt.setCustomView(mAdapter.getTabView(i));
    }
}
```

### 其他方法
```java
// 获取指定位置的tab
Tab tab =  mTl.getTabAt(position);

// 选中指定的tab
tab.select();
```
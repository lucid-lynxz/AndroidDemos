# 列表控件的使用方法

[TOC]

### RecycleView
1. 设置布局管理器
```java
// LinearLayout布局
LinearLayoutManager mLinearLayoutMgr = new LinearLayoutManager(this);
mLinearLayoutMgr.setOrientation(LinearLayoutManager.HORIZONTAL);

// Grid布局
LinearLayoutManager mGridLayoutMgr = new GridLayoutManager(this, 3);

// 瀑布流布局
StaggeredGridLayoutManager mStaggedGridLayoutMgr = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL);

mRv.setLayoutManager(mLinearLayoutMgr);
```

2. 设置适配器
适配器需要继承 RecyclerView.Adapter<? extends RecyclerView.ViewHolder>
需要重写几个方法:
 * onCreateViewHolder(ViewGroup parent, int viewType) 根据viewType创建具体的行布局
 * onBindViewHolder(PairViewHolder holder, int position) 绑定数据到具体布局视图上,并设置点击事件等操作
 * getItemCount() 共有多少个item
 * getItemViewType(int position) 创建ViewHolder时的依据,只有一种布局时,不需关心

#### 跳转动效
最终还是使用smoothScrollToPosition(int position),重写布局管理器即可:
```java
// 控制滑动速度的LinearLayoutManager
public class ScrollSpeedLinearLayoutManger extends LinearLayoutManager {
    private float MILLISECONDS_PER_INCH = 0.3f;
    private Context context;

    public ScrollSpeedLinearLayoutManger(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    public PointF computeScrollVectorForPosition(int targetPosition) {
                        return ScrollSpeedLinearLayoutManger.this
                                .computeScrollVectorForPosition(targetPosition);
                    }

                    //返回滑动一个pixel需要多少毫秒
                    @Override
                    protected float calculateSpeedPerPixel
                    (DisplayMetrics displayMetrics) {
                        return MILLISECONDS_PER_INCH / displayMetrics.density;
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public void setSpeedSlow() {
        //自己在这里用density去乘，希望不同分辨率设备上滑动速度相同
        //0.3f是自己估摸的一个值，可以根据不同需求自己修改
        MILLISECONDS_PER_INCH = context.getResources().getDisplayMetrics().density * 0.3f;
    }

    public void setSpeedFast() {
        MILLISECONDS_PER_INCH = context.getResources().getDisplayMetrics().density * 0.03f;
    }
}
```


### GridView
```java
GridView mGv = findViewById(R.id.gv_basic);
mGv.setNumColumns(3);//设置列数,也可在xml中设定

// 适配器同样与ListView类似,继承自BaseAdapter
GvAdapter gvAdapter = new GvAdapter(this, mData, true);
mGv.setAdapter(gvAdapter);

//添加点击监听
mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick 您点击了第 position: " + position + " 个item");
    }
});
```

#### 1. 固定item高度
之前有个需求是在一个页面显示9个item,填满屏幕:
```java
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    ......
    //固定item高度,这里使用3*3填满整个屏幕/gridView
    convertView.setLayoutParams(new AbsListView.LayoutParams(parent.getWidth() / 3, parent.getHeight() / 3));
    // 恢复默认的话设置高度为wrap_content就可以了
    // convertView.setLayoutParams(new AbsListView.LayoutParams(parent.getWidth() / 3,ViewGroup.LayoutParams.WRAP_CONTENT));
    ......
    return convertView;
}
```

#### 2. ListView中嵌套GridView
* gridView只显示一行的问题
```java
//重写GridView的onMeasure()方法
public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
            MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
}
```

* 同时设置ListView和GridView的点击事件,只有GridView的有响应
需要在ListView的item布局顶层屏蔽子元素焦点事件
```xml
<LinearLayout 
    ......
    android:descendantFocusability="blocksDescendants">

    <org.lynxz.androiddemos.widget.FixGridView
    ....../>
</LinearLayout>
```
这样listView的item点击事件就能被触发了,同时若是点击到GridView的item会触发GridView的事件;
同理,若是GridView的item中有抢焦点的控件导致其点击事件失效,也同样在其item布局顶层添加该属性;

## 其他

### ToolBar
考虑到兼容性,隐藏页面自带的,使用supportV7包中的ToolBar替代:
1. 隐藏默认的ActionBar
    * 方式1: 使用 Theme.AppCompat.Light.NoActionBar 主题;
    * 方式2: 在Activity中隐藏 `getActionBar().hide()`;

2. 在布局中添加ToolBar
```xml
    <android.support.v7.widget.Toolbar
        android:id="@+id/tb"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
```

3. 设置其为ActionBar
```java
Toolbar tb = findView(R.id.tb);
setSupportActionBar(tb);
```

4. 创建菜单item
在 `res/menu/` 目录下创建 `base_menu.xml`
注意,页面继承AppCompatActivity时,showAsAction属性需要使用app命名空间;
另外,title属性是必须的,同时group标签可不写;
```menu
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
      xmlns:app="http://schemas.android.com/apk/res-auto">

    <group>
        <item
            android:id="@+id/it_search"
            android:icon="@drawable/search"
            android:title="add"
            app:showAsAction="always"/>
        <item
            android:id="@+id/g1_2"
            android:icon="@drawable/iconfont_wl"
            android:title="add2"/>
        <item
            android:id="@+id/g1_3"
            android:icon="@drawable/iconfont_ls"
            android:title="add3"/>
    </group>
</menu>
```

5. 重写onCreateOptionsMenu()创建菜单
```java
@Override
public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater menuInflater = getMenuInflater();
    menuInflater.inflate(R.menu.base_menu, menu);
    return super.onCreateOptionsMenu(menu);
}
```

6. 设置菜单项点击事件
```java
@Override
public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {
        case R.id.it_search:
            break;
    }
    return super.onOptionsItemSelected(item);
}
```

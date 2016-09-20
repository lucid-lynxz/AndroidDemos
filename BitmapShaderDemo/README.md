
# CircleImageView
直接继承ImageView,方便使用,使用BitmapShader来绘制圆形图案

```java
BitmapShader(@NonNull Bitmap bitmap, TileMode tileX, TileMode tileY) {
```
BitmapShader 构造方法三个参数:
1. Bitmap bitmap : 需要绘制的图像Bitmap对象
2. TileMode : 平铺模式,分别当图像不足以填满控件时,其x,y方向上延展方式,共有三种:
CLAMP -  拉伸, REPEAT - 重复 , MIRROR - 镜像

# 关键代码
### 1. 获取Bitmap对象(`ImageView` 通过 `src` 属性设置图片)
由于 `ImageView` 通过 `getDrawable()` 获取到的是 `Drawable` 对象,需要转换成 `Bitmap` 使用,
```java
private Bitmap drawableToBitmap(Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }
    return createBitmap(drawable);
}

/**
* 根据Drawable对象创建Bitmap对象
*/
@NonNull
private Bitmap createBitmap(Drawable drawable) {
    int w = drawable.getIntrinsicWidth();
    int h = drawable.getIntrinsicHeight();
    Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, w, h);
    drawable.draw(canvas);
    return bitmap;
}
```

### 2. 图片边框默认为方形,根据控件宽高情况设置最终圆形半径
```java
@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mViewSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
    mRadius = mViewSize / 2;
    setMeasuredDimension(mViewSize, mViewSize);
}
```

### 3. 根据图像与空间之间的大小关系,缩放图像
```java
private void initShader() {
    Bitmap bmp = drawableToBitmap(getDrawable());
    mBitmapShader = new BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

    // 设置放大或者缩小,避免如图片过小,但view太大,导致拉伸变形等问题
    float scale = 1;
    int bSize = Math.min(bmp.getWidth(), bmp.getHeight());
    scale = mViewSize * 1.0f / bSize;
    mMatrix.setScale(scale, scale);
    mBitmapShader.setLocalMatrix(mMatrix);

    mBitmapPaint.setShader(mBitmapShader);
}
```

### 4. 设置圆形边框线
```java
private void initBorderPaint() {
    mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    mBorderPaint.setStyle(Paint.Style.STROKE);//STROKE模式
    mBorderPaint.setStrokeWidth(mStrokeWidth);//设置边框线宽度
    mBorderPaint.setColor(Color.RED);//设置边框线颜色
}

@Override
protected void onDraw(Canvas canvas) {
    ......
    // 这里要减掉边框宽度的一半,不然可能有些边框显示不出来,被控件区域给限制了
    //画笔刚好在圆形图片边缘上,绘制的时候,往边缘两侧各绘制一半
    canvas.drawCircle(mRadius, mRadius, mRadius - mStrokeWidth / 2, mBorderPaint);
}
``

# 使用
```xml
<org.lynxz.bitmapshaderdemo.widget.CircleImageView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/circle_src"/>
```

```java
// 与glide等配合,当作普通imageView即可
ImageView ivOnline = (ImageView) findViewById(R.id.civ_online);
Glide.with(this)
        .load(picUrl)
        .placeholder(R.drawable.loading)
        .error(R.drawable.error)
        .into(ivOnline);
```

# 参考
[BitmapShader 实战 实现圆形、圆角图片](http://blog.csdn.net/lmj623565791/article/details/41967509)  
[制作圆形图片，你会以下几种？](http://www.jianshu.com/p/305b71249250)

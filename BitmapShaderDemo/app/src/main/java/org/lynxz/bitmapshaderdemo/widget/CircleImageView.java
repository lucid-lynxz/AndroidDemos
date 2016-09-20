package org.lynxz.bitmapshaderdemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Lynxz on 2016/9/20 .
 */
public class CircleImageView extends ImageView {
    private Matrix mMatrix;//用于缩小放大
    private BitmapShader mBitmapShader;//使用图像为绘制图形着色
    private int mViewSize;//view宽度
    private int mRadius;//圆角的半径
    private Paint mBitmapPaint;
    private Paint mBorderPaint;
    private int mStrokeWidth = 5;

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
        initBorderPaint();
    }


    /**
     * 初始化参数
     */
    private void initData() {
        mMatrix = new Matrix();
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    /**
     * 初始化BitmapShader
     */
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

    /**
     * 设置边框画笔
     */
    private void initBorderPaint() {
        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mStrokeWidth);
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        mBorderPaint.setColor(Color.RED);

        // 设置阴影,这个不太好用啊
        //        setLayerType(LAYER_TYPE_SOFTWARE, mBorderPaint);
        //        mBorderPaint.setShadowLayer(12, 3, 3, Color.BLUE);
    }

    /**
     * drawable转bitmap
     */
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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewSize = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = mViewSize / 2;
        setMeasuredDimension(mViewSize, mViewSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        initShader();
        canvas.drawCircle(mRadius, mRadius, mRadius, mBitmapPaint);
        canvas.drawCircle(mRadius, mRadius, mRadius - mStrokeWidth / 2, mBorderPaint);

        //        RectF rect = new RectF(0, 0, 2 * mRadius, 2 * mRadius);
        //        canvas.drawArc(rect, 0, 180, false, mBorderPaint);
    }
}

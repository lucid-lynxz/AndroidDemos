package org.lynxz.listdemos.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class MixedUtils {

    /**
     * dpToPx simple
     *
     * @param ctx
     * @param val
     * @return
     */
    public static int dpToPx(Context ctx, float val) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                val, ctx.getResources().getDisplayMetrics());
    }

    /**
     * 获取屏幕大小
     *
     * @param context
     * @return
     */
    @TargetApi(13)
    public static int[] getScreenDimention(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int[] cordinary = new int[2];
        if (Build.VERSION.SDK_INT >= 13) {
            Point size = new Point();
            display.getSize(size);
            cordinary[0] = size.x;
            cordinary[1] = size.y;
        } else {
            cordinary[0] = display.getWidth();
            cordinary[1] = display.getHeight();
        }
        return cordinary;
    }
}
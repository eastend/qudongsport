package com.sport.qudong.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class DensityUtils {

    private static float density;
    private static float scaledDensity;
    private static int mScreenWidth;
    private static int mScreenHeight;

    public static void init(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        density = outMetrics.density;
        scaledDensity = outMetrics.scaledDensity;
        mScreenHeight = outMetrics.heightPixels;
        mScreenWidth = outMetrics.widthPixels;
    }

    public static int getDensity() {
        return (int) density;
    }

    public static float dp2px(int dp) {
        return (density * dp + 0.5f);
    }

    public static int px2dp(float px) {
        return (int) ((px - 0.5f) / density);
    }


    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / scaledDensity + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * scaledDensity + 0.5f);
    }

    public static int getmScreenWidth() {
        return mScreenWidth;
    }

    public static int getmScreenHeight() {
        return mScreenHeight;
    }


    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context activity) {

        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 38;//默认为38，貌似大部分是这样的
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }


    /**
     * 获取标题栏高度
     */

    public static int getTitleBar(Activity activity) {

        int contentTop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //statusBarHeight是上面所求的状态栏的高度
        return contentTop - getStatusBarHeight(activity);
    }


    /**
     * 获取标题栏+标题栏高度
     */

    public static int getTopHeight(Activity activity) {

        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

}

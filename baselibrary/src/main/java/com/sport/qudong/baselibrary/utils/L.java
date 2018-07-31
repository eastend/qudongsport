package com.sport.qudong.baselibrary.utils;

import android.util.Log;

/**
 * Log统一管理类
 * <p>
 * Created by wood on 2017/4/10.
 */
public class L {

    private L() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static String TAG = "L";


    public static void init(String tag, boolean debug) {
        isDebug = debug;
        TAG = tag;
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (isDebug) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }
}

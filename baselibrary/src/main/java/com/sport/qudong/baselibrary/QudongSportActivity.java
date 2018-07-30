package com.sport.qudong.baselibrary;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sport.qudong.baselibrary.utils.StatusBarUtil;


/**
 * activity 基类
 */

public abstract class QudongSportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    Mode currentMode = Mode.DARK;
    int type;

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int resId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }


    /**
     * 定义状态栏显示模式
     */
    public enum Mode {
        DARK, LIGHT
    }


    /**
     * 设置状态栏
     *
     * @param mode
     * @param color
     */
    public void setStatusBar(Mode mode, int color) {
        setStatusBarTextMode(mode);
        if (type == 0 && color == Color.WHITE) {
            //设置亮色模式失败
            color = 0xffcccccc;
        }
        StatusBarUtil.setStatusBarColor(this, color);
    }

    /**
     * 设置状态栏显示模式
     *
     * @param mode
     */
    public void setStatusBarTextMode(Mode mode) {
        if (mode == null) {
            return;
        }

        if (currentMode == mode) {
            return;
        }

        if (mode == Mode.LIGHT) {
            type = StatusBarUtil.StatusBarLightMode(this);
        } else {
            StatusBarUtil.StatusBarDarkMode(this, type);
        }
    }


    /**
     * 设置导航栏颜色
     *
     * @param color
     */
    protected final void setNavicationBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(color);
        }
    }

    protected final void hideNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    protected final void showNavigationBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
        decorView.setSystemUiVisibility(uiOptions);
    }


}

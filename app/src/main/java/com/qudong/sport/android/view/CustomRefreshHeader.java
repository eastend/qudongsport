package com.qudong.sport.android.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.qudong.sport.android.R;
import com.qudong.sport.android.view.loadingWideget.LVCircularRing;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.sport.qudong.baselibrary.utils.DensityUtils;

/**
 * Created by wood on 2018/2/8.
 */

public class CustomRefreshHeader extends LinearLayout implements RefreshHeader {

    private int bgColor = Color.WHITE;
    private int ringColor;
    private int ringBgColor;
    private LVCircularRing ring;
    private boolean isAnim = false;

    public CustomRefreshHeader(Context context) {
        super(context);
        this.ringColor = ContextCompat.getColor(getContext(), R.color.colorAccent);
        this.ringBgColor = ContextCompat.getColor(getContext(), R.color.bg_color);
        init();
    }

    public CustomRefreshHeader(Context context, int ringColor, int ringBgColor) {
        super(context);
        this.ringColor = ringColor;
        this.ringBgColor = ringBgColor;
        init();
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        this.bgColor = color;
    }

    private void init() {
        setBackgroundColor(bgColor);
        int size = (int) DensityUtils.dp2px(25);
        int padding = (int) DensityUtils.dp2px(25);
        setGravity(Gravity.CENTER);
        setPadding(0, padding, 0, padding);
        ring = new LVCircularRing(getContext(), ringColor, ringBgColor);
        addView(ring, new LinearLayoutCompat.LayoutParams(size, size));
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {
        if (!isAnim) {
            isAnim = true;
            ring.startAnim();
        }
    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {
    }


    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int extendHeight) {
        kernel.requestDrawBackgoundForHeader(bgColor);
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        return 10;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

    }
}

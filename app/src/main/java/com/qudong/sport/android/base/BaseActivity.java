package com.qudong.sport.android.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sport.qudong.baselibrary.QudongSportActivity;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class BaseActivity extends QudongSportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar(Mode.LIGHT, Color.WHITE);
    }
}

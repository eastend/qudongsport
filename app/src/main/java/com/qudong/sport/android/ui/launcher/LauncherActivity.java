package com.qudong.sport.android.ui.launcher;

import android.os.Handler;
import android.os.Bundle;

import com.qudong.sport.android.R;
import com.qudong.sport.android.base.BaseActivity;
import com.qudong.sport.android.ui.main.HomeActivity;

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HomeActivity.intentToHome(LauncherActivity.this);
                finish();
            }
        }, 1000);
    }
}

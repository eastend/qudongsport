package com.qudong.sport.android.ui.main.page.sport.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudong.sport.android.R;
import com.qudong.sport.android.common.ViewpagerFragment;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class SignInFragment extends ViewpagerFragment {

    private View contentView;

    public static SignInFragment getNewInstance() {
        return new SignInFragment();
    }

    @Override
    protected String getTitle() {
        return "签到";
    }

    @Override
    protected void firstLoad() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fg_sport_sign_in, null);
        return contentView;
    }
}

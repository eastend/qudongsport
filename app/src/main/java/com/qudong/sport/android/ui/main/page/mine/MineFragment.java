package com.qudong.sport.android.ui.main.page.mine;

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

public class MineFragment extends ViewpagerFragment {

    private View contentView;

    public static final MineFragment getNewInstance(){
        return new MineFragment();
    }

    @Override
    protected void firstLoad() {

    }

    @Override
    protected String getTitle() {
        return "我";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fg_mine,null);
        return contentView;
    }
}

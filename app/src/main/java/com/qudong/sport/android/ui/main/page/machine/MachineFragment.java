package com.qudong.sport.android.ui.main.page.machine;

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

public class MachineFragment extends ViewpagerFragment {


    private View contentView;

    public static final MachineFragment getNewInstance(){
        return new MachineFragment();
    }

    @Override
    protected void firstLoad() {

    }


    @Override
    protected String getTitle() {
        return "矿机";
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView=inflater.inflate(R.layout.fg_machine,null);
        return contentView;
    }
}

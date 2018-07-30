package com.qudong.sport.android.ui.main.page.sport.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qudong.sport.android.R;
import com.qudong.sport.android.common.ViewpagerFragment;
import com.qudong.sport.android.ui.main.page.sport.SportShareAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class ShareFragment extends ViewpagerFragment {

    private View contentView;

    @BindView(R.id.rv_share)
    RecyclerView recyclerView;

    SportShareAdapter sportShareAdapter;

    public static ShareFragment getNewInstance() {
        return new ShareFragment();
    }

    @Override
    protected String getTitle() {
        return "分享";

    }

    @Override
    protected void firstLoad() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fg_sport_share, null);
        ButterKnife.bind(this, contentView);
        sportShareAdapter = new SportShareAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sportShareAdapter);

        return contentView;
    }
}

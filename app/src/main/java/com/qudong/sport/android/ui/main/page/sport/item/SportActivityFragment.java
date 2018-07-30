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
import com.qudong.sport.android.ui.main.page.sport.SportEventAdapter;
import com.qudong.sport.android.ui.main.page.sport.TeachVideoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class SportActivityFragment extends ViewpagerFragment {

    public static SportActivityFragment getNewInstance() {
        return new SportActivityFragment();
    }

    private View contentView;

    @BindView(R.id.rv_tech_video)
    RecyclerView rvTeachVideo;

    @BindView(R.id.rv_event)
    RecyclerView rvEvent;

    TeachVideoAdapter teachVideoAdapter;

    SportEventAdapter sportEventAdapter;

    @Override
    protected String getTitle() {
        return "活动";
    }

    @Override
    protected void firstLoad() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fg_sport_activity, null);
        ButterKnife.bind(this, contentView);
        teachVideoAdapter = new TeachVideoAdapter();
        rvTeachVideo.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvTeachVideo.setAdapter(teachVideoAdapter);

        sportEventAdapter = new SportEventAdapter();
        rvEvent.setLayoutManager(new LinearLayoutManager(getContext()));
        rvEvent.setHasFixedSize(true);
        rvEvent.setNestedScrollingEnabled(false);
        rvEvent.setAdapter(sportEventAdapter);

        return contentView;
    }


}

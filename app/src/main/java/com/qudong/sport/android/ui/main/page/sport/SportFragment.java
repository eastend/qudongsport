package com.qudong.sport.android.ui.main.page.sport;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidkun.xtablayout.XTabLayout;
import com.qudong.sport.android.R;
import com.qudong.sport.android.common.CommonFgAdapter;
import com.qudong.sport.android.common.ViewpagerFragment;
import com.qudong.sport.android.ui.main.page.sport.item.ShareFragment;
import com.qudong.sport.android.ui.main.page.sport.item.SignInFragment;
import com.qudong.sport.android.ui.main.page.sport.item.SportActivityFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class SportFragment extends ViewpagerFragment {


    public static final SportFragment getNewInstance() {
        return new SportFragment();
    }


    private View contentView;

    @BindView(R.id.tl_sport)
    XTabLayout tabLayout;

    @BindView(R.id.vp_sport)
    ViewPager viewPager;

    CommonFgAdapter<ViewpagerFragment> adapter;

    @Override
    protected void firstLoad() {
        adapter = new CommonFgAdapter(getChildFragmentManager(), getFragments());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new ViewPagerOnTabSelectedListener(viewPager));
    }

    @Override
    protected String getTitle() {
        return "运动";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fg_sport, null);
        ButterKnife.bind(this, contentView);
        return contentView;
    }


    private ArrayList<ViewpagerFragment> getFragments() {
        ArrayList<ViewpagerFragment> list = new ArrayList<>();

        SportActivityFragment sportActivityFragment = (SportActivityFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(viewPager.getId(), 0));

        if (sportActivityFragment == null) {
            sportActivityFragment = SportActivityFragment.getNewInstance();
        }


        ShareFragment shareFragment = (ShareFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(viewPager.getId(), 1));

        if (shareFragment == null) {
            shareFragment = ShareFragment.getNewInstance();
        }


        SignInFragment signInFragment = (SignInFragment) getChildFragmentManager().findFragmentByTag(makeFragmentName(viewPager.getId(), 2));

        if (signInFragment == null) {
            signInFragment = SignInFragment.getNewInstance();
        }

        list.add(sportActivityFragment);
        list.add(shareFragment);
        list.add(signInFragment);

        return list;

    }

    public static class ViewPagerOnTabSelectedListener implements XTabLayout.OnTabSelectedListener {

        private ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override
        public void onTabSelected(XTabLayout.Tab tab) {

            viewPager.setCurrentItem(tab.getPosition(), false);
        }

        @Override
        public void onTabUnselected(XTabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(XTabLayout.Tab tab) {

        }
    }
}

package com.qudong.sport.android.ui.main;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.qudong.sport.android.R;
import com.qudong.sport.android.base.BaseActivity;
import com.qudong.sport.android.common.CommonFgAdapter;
import com.qudong.sport.android.common.ViewpagerFragment;
import com.qudong.sport.android.ui.main.page.machine.MachineFragment;
import com.qudong.sport.android.ui.main.page.mine.MineFragment;
import com.qudong.sport.android.ui.main.page.sport.SportFragment;
import com.qudong.sport.android.view.CustomViewpager;

import java.util.ArrayList;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.rg_main_tab)
    RadioGroup tabGroup;

    @BindView(R.id.vp_main)
    CustomViewpager viewpager;

    @BindColor(R.color.colorAccent)
    int colorAccent;

    CommonFgAdapter<ViewpagerFragment> fgAdapter;

    SportFragment sportFragment;

    MachineFragment machineFragment;

    MineFragment mineFragment;


    public static final void intentToHome(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        fgAdapter = new CommonFgAdapter<>(getSupportFragmentManager(), getFragments());
        viewpager.setAdapter(fgAdapter);
        viewpager.setOffscreenPageLimit(3);
        viewpager.setPagingEnabled(false);
        tabGroup.setOnCheckedChangeListener(this);
        tabGroup.check(R.id.rb_main_tab_sport);
    }

    private ArrayList<ViewpagerFragment> getFragments() {

        ArrayList<ViewpagerFragment> list = new ArrayList<>();

        sportFragment = (SportFragment) getSupportFragmentManager().findFragmentByTag(ViewpagerFragment.makeFragmentName(viewpager.getId(), 0));
        machineFragment = (MachineFragment) getSupportFragmentManager().findFragmentByTag(ViewpagerFragment.makeFragmentName(viewpager.getId(), 1));
        mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(ViewpagerFragment.makeFragmentName(viewpager.getId(), 2));

        if (sportFragment == null) {
            sportFragment = SportFragment.getNewInstance();
        }

        if (machineFragment == null) {
            machineFragment = MachineFragment.getNewInstance();
        }


        if (mineFragment == null) {
            mineFragment = MineFragment.getNewInstance();
        }

        list.add(sportFragment);

        list.add(machineFragment);

        list.add(mineFragment);

        return list;

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_main_tab_sport:
                setStatusBar(Mode.LIGHT, Color.WHITE);
                viewpager.setCurrentItem(0, false);
                break;
            case R.id.rb_main_tab_machine:
                setStatusBar(Mode.LIGHT, colorAccent);
                viewpager.setCurrentItem(1, false);
                break;
            case R.id.rb_main_tab_mine:
                setStatusBar(Mode.LIGHT, colorAccent);
                viewpager.setCurrentItem(2, false);
                break;
        }
    }
}

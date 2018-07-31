package com.qudong.sport.android.ui.main.page.sport.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qudong.sport.android.R;
import com.qudong.sport.android.base.ViewpagerFragment;
import com.qudong.sport.android.bean.CalendarDate;
import com.qudong.sport.android.ui.main.page.sport.SignInMonthAdapter;
import com.sport.qudong.baselibrary.utils.DateUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public class SignInFragment extends ViewpagerFragment {

    private View contentView;

    @BindView(R.id.tv_date)
    TextView tvDate;

    @BindView(R.id.rv_date)
    RecyclerView rvDate;

    SignInMonthAdapter signInMonthAdapter;

    public static SignInFragment getNewInstance() {
        return new SignInFragment();
    }

    @Override
    protected String getTitle() {
        return "签到";
    }

    @Override
    protected void firstLoad() {
        signInMonthAdapter.setList(getMonthData(DateUtil.getYear(), DateUtil.getMonth()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fg_sport_sign_in, null);
        ButterKnife.bind(this, contentView);
        rvDate.setLayoutManager(new GridLayoutManager(getContext(), 7));
        signInMonthAdapter = new SignInMonthAdapter();
        rvDate.setAdapter(signInMonthAdapter);
        return contentView;
    }

    private ArrayList<CalendarDate> getMonthData(int year, int month) {
        ArrayList<CalendarDate> list = new ArrayList<>();

        int dayOfMonth = DateUtil.getMonthDays(year, month);

        int firstDayOfWeek = DateUtil.getMonthFirstDayOfWeek(year, month);

        int frontIllegalCount = firstDayOfWeek - 1;

        for (int i = 0; i < frontIllegalCount; i++) {
            list.add(new CalendarDate());
        }

        for (int i = 1; i <= dayOfMonth; i++) {
            list.add(new CalendarDate(year, month, i));
        }
        return list;

    }
}

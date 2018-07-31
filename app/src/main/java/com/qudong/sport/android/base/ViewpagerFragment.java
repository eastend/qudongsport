package com.qudong.sport.android.base;

/**
 * Created by Administrator on 2018/7/29 0029.
 */

public abstract class ViewpagerFragment extends LazyFragment {

    public static final String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }

    protected abstract  String getTitle();

}

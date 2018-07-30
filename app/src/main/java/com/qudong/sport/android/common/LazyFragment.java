package com.qudong.sport.android.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.qudong.sport.android.base.BaseFragment;
import com.sport.qudong.baselibrary.utils.L;

/**
 * Created by wood on 2018/1/18.
 */

public abstract class LazyFragment extends BaseFragment {
    private final String TAG = "LazyFragment";


    private boolean isVisible = false;
    private boolean isPrepared = false;
    private boolean isLazy = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        L.d(TAG, getClass().getSimpleName() + "---->onCreate");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        L.d(TAG, getClass().getSimpleName() + "---->onViewCreated");
        isPrepared = true;
        tryLazy();
    }


    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        L.d(TAG, getClass().getSimpleName() + "---->setUserVisibleHint==" + isVisibleToUser);
        isVisible = isVisibleToUser;
        tryLazy();

        if (isPrepared && isLazy) {
            if (isVisible) {
//                L.d(TAG, getClass().getSimpleName() + "---->onVise");
                onVise();
            } else {
//                L.d(TAG, getClass().getSimpleName() + "---->onInVise");
                onInVise();
            }
        }

    }


    private void tryLazy() {
        if ((isVisible && isPrepared) && (!isLazy)) {
            isLazy = true;
            L.d(TAG, getClass().getSimpleName() + "---->firstLoad");
            firstLoad();
        }
    }


    protected abstract void firstLoad();

    protected void onVise() {
    }

    protected void onInVise() {
    }


}

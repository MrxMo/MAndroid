package com.mrmo.mandroidlib.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mrmo.mandroidlib.app.MPresenter;
import com.mrmo.mandroidlib.app.activity.MPresenterActivity;

import java.io.Serializable;

/**
 支持Presenter的Fragment
 * Created by moguangjian on 2017/1/20.
 */
public abstract class MPresenterFragment<T extends MPresenter> extends MFragment implements Serializable {

    private T presenter;
    private static final String TAG = MPresenterFragment.class.getCanonicalName();

    public void setMAnalyticsPageName(String mAnalyticsPageName) {
        this.mAnalyticsPageName = mAnalyticsPageName;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().onCreate(savedInstanceState);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getPresenter() != null) {
            getPresenter().onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }


    public
    @Nullable
    T getPresenter() {
        if (presenter == null) {
            presenter = MPresenterActivity.getPresenter(this);
            if (presenter != null) {
                presenter.setView(this);
            }

        }
        return presenter;
    }
}

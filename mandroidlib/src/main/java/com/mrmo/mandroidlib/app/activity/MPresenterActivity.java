package com.mrmo.mandroidlib.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mrmo.mandroidlib.app.MPresenter;
import com.mrmo.mandroidlib.common.reflex.MReflex;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 支持Presenter的Activity
 * Created by moguangjian on 2017/3/8.
 */

public abstract class MPresenterActivity<T extends MPresenter> extends MActivity{

    private static final String TAG = MPresenterActivity.class.getCanonicalName();

    private T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getPresenter() != null) {
            getPresenter().onCreate(savedInstanceState);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPresenter() != null) {
            getPresenter().onResume();
        }
//        GAppUtil.setAnalyticsPageName(this);
//        JAnalyticsManagerUtil.onResume(getMContext(), mAnalyticsPageName);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (getPresenter() != null) {
            getPresenter().onPause();
        }
//        GAppUtil.setAnalyticsPageName(this);
//        JAnalyticsManagerUtil.onResume(getMContext(), mAnalyticsPageName);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getPresenter() != null) {
            getPresenter().onDestroy();
        }
    }

    public
    @Nullable
    T getPresenter() {
        if (presenter == null) {
            presenter = getPresenter(this);
            if (presenter != null) {
                presenter.setView(this);
            }

        }
        return presenter;
    }

    public static <T> T getPresenter(Object object) {

        Type type = object.getClass().getGenericSuperclass();
        if (type == null) {
            Log.e(TAG, "没有父类!");
            return null;
        }

        if (!(type instanceof ParameterizedType)) {
            Log.w(TAG, "没有泛型类!");
            return null;
        }

        ParameterizedType parameterizedType = (ParameterizedType) type;
        Class clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];

        if (clazz ==null) {
            Log.e(TAG, "instance Presenter failure !");
            return null;
        }
        return MReflex.instanceClass(clazz);
    }
}

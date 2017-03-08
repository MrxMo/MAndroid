package com.mrmo.mandroidlib.app;

import android.os.Bundle;

/**
 * Created by moguangjian on 2017/1/20.
 */

public abstract class MPresenter<T> {
    public T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view  = view;
    }

    /**
     * 对应Activity / Fragment生命周期
     * @param savedInstanceState
     */
    public abstract void onCreate(Bundle savedInstanceState);

    /**
     * 对应Fragemnt onActivityCreated
     * @param savedInstanceState
     */
    public void onActivityCreated(Bundle savedInstanceState) {}

    public void onResume() {}

    public void onPause() {}

    public void onStop() {}

    public void onDestroy() {}
}

package com.mrmo.mandroidlib.app.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

/**
 * Created by moguangjian on 15/10/18 00:39.
 */
public abstract class MFragment extends Fragment {

    private static final String TAG = MFragment.class.getSimpleName();
    private Context context;
    private View mRootView;
    private boolean isSaveCache;

    /**页面名称。该参数不能混淆**/
    public String mAnalyticsPageName = null;

    public static final int MIN_CLICK_DELAY_TIME = 1500;
    private long lastClickTime = 0;

    protected abstract int getLayoutId();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            isSaveCache = true;
        }

        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，否则会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent == null) {
            parent.removeView(mRootView);
        }

        return mRootView;
    }

    public View getMRootView() {
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isSaveCache()) {
            return;
        }

    }
    public <T extends View> T findViewById(int resId) {
        return (T) getView().findViewById(resId);
    }

    public Context getMContext() {
        return context;
    }

    protected boolean isSaveCache() {
        return isSaveCache;
    }

    /**
     * 防止重复点击
     *
     * @return
     */
    protected boolean isMultiClick() {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            return false;
        }
        return true;
    }

    public void setMAnalyticsPageName(String mAnalyticsPageName) {
        this.mAnalyticsPageName = mAnalyticsPageName;
    }
}

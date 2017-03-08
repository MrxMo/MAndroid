package com.mrmo.mandroidlib.app.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;
import com.jude.swipbackhelper.SwipeListener;
import com.mrmo.mandroidlib.app.MManagerAble;

import java.util.Calendar;

/**
 * Created by moguangjian on 2017/3/8.
 */

public abstract class MActivity extends AppCompatActivity {

    private static final int MIN_CLICK_DELAY_TIME = 1000;

    private Context context;
    private long lastClickTime = 0;

    /**页面名称。该参数不能混淆**/
    public String mAnalyticsPageName = null;

    /**
     * 页面开发者信息
     * @return
     */
    protected abstract MManagerAble getDevManager();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
        SwipeBackHelper.onCreate(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    public Context getMContext() {
        return context;
    }

    private void getMRootView() {
        ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
    }

    private void swipeBackHelperUsage() {
        SwipeBackHelper.getCurrentPage(this)//获取当前页面
                .setSwipeBackEnable(true)//设置是否可滑动
                .setSwipeEdge(200)//可滑动的范围。px。200表示为左边200px的屏幕
                .setSwipeEdgePercent(0.2f)//可滑动的范围。百分比。0.2表示为左边20%的屏幕
                .setSwipeSensitivity(0.5f)//对横向滑动手势的敏感程度。0为迟钝 1为敏感
                .setScrimColor(Color.BLUE)//底层阴影颜色
                .setClosePercent(0.8f)//触发关闭Activity百分比
                .setSwipeRelateEnable(false)//是否与下一级activity联动(微信效果)。默认关
                .setSwipeRelateOffset(500)//activity联动时的偏移量。默认500px。
                .setDisallowInterceptTouchEvent(true)//不抢占事件，默认关（事件将先由子View处理再由滑动关闭处理）
                .addListener(new SwipeListener() {//滑动监听

                    @Override
                    public void onScroll(float percent, int px) {//滑动的百分比与距离
                    }

                    @Override
                    public void onEdgeTouch() {//当开始滑动
                    }

                    @Override
                    public void onScrollToClose() {//当滑动关闭
                    }
                });
    }

    //再按一次退出程序
    private long lastTime = 0;
    private int exitTime = 2000;

    protected void repeatBackKeyExit() {
        if ((System.currentTimeMillis() - lastTime) < exitTime) {
            finish();
        } else {
            lastTime = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
        }
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

    /**
     * 设置状态栏颜色
     * @param resId
     */
    protected void setStatusBarColor(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(resId);
        }
    }

    public void setMAnalyticsPageName(String mAnalyticsPageName) {
        this.mAnalyticsPageName = mAnalyticsPageName;
    }
}

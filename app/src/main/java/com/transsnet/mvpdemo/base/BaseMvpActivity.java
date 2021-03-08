package com.transsnet.mvpdemo.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.transsnet.mvpdemo.util.ReflectUtil;


/**
 * 说明: Activity基类 <br/>
 * 邮箱: yangbin5052@gmail.com <br/>
 * Create at 2017/12/21-下午10:47 by Luuren
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;
    protected Context mContext;
    private Activity activity;
    private String TAG = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        TAG = this.getLocalClassName();
        initLocaleLanguage();
        setContentView(getLayoutId());

        // 系统 6.0 以上 状态栏白底黑字的实现方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
//            StatusBarUtil.setColor(this, getResources().getColor(R.color.color_f5f5f5));
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        mContext = this;
        if (isMvp()) {
            mPresenter = ReflectUtil.getT(this, 0);
            if (null == mPresenter) {
                throw new RuntimeException("请在实现类添加Presenter泛型....");
            }
            mPresenter.onAttach(this);
        }

        initView();
        initData();
    }

    protected abstract void initLocaleLanguage();

    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * 初始化数据,默认在onCreate中调用
     *
     * @author caiyangbin
     * create at 2017/7/24 下午2:54
     */
    protected abstract void initData();

    /**
     * 是否打开mvp模式
     *
     * @return true --打开 ，false --关闭
     * @author caiyangbin
     * create at 2017/7/23 上午11:06
     */
    protected abstract boolean isMvp();




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.onDetach();
        }

    }


    @Override
    public void applyOverrideConfiguration(final Configuration overrideConfiguration) {
        if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT < 23) { //解决在5.1系统WebView的Xml无法实例化问题
            overrideConfiguration.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
        }
        super.applyOverrideConfiguration(overrideConfiguration);
    }


    public Activity getActivity() {
        return activity;
    }

    /**
     * 跳转页面相关
     *
     * @param cls
     */
    public void gotoOther(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void gotoOther(Intent intent) {
        startActivity(intent);
    }

    public void gotoOtherForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void gotoOtherForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }







    /**
     * 如果为点击键盘enter键隐藏键盘
     *
     * @param event
     * @return
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        try {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                /*隐藏软键盘*/

                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                if (inputMethodManager.isActive()) {

                    inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

                }

                return true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.dispatchKeyEvent(event);

    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }




}

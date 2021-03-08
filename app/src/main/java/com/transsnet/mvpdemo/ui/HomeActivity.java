package com.transsnet.mvpdemo.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.transsnet.mvpdemo.R;
import com.transsnet.mvpdemo.base.BaseMvpActivity;
import com.transsnet.mvpdemo.bean.HomeBanner;
import com.transsnet.mvpdemo.bean.SplashAdInfoBean;
import com.transsnet.mvpdemo.presenter.HomeContract;
import com.transsnet.mvpdemo.presenter.HomePresenter;
import com.transsnet.mvpdemo.util.ToastUtils;
import com.transsnet.mvpdemo.util.Utils;

/**
 * Author:  zengfeng
 * Time  :  2020/1/10 18:18
 * Des   :
 */
public class HomeActivity extends BaseMvpActivity<HomePresenter> implements HomeContract.IView {

    private ViewStub viewStub1;
    private ViewStub viewStub2;
    View view;

    @Override
    protected void initLocaleLanguage() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        viewStub1 = findViewById(R.id.stub_1);
        viewStub1.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                Log.d("vivi","inflated finish:"+inflated);
               view=inflated;
            }
        });
        viewStub2 = findViewById(R.id.stub_2);
        LinearLayout ll1 = (LinearLayout) viewStub1.inflate();
        TextView tv1 = ll1.findViewById(R.id.tv1);
        TextView tv2 = ll1.findViewById(R.id.tv2);
        tv1.setText("tv1");
        tv2.setText("tv2");
        Log.d("vivi","inflated ll1:"+ll1.equals(view) );

    }

    @Override
    protected void initData() {
        mPresenter.getBanner(this);
        mPresenter.uploadAgentInfo(this);
//        mPresenter.getSplashAdInfo(this);
        mPresenter.getXcrossEnter(this);

    }

    public  String getAppVersionName(final String packageName) {
        try {
            PackageManager pm = Utils.getApp().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
            return pi == null ? null : pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected boolean isMvp() {
        return true;
    }

    @Override
    public void onGetBannerSuccess(HomeBanner homeBanner) {
        Log.d("vivi","homeBanner"+homeBanner.getHomeBanner());
    }

    @Override
    public void onGetBannerFailed(String errCode, String errMsg) {
        Toast.makeText(this,errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAgentUploadDataSuccess() {
        Log.d("vivi","onAgentUploadDataSuccess--"+Thread.currentThread().getName());
    }

    @Override
    public void onAgentUploadDataError(String errCode, String errMsg) {
        Log.d("vivi","onAgentUploadDataError");
    }

    @Override
    public void onGetSplashAdInfoSuccess(SplashAdInfoBean bean) {
        Log.d("vivi","onGetSplashAdInfoSuccess:"+bean.getBootPagePic());
        ToastUtils.showShort(bean.getAdNo());
    }

    @Override
    public void onGetSplashAdInfoFailed(String errCode, String errMsg) {
        Log.d("vivi","onGetSplashAdInfoFailed:"+errMsg);
        ToastUtils.showShort("onGetSplashAdInfoFailed"+errMsg);
    }

    @Override
    public void onGetXcrossEnterSuccess(Object obj) {
        ToastUtils.showShort("Xcross入口查询成功");
        Log.d("vivi","onGetXcrossEnterSuccess");
    }

    @Override
    public void onGetXcrossEnterFailed(String errStatus, String errMsg) {
        ToastUtils.showShort("Xcross入口查询失败");
        Log.d("vivi","onGetXcrossEnterFailed");
    }
}

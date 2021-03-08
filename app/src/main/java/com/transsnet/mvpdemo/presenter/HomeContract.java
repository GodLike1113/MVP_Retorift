package com.transsnet.mvpdemo.presenter;

import android.content.Context;

import com.transsnet.mvpdemo.base.BasePresenter;
import com.transsnet.mvpdemo.bean.HomeBanner;
import com.transsnet.mvpdemo.bean.SplashAdInfoBean;

/**
 * Author:  zengfeng
 * Time  :  2020/1/10 18:08
 * Des   :
 */
public interface HomeContract {
    interface IView{
        void onGetBannerSuccess(HomeBanner homeBanner);
        void onGetBannerFailed(String errCode, String errMsg);

        void onAgentUploadDataSuccess();
        void onAgentUploadDataError(String errCode, String errMsg);

        void onGetSplashAdInfoSuccess(SplashAdInfoBean bean);
        void onGetSplashAdInfoFailed(String errCode, String errMsg);

        void onGetXcrossEnterSuccess(Object obj);
        void onGetXcrossEnterFailed(String errStatus, String errMsg);
    }

    abstract class Presenter extends BasePresenter<IView>{
        public abstract void getBanner(Context context);
        public abstract void uploadAgentInfo(Context context);
        public abstract void getSplashAdInfo(Context context);
        public abstract void getXcrossEnter(Context context);


    }
}

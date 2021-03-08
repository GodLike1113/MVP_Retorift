package com.transsnet.mvpdemo.model;

import android.content.Context;

import com.transsnet.mvpdemo.bean.HomeBanner;
import com.transsnet.mvpdemo.bean.PersonalInfo;
import com.transsnet.mvpdemo.bean.SplashAdInfoBean;

/**
 * Author:  zengfeng
 * Time  :  2020/1/10 17:55
 * Des   :
 */
public interface IHomeModel {
    void getPersonalInfo(Context context, OnGetPersonalInfoListener listener);
    void getData(Context context, OnGetDataListener listener);
    void getSplashAdInfo(Context context,OnGetSplashInfoListener listener);
    void getXcrossEnter(Context context,OnQueryXcrossEnterListener listener);

    interface OnGetPersonalInfoListener {
        void onGetPersonalInfoSuccess(PersonalInfo bean);
        void onGetPersonalInfoFailed(String errStatus, String errMsg);
    }

    interface OnGetDataListener {
        void onGetDataSuccess(HomeBanner bean);
        void onGetDataFailed(String errStatus, String errMsg);
    }

    interface OnGetSplashInfoListener{
        void onGetSplashInfoSuccess(SplashAdInfoBean bean);
        void onGetSplashInfoFailed(String errStatus, String errMsg);
    }

    interface OnQueryXcrossEnterListener{
        void onGetXcrossEnterSuccess(Object obj);
        void onGetXcrossEnterFailed(String errStatus, String errMsg);
    }
}

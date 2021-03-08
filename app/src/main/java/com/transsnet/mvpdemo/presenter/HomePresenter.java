package com.transsnet.mvpdemo.presenter;

import android.content.Context;

import com.transsnet.mvpdemo.bean.HomeBanner;
import com.transsnet.mvpdemo.bean.PersonalInfo;
import com.transsnet.mvpdemo.bean.SplashAdInfoBean;
import com.transsnet.mvpdemo.model.HomeModel;
import com.transsnet.mvpdemo.model.IHomeModel;
import com.transsnet.mvpdemo.ui.HomeActivity;
import com.transsnet.mvpdemo.util.ActivityUtils;

/**
 * Author:  zengfeng
 * Time  :  2020/1/10 18:07
 * Des   :
 */
public class HomePresenter extends HomeContract.Presenter {
    private HomeModel mHomeModel;

    @Override
    public void getBanner(Context context) {
        if (mHomeModel == null) {
            mHomeModel = new HomeModel();
        }
        mHomeModel.getData(context, new IHomeModel.OnGetDataListener() {
            @Override
            public void onGetDataSuccess(HomeBanner bean) {
                if (isViewAttached()) {
                    getView().onGetBannerSuccess( bean);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity) ActivityUtils.getTopActivity()).onGetBannerSuccess((HomeBanner) bean);
                }
            }

            @Override
            public void onGetDataFailed(String errStatus, String errMsg) {
                if (isViewAttached()) {
                    getView().onGetBannerFailed(errStatus,errMsg);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity) ActivityUtils.getTopActivity()).onGetBannerFailed(errStatus,errMsg);
                }
            }
        });
    }

    @Override
    public void uploadAgentInfo(Context context) {
        if (mHomeModel == null) {
            mHomeModel = new HomeModel();
        }
        mHomeModel.getPersonalInfo(context, new IHomeModel.OnGetPersonalInfoListener() {
            @Override
            public void onGetPersonalInfoSuccess(PersonalInfo bean) {
                if (isViewAttached()) {
                    getView().onAgentUploadDataSuccess();
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity) ActivityUtils.getTopActivity()).onAgentUploadDataSuccess();
                }
            }

            @Override
            public void onGetPersonalInfoFailed(String errStatus, String errMsg) {
                if (isViewAttached()) {
                    getView().onAgentUploadDataError(errStatus,errMsg);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity) ActivityUtils.getTopActivity()).onAgentUploadDataError(errStatus,errMsg);
                }
            }
        });
    }

    @Override
    public void getSplashAdInfo(final Context context) {
        if (mHomeModel == null) {
            mHomeModel = new HomeModel();
        }
        mHomeModel.getSplashAdInfo(context, new IHomeModel.OnGetSplashInfoListener() {
            @Override
            public void onGetSplashInfoSuccess(SplashAdInfoBean bean) {
                if (isViewAttached()) {
                    getView().onGetSplashAdInfoSuccess(bean);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity)context).onGetSplashAdInfoSuccess(bean);
                }
            }

            @Override
            public void onGetSplashInfoFailed(String errStatus, String errMsg) {
                if (isViewAttached()) {
                    getView().onGetSplashAdInfoFailed(errStatus,errMsg);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity)context).onGetSplashAdInfoFailed(errStatus,errMsg);
                }
            }
        });

    }

    @Override
    public void getXcrossEnter(final Context context) {
        if (mHomeModel == null) {
            mHomeModel = new HomeModel();
        }
        mHomeModel.getXcrossEnter(context, new IHomeModel.OnQueryXcrossEnterListener() {
            @Override
            public void onGetXcrossEnterSuccess(Object obj) {
                if (isViewAttached()) {
                    getView().onGetXcrossEnterSuccess(obj);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity)context).onGetXcrossEnterSuccess(obj);
                }
            }

            @Override
            public void onGetXcrossEnterFailed(String errStatus, String errMsg) {
                if (isViewAttached()) {
                    getView().onGetXcrossEnterFailed(errStatus,errMsg);
                } else if (ActivityUtils.getTopActivity() instanceof HomeActivity) {
                    ((HomeActivity)context).onGetXcrossEnterFailed(errStatus,errMsg);
                }
            }
        });
    }
}

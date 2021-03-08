package com.transsnet.mvpdemo.model;

import android.content.Context;
import android.util.Log;

import com.transsnet.mvpdemo.app.App;
import com.transsnet.mvpdemo.bean.HomeBanner;
import com.transsnet.mvpdemo.bean.PersonalInfo;
import com.transsnet.mvpdemo.bean.SplashAdInfoBean;
import com.transsnet.mvpdemo.http.BaseNewObserver;
import com.transsnet.mvpdemo.http.BaseObserver;
import com.transsnet.mvpdemo.http.RxHelper;
import com.transsnet.mvpdemo.http.bean.BaseEntity;
import com.transsnet.mvpdemo.http.bean.BaseNewEntity;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Author:  zengfeng
 * Time  :  2020/1/10 17:55
 * Des   :  获取数据的Model
 */
public class HomeModel implements IHomeModel {
    @Override
    public void getPersonalInfo(Context context, OnGetPersonalInfoListener listener) {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName("zhangsan");
        personalInfo.setAge(18);
        //此处写网络请求和回调
        if (listener != null) {
            listener.onGetPersonalInfoSuccess(personalInfo);
        }
    }

    @Override
    public void getData(Context context, OnGetDataListener listener) {
        if (listener != null) {
            listener.onGetDataSuccess(new HomeBanner());
        }
    }

    @Override
    public void getSplashAdInfo(Context context, final OnGetSplashInfoListener listener) {
        HashMap<String,String> map =new HashMap<>();
        map.put("test","test");
        App.api.getSplashAdInfo(map)
                .compose(RxHelper.<BaseEntity<SplashAdInfoBean>>io_main())
                .subscribe(new BaseObserver<SplashAdInfoBean>(context) {
                    @Override
                    protected void onFail(String errorStatus, String msg) {
                        Log.d("vivi","onFail:"+msg);
                        if(listener!=null){
                            listener.onGetSplashInfoFailed(errorStatus,msg);
                        }

                    }

                    @Override
                    public void onSuccess(SplashAdInfoBean splashAdInfoBean) {
                        Log.d("vivi","onSuccess:"+splashAdInfoBean.getBootPagePic());
                        if(listener!=null){
                            listener.onGetSplashInfoSuccess(splashAdInfoBean);
                        }
                    }
                });
    }

    @Override
    public void getXcrossEnter(Context context, final OnQueryXcrossEnterListener listener) {
        HashMap<String,String> map =new HashMap<>();
        map.put("test","test");
//        App.api.getXcrossOpenStatus(map)
//                .compose(RxHelper.<BaseEntity<Object>>io_main())
//                .subscribe(new BaseObserver<Object>(context) {
//                    @Override
//                    protected void onFail(String errorStatus, String msg) {
//                        if(listener!=null){
//                            listener.onGetXcrossEnterFailed(errorStatus,msg);
//                        }
//                    }
//
//                    @Override
//                    public void onSuccess(Object o) {
//                        if(listener!=null){
//                            listener.onGetXcrossEnterSuccess(o);
//                        }
//                    }
//                });

        App.api.getXcrossOpenStatus(map)
                .compose(RxHelper.<BaseNewEntity<Object>>io_main())
                .subscribe(new BaseNewObserver<Object>(context) {
                    @Override
                    protected void onFail(int errorStatus, String msg) {
                        if(listener!=null){
                            listener.onGetXcrossEnterFailed(errorStatus+"",msg);
                        }
                    }

                    @Override
                    public void onSuccess(Object o) {
                        if(listener!=null){
                            listener.onGetXcrossEnterSuccess(o);
                        }
                    }
                });
    }


}

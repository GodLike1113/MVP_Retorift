package com.transsnet.mvpdemo.http;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.transsnet.mvpdemo.contants.Contstant;
import com.transsnet.mvpdemo.dialog.LoadingDialog;
import com.transsnet.mvpdemo.http.bean.BaseNewEntity;
import com.transsnet.mvpdemo.http.exception.ApiException;
import com.transsnet.mvpdemo.http.exception.WeakNetworkException;
import com.transsnet.mvpdemo.timer.NetworkTimeCount;
import com.transsnet.mvpdemo.util.LogUtils;
import com.transsnet.mvpdemo.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ConcurrentModificationException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseNewObserver<T> implements Observer<BaseNewEntity<T>> {
    private Context mContext;
    private LoadingDialog mProgressDialog;
    private Disposable d;
    private boolean isShowLoading = true;
    private int mTimeCount;
    private NetworkTimeCount mNetworkTimeCount;

    public BaseNewObserver(Context context) {
        this(context,true);
    }

    public BaseNewObserver(Context context, int timeCount) {
        this(context,true,timeCount);

    }

    public BaseNewObserver(Context context, boolean needShowLoading) {
        this.mContext = context;
        isShowLoading = needShowLoading;
        if(!((Activity) context).isFinishing()) {
            if (isShowLoading) {
                mProgressDialog = new LoadingDialog(context);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
            }
        }
        mTimeCount = Contstant.NOMAL_TIME_COUNT;
    }

    public BaseNewObserver(Context context, boolean needShowLoading, int timeCount) {
        this.mContext = context;
        if(!((Activity) context).isFinishing()) {
            isShowLoading = needShowLoading;
            if (isShowLoading) {
                mProgressDialog = new LoadingDialog(context);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
                mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
            }
        }
        mTimeCount = timeCount;
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        if (isShowLoading&&(mProgressDialog!=null)&&(mContext!=null)){
            mProgressDialog.show();
        }
        startTimeCount();
    }

    @Override
    public void onNext(BaseNewEntity<T> tBaseEntity) {
        if (isShowLoading&&(mProgressDialog!=null)&&(mContext!=null)){
            try {
                mProgressDialog.dismiss();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        if(tBaseEntity.getStatus().getCode() == 0){
            try {
                onSuccess(tBaseEntity.getData());
            } catch (Exception e){
                if(e!=null){
                    Log.d("BaseNewObserver_onNext","------exception=====" + e.toString());
                }
                e.printStackTrace();
                onFail(tBaseEntity.getStatus().getCode(),"");
            }
        } else {
            onFail(tBaseEntity.getStatus().getCode(),tBaseEntity.getStatus().getMsg());
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isShowLoading&&(mProgressDialog!=null)){
            try {
                mProgressDialog.dismiss();
            } catch (Exception exception){
                LogUtils.file(exception.getMessage());
            }

        }
        if (e instanceof ApiException) {
            // TODO: 2018/3/13 处理报错信息
            LogUtils.file(LogUtils.I,"http","-ApiException="+(e==null?"":e.getMessage()));
            ToastUtils.showDuration("ApiException");
            onFail(BaseNewEntity.API_EXCEPTION,"");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            Log.d("BaseNewObserver_onError","------exception=====" + e.toString());
            ToastUtils.showDuration("网络连接失败");
            onFail(BaseNewEntity.CONNECTION_EXCEPTION,"");
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            Log.d("BaseNewObserver_onError","------exception=====" + e.toString());
            ToastUtils.showDuration("网络连接超时");
            onFail(BaseNewEntity.TIMEOUT_EXCEPTION,"");
        } else if (e instanceof WeakNetworkException){
            Log.d("BaseNewObserver_onError","------exception=====" + e.toString());
            ToastUtils.showDuration("网络太差了");
            onWeakNetWorkError();
            onFail(BaseNewEntity.WEAKNETWORK_EXCEPTION,"");
        } else if (e instanceof JsonSyntaxException) {
            Log.d("BaseNewObserver_onError","------exception=====" + e.toString());
            ToastUtils.showDuration("数据解析异常");
            onFail(BaseNewEntity.JSONSYNTAXEXCEPTION,"");
        }  else if(e instanceof HttpException) {
            if(((HttpException) e).code()==401){
                LogUtils.file(LogUtils.I,"http","--401--");
                Log.d("BaseNewObserver_onError","------http=====401");
                ToastUtils.showShort("请重试！");
            } else {
                LogUtils.file(LogUtils.I,"http","--HttpException=="+(e==null?"":e.getMessage()));

            }
            onFail(BaseNewEntity.HTTP_EXCEPTION,"");
        }else if (e instanceof ConcurrentModificationException){
            LogUtils.file(LogUtils.I,"http","--ConcurrentModificationException=="+(e==null?"":e.getMessage()));
            onFail(BaseNewEntity.CONCURRENTMODIFICATION_EXCEPTION,"");
        }else {
            LogUtils.file(LogUtils.I,"http","-Exception-else=="+(e==null?"":e.getMessage()));
            ToastUtils.showShort("请再试一次!");
            onFail(BaseNewEntity.TIMEOUT_EXCEPTION,"");
        }
        onNetWorkError();
        e.printStackTrace();
        stopTimeCount();
    }

    @Override
    public void onComplete() {
        if (isShowLoading&&(mProgressDialog!=null)){
            try {
                mProgressDialog.dismiss();
            } catch (Exception exception){
                LogUtils.file(exception.getMessage());
            }
        }
        stopTimeCount();
    }

    private void startTimeCount(){
        mNetworkTimeCount = new NetworkTimeCount(mTimeCount, new NetworkTimeCount.TimeCountFinishListener() {
            @Override
            public void onFinish() {
                sendWeakNetworkError();
            }
        });
        mNetworkTimeCount.start();
    }

    private void stopTimeCount(){
        if(mNetworkTimeCount != null){
            mNetworkTimeCount.cancel();
            mNetworkTimeCount = null;
        }
    }

    private void sendWeakNetworkError(){
        this.onError(new WeakNetworkException());
        if(this.d != null){
            this.d.dispose();
        }
    }

    protected abstract void onFail(int errorStatus,String msg);

    public abstract void onSuccess(T t);
    public void onNetWorkError(){}
    public void onTimeOutError(){}
    public void onWeakNetWorkError(){}
}

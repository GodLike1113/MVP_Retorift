package com.transsnet.mvpdemo.http;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;

import androidx.annotation.NonNull;

import com.google.gson.JsonSyntaxException;
import com.transsnet.mvpdemo.bean.UpdateEvent;
import com.transsnet.mvpdemo.contants.Contstant;
import com.transsnet.mvpdemo.dialog.LoadingDialog;
import com.transsnet.mvpdemo.http.bean.BaseEntity;
import com.transsnet.mvpdemo.http.exception.ApiException;
import com.transsnet.mvpdemo.http.exception.WeakNetworkException;
import com.transsnet.mvpdemo.timer.NetworkTimeCount;
import com.transsnet.mvpdemo.util.EventBusUtils;
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


public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private Context mContext;
    private LoadingDialog mProgressDialog;
    private Disposable d;
    private boolean isShowLoading = true;
    private int mTimeCount;
    private NetworkTimeCount mNetworkTimeCount;

    public BaseObserver(Context context) {
        this(context, true);
    }

    public BaseObserver(Context context, int timeCount) {
        this(context, true, timeCount);
    }

    public BaseObserver(Context context, boolean needShowLoading) {

        this.mContext = context;
        isShowLoading = needShowLoading;
        if (!((Activity) context).isFinishing()) {
            if (isShowLoading) {
                mProgressDialog = new LoadingDialog(context);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                mProgressDialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
                mProgressDialog.setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
            }
        }
        mTimeCount = Contstant.NOMAL_TIME_COUNT;
    }

    public BaseObserver(Context context, boolean needShowLoading, int timeCount) {

        this.mContext = context;
        if (!((Activity) context).isFinishing()) {
            isShowLoading = needShowLoading;
            if (isShowLoading) {
                mProgressDialog = new LoadingDialog(context);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                mProgressDialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        d.dispose();
                    }
                });
                mProgressDialog.setOnCancelListener(new OnCancelListener() {
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
    public void onSubscribe(@NonNull Disposable d) {
        this.d = d;
        if (isShowLoading && (mProgressDialog != null) && (mContext != null)) {
            mProgressDialog.show();
        }
        startTimeCount();

    }

    @Override
    public void onNext(@NonNull BaseEntity<T> tBaseEntity) {
        if (isShowLoading && (mProgressDialog != null) && (mContext != null)) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        switch (tBaseEntity.getStatus()) {
            case BaseEntity.SUCCESS://成功
                try {
                    onSuccess(tBaseEntity.getData());
                } catch (Exception e) {
                    if (e != null) {
                        LogUtils.file(LogUtils.E, "------exception=====" + e.toString());
                    }
                    e.printStackTrace();
                    onFail(tBaseEntity.getStatus(), "");
                }

                break;
            case BaseEntity.ERROR://失败
            case BaseEntity.FAIL:
                try {
                    onFail(tBaseEntity.getStatus(), tBaseEntity.getMsg());
                } catch (Exception e) {
                    if (e != null) {
                        LogUtils.file(LogUtils.E, "------exception=====" + e.getMessage() == null ? "" : e.getMessage());
                    }
                    e.printStackTrace();
                }

                break;

            case BaseEntity.FAIL_HASAUTH:
                try {
                    onFailHasSuccess(tBaseEntity.getData());
                } catch (Exception e) {
                    if (e != null) {
                        LogUtils.file(LogUtils.E, "------exception=====" + e.getMessage() == null ? "" : e.getMessage());
                    }
                    e.printStackTrace();
                }
                break;

            case BaseEntity.FALI_HASBINDBANKACCOUNT:
                try {
                    onFailHasSuccess(tBaseEntity.getData());
                } catch (Exception e) {
                    if (e != null) {
                        LogUtils.file(LogUtils.E, "------exception=====" + e.getMessage() == null ? "" : e.getMessage());
                    }
                    e.printStackTrace();
                }
                break;
            case BaseEntity.NOT_LOGIN://token失效
                onLoginError();
                break;
            case BaseEntity.APP_UPDATE://版本更新
                LogUtils.e("版本更新");
                EventBusUtils.post(new UpdateEvent());
                break;

            default:
                try {
                    onFail(tBaseEntity.getStatus(), tBaseEntity.getMsg());
                } catch (Exception e) {
                    if (e != null) {
                        LogUtils.file(LogUtils.E, "------exception=====" + e.toString());
                    }
                }

                break;
        }
    }

    //登录失效
    private void onLoginError() {
        ToastUtils.showShort("登陆过期，请重新登录");
//    GlobalCreditCacheManager.getInstance().removeLoginInfo();
//    EventBusUtils.post(new LogoutEvent());
//    Intent intent = new Intent(mContext, LoginActivity.class);
//    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP
//      | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//    mContext.startActivity(intent);
    }

    protected abstract void onFail(String errorStatus, String msg);

    @Override
    public void onError(@NonNull Throwable e) {

        if (isShowLoading && (mProgressDialog != null)) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception exception) {
                LogUtils.file(exception.getMessage());
            }
        }

        if (e instanceof ApiException) {
            // TODO: 2018/3/13 处理报错信息
            LogUtils.file(LogUtils.I, "http", "-ApiException=" + (e == null ? "" : e.getMessage()));
            ToastUtils.showDuration("ApiException");
            onFail("", "");
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            LogUtils.file(LogUtils.I, "http", "-ConnectException=UnknownHostException=" + (e == null ? "" : e.getMessage()));
            ToastUtils.showDuration("网络连接失败");
            onFail("", "");
        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
            LogUtils.file(LogUtils.I, "http", "-TimeoutException=SocketTimeoutException=" + (e == null ? "" : e.getMessage()));
            ToastUtils.showDuration("网络连接超时");
            onFail("", "");
        } else if (e instanceof WeakNetworkException) {
            LogUtils.file(LogUtils.I, "http", "-WeakNetworkException=" + (e == null ? "" : e.getMessage()));
            ToastUtils.showDuration("网络连接超时");
            onWeakNetWorkError();
            onFail("", "");
        } else if (e instanceof JsonSyntaxException) {
            LogUtils.file(LogUtils.I, "http", "-JsonSyntaxException==" + (e == null ? "" : e.getMessage()));
            ToastUtils.showDuration("数据异常");
            onFail("", "");
        } else if (e instanceof HttpException) {
            if (((HttpException) e).code() == 401) {
                LogUtils.file(LogUtils.I, "http", "--401--");
                ToastUtils.showShort("请重试！");
            } else {
                LogUtils.file(LogUtils.I, "http", "--HttpException==" + (e == null ? "" : e.getMessage()));

            }
            onFail("", "");
        } else if (e instanceof ConcurrentModificationException) {
            LogUtils.file(LogUtils.I, "http", "--ConcurrentModificationException==" + (e == null ? "" : e.getMessage()));
            onFail("", "");
        } else {
            LogUtils.file(LogUtils.I, "http", "-Exception-else==" + (e == null ? "" : e.getMessage()));
            ToastUtils.showShort("请重试");
            onFail("", "");
        }
        onNetWorkError();
        e.printStackTrace();
        stopTimeCount();
    }

    @Override
    public void onComplete() {
        if (isShowLoading && (mProgressDialog != null)) {
            try {
                mProgressDialog.dismiss();
            } catch (Exception exception) {
                LogUtils.file(exception.getMessage());
            }
        }
        stopTimeCount();

    }

    private void startTimeCount() {

        mNetworkTimeCount = new NetworkTimeCount(mTimeCount, new NetworkTimeCount.TimeCountFinishListener() {
            @Override
            public void onFinish() {
                sendWeakNetworkError();
            }
        });
        mNetworkTimeCount.start();


    }

    private void stopTimeCount() {
        if (mNetworkTimeCount != null) {
            mNetworkTimeCount.cancel();
            mNetworkTimeCount = null;
        }
    }

    private void sendWeakNetworkError() {
        this.onError(new WeakNetworkException());
        if (this.d != null) {
            this.d.dispose();
        }
    }

    public abstract void onSuccess(T t);

    public void onNetWorkError() {
    }

    public void onTimeOutError() {
    }

    public void onFailHasSuccess(Object object) {
    }

    public void onWeakNetWorkError() {
    }

}

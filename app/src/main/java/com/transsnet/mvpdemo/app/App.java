package com.transsnet.mvpdemo.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.airbnb.lottie.model.LottieCompositionCache;
import com.transsnet.mvpdemo.R;
import com.transsnet.mvpdemo.contants.Contstant;
import com.transsnet.mvpdemo.http.RetrofitUtil;
import com.transsnet.mvpdemo.http.api.Api;
import com.transsnet.mvpdemo.util.LogUtils;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;


/**
 *build {multiDexEnabled true}
 */
public class App extends MultiDexApplication {
    private static final String TAG = App.class.getSimpleName();
    private static final long CONNECT_TIMEOUT = 30000;
    private static final long READ_TIMEOUT = 30000;
    public static Api api;
    public static Calendar calendar;
    private static App myApplication;
    private boolean isDebug = true;
    public static String language = "en_NG";
    public static Typeface monetaryUnitFont;
    public static Typeface titleBarFont;
    public static Typeface newAmountFont;
    private boolean isInitStatInterface = false;
    private boolean hasAppsFlyerCallback = false;
    public boolean IS_INIT_DATA_APPLICATION = false;
    public int liveChatCount;
    public boolean isEnterChat = false;
    public boolean LiveChatHaveFirstName = false;
    private String firstName;
    OkHttpClient client = null;
    public final static int APP_STATUS_KILLED = 0; // 表示应用是被杀死后在启动的
    public final static int APP_STATUS_NORMAL = 1; // 表示应用时正常的启动流程
    public static int APP_STATUS = APP_STATUS_KILLED; // 记录App的启动状态

    /**********************不要在Application类里调用网络请求***********************/
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("vivi","Application onCreate");

//        if (MemoryUtils.hasAvailMemory(this)) {
//            if (!getProcessName(this).equals(this.getPackageName())) {
//                return;
//            }
//            myApplication = this;
//            InitCommonLib();
//            calendar = Calendar.getInstance();
//            AppInit.initUtils(this, BuildConfig.DEBUG);
//            if (PreferenceUtil.getBooleanValue(this, PreferenceUtil.POLICY_DIALOG_FIRST_SHOWED)) {
//                IS_INIT_DATA_APPLICATION = true;
//                AppInit.IS_INIT_DATA_APPLICATION = true;
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        //设置线程的优先级，不与主线程抢资源
//                        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
//                        initThirdLib();
//                    }
//                }).start();
//                initTudc();
                retrofitUtils();
//                UploadBuildApkConfig.setEnvironment(BuildApkConfig.getEnvironment());
//                initCC();
//            }
//            registerNetworkReceive(myApplication);
//        } else {
//            myApplication = null;
//        }
    }

//    private void InitCommonLib() {
//        AppInit.setApplication(this);
//        AppInit.setChannelId(BuildApkConfig.getChannel_Id());
//        AppInit.setXcrossBaseUrl(BuildConfig.XCROSS_BASE_URL);
//        AppInit.setAppKey(BuildApkConfig.getAppKey());
//        AppInit.setAppSecret(BuildApkConfig.getAppSecret());
//        AppInit.setTudcAppid(getResources().getString(R.string.tudc_appid));
//        AppInit.setBigDataUrl(BuildConfig.BIGDATA_BASE_URL);
//        AppInit.setBusinessBaseUrl(BuildConfig.BASE_URL);
//        AppInit.initApm(BuildConfig.NET_STREAM_NAME);
//    }
//
//    public void init() {
//        initThirdLib();
//        initTudc();
//        retrofitUtils();
//        IS_INIT_DATA_APPLICATION = true;
//        AppInit.IS_INIT_DATA_APPLICATION = true;
//    }
//
//    private void initThirdLib() {
//        AppInit.initTudcAndCrash(this, BuildConfig.DEBUG);
//        initAppsFlyer();
//        RateCacheCheckUtils.check();
//        initWorkMananger();
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public void retrofitUtils() {
        if (api == null) {
            api = RetrofitUtil.getApiService(Api.class);
        }
    }

    public static App getMyApplication() {
        return myApplication;
    }

    public static Typeface getMonetaryUnitFont(Context context) {
        if (monetaryUnitFont != null) {
            return monetaryUnitFont;
        } else {
            monetaryUnitFont = Typeface.createFromAsset(context.getAssets(), "font/iconfont.ttf");
        }
        return monetaryUnitFont;
    }

    public static Typeface getTitleBarFont(Context context) {
        if (titleBarFont != null) {
            return titleBarFont;
        } else {
            titleBarFont = Typeface.createFromAsset(context.getAssets(), "font/avenir_heavy.ttf");
        }
        return titleBarFont;
    }

    public static Typeface getNewAmountFont(Context context) {
        if (newAmountFont != null) {
            return newAmountFont;
        }else{
            newAmountFont = Typeface.createFromAsset(context.getAssets(), "font/DINAlternate-bold.ttf");
        }
        return newAmountFont;
    }


    private String getProcessName(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return "";
        }

        for (ActivityManager.RunningAppProcessInfo runningAppProcess : runningAppProcesses) {
            if (runningAppProcess.pid == android.os.Process.myPid()
                    && !TextUtils.isEmpty(runningAppProcess.processName)) {
                return runningAppProcess.processName;
            }
        }
        return "";
    }

//    /**
//     * 7.0需要动态注册0需要动态注册CONNECTIVITY_ACTION广播
//     */
//    private NetWorkStateReceiver mNetWorkStateReceiver;
//
//    private void registerNetworkReceive(Context context) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            IntentFilter intentFilter = new IntentFilter(CONNECTIVITY_ACTION);
//            intentFilter.setPriority(Integer.MAX_VALUE);
//            context.registerReceiver(new NetWorkStateReceiver(), intentFilter);
//        }
//    }
//
//    private void unRegisterNetworkReceive(Context context) {
//        if (mNetWorkStateReceiver != null) {
//            context.unregisterReceiver(mNetWorkStateReceiver);
//        }
//    }


    public static void reInitApp() {
//        Intent intent = new Intent(getMyApplication(), SplashActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        getMyApplication().startActivity(intent);
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Log.d(TAG,"onTrimMemory"+level + "");
        switch (level) {
            case TRIM_MEMORY_COMPLETE:
            case TRIM_MEMORY_MODERATE:
            case TRIM_MEMORY_BACKGROUND:
            case TRIM_MEMORY_UI_HIDDEN:
            case TRIM_MEMORY_RUNNING_CRITICAL:
            case TRIM_MEMORY_RUNNING_LOW:
            case TRIM_MEMORY_RUNNING_MODERATE:
                releaseMemory();
                break;
        }
    }

    public static void releaseMemory() {
//        if(AppInit.NETWORK_URL_MAP.size() > 0) {
//            for (String key : AppInit.NETWORK_URL_MAP.values()) {
//                GlideUtil.invalidate(key);
//            }
//            AppInit.NETWORK_URL_MAP.clear();
//        }
        LottieCompositionCache.getInstance().clearCache();
    }
}

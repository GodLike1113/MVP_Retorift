package com.transsnet.mvpdemo.http;

import android.text.TextUtils;


import com.transsnet.mvpdemo.contants.Contstant;
import com.transsnet.mvpdemo.util.LoggerInterceptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create by tianshenyi on 2020/4/24.
 */
public class RetrofitUtil {
    private static Retrofit mRetrofit;

    private static Retrofit getRetrofit() {
        if (null == mRetrofit) {
            OkHttpClient.Builder builder;
            builder = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        //添加共有Header
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request.Builder builder = chain.request().newBuilder();
                            String headerValues = chain.request().header(Contstant.HEADER_KEY);
                            if(headerValues != null && headerValues.equals(Contstant.XCROSS_HEADER_NAME)) {
//                                builder.addHeader("X-TOKEN", GlobalCreditCacheManager.getInstance().getToken());
//                                if (GlobalCreditCacheManager.getInstance().getLoginedInfo() != null) {
//                                    builder.addHeader("X-UID", GlobalCreditCacheManager.getInstance().getLoginedInfo().getUid());
//                                } else {
//                                    builder.addHeader("X-UID", "");
//                                }
//                                if (GlobalCreditCacheManager.getInstance().getmStateInfo() != null) {
//                                    builder.addHeader("X-CID", GlobalCreditCacheManager.getInstance().getmStateInfo().getCustId());
//                                }else{
//                                    builder.addHeader("X-CID", "");
//                                }
//                                builder.addHeader("X-APP-ID", Contstant.X_CROSS);
//                                builder.addHeader("csign", GlobalCreditCacheManager.getInstance().getSignSha1());
                                if (TextUtils.isEmpty(chain.request().header("X-REQUEST-ID"))) {
                                    String requestId = UUID.randomUUID().toString();
                                    builder.addHeader("X-REQUEST-ID", requestId);
                                    builder.addHeader(Contstant.AF_REQUESTID, requestId);
                                } else {
                                    builder.addHeader(Contstant.AF_REQUESTID, chain.request().header("X-REQUEST-ID"));
                                }
                                builder.addHeader("language", Locale.getDefault().toString());
                                builder.addHeader("countryCode", Contstant.CURCOUNTRY);//国家代码与后端约定
//                                builder.addHeader("X-CHANNEL", AppInit.getChannelId());
//                                try {
//                                        builder.addHeader(Contstant.XAGENTID, AppInit.getAgentId());
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                builder.addHeader(Contstant.XAPPVERNAME, GlobalCreditCacheManager.getInstance().getVersionName());
//                                builder.addHeader("X-APP-VERSION", GlobalCreditCacheManager.getInstance().getVersionCode());
                                builder.addHeader("reqChannel", Contstant.REQUEST_CHANNEL);

                                String xcrossBaseUrl="https://testing-xcross.palmcredit.loans/";
                                HttpUrl oldHttpUrl = chain.request().url();
                                HttpUrl newBaseUrl = HttpUrl.parse(xcrossBaseUrl);
                                HttpUrl newFullUrl = oldHttpUrl
                                        .newBuilder()
                                        .scheme(newBaseUrl.scheme())
                                        .host(newBaseUrl.host())
                                        .port(newBaseUrl.port())
                                        .build();
                                return chain.proceed(builder.url(newFullUrl).build());
                            } else {
//                                builder.addHeader("token", GlobalCreditCacheManager.getInstance().getToken());
//                                builder.addHeader("csign", GlobalCreditCacheManager.getInstance().getSignSha1());
                                if (TextUtils.isEmpty(chain.request().header(Contstant.REQUESTID))) {
                                    String requestId = UUID.randomUUID().toString();
                                    builder.addHeader(Contstant.REQUESTID, requestId);
                                    builder.addHeader(Contstant.AF_REQUESTID, requestId);
                                } else {
                                    builder.addHeader(Contstant.AF_REQUESTID, chain.request().header(Contstant.REQUESTID));
                                }
                                builder.addHeader("language", Locale.getDefault().toString());
                                builder.addHeader("countryCode", Contstant.CURCOUNTRY);//国家代码与后端约定
//                                builder.addHeader("app_channelid", AppInit.getChannelId());
//                                builder.addHeader(Contstant.XAGENTID, AppInit.getAgentId());
//                                builder.addHeader(Contstant.XAPPVERNAME, GlobalCreditCacheManager.getInstance().getVersionName());
//                                builder.addHeader(Contstant.XAPPVERCODE, GlobalCreditCacheManager.getInstance().getVersionCode());
                                builder.addHeader("reqChannel", Contstant.REQUEST_CHANNEL);
                                return chain.proceed(builder.build());
                            }
                        }
                    })
                    .addInterceptor(
                            new LoggerInterceptor("http", true)
                    )
                    .connectTimeout(Contstant.NOMAL_TIME_COUNT, TimeUnit.MILLISECONDS)
                    .readTimeout(Contstant.NOMAL_TIME_COUNT, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(false);
            SSLSocketFactory sslSocketFactory = getSSLSocketFactory();
            if (sslSocketFactory != null) {
                builder.sslSocketFactory(sslSocketFactory);
            }

            String baseBusinessUrl="https://testing-palmcredit-app-ng.palmcash.com/appserver/";
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(baseBusinessUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(builder.build())
                    .build();
        }
        return mRetrofit;
    }

    private static SSLSocketFactory getSSLSocketFactory() {

        try {
            SSLContext sc = null;
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            KeyStore trusted = KeyStore.getInstance("PKCS12", "BC");
            trusted.load(null, null);
            InputStream in = new ByteArrayInputStream(CrtConstant.CRT.getBytes("UTF-8"));
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(in);
            trusted.setCertificateEntry("trust", certificate);
            in.close();

            tmf.init(trusted);

            TrustManager[] tms = tmf.getTrustManagers();

            sc = SSLContext.getInstance("TLS");
            sc.init(null, tms, new SecureRandom());
            return sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T getApiService(Class<T> tClass) {
        return getRetrofit().create(tClass);
    }
}

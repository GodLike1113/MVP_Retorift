package com.transsnet.mvpdemo.http;

import android.content.Context;

import com.transsnet.mvpdemo.util.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

/**
 * 本类主页用在服务创建OkhttpClient;
 */

public class OkhtttpUtil {
//    public static OkHttpClient makeHttpClient(Context context, Interceptor interceptor) {
//        OkHttpClient.Builder builder;
//        builder = new OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .addInterceptor(
//                        new LoggerInterceptor("http", context)
//                )
//                // .hostnameVerifier(new TrustHostnameVerifier())
//                .connectTimeout(60000, TimeUnit.MILLISECONDS)
//                .readTimeout(60000, TimeUnit.MILLISECONDS)
//                .pingInterval(20, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true);
//        SSLSocketFactory sslSocketFactory = SslUtil.getSSLSocketFactory();
//        if (sslSocketFactory != null) {
//            builder.sslSocketFactory(sslSocketFactory);
//        }
//        return builder.build();
//    }

    public static OkHttpClient makeHttpClient(Context context,Interceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(
                        new LoggerInterceptor("http", true)
                )
                .sslSocketFactory(SslUtil.getSSLSocketFactory())
                .hostnameVerifier(new TrustHostnameVerifier())
                .connectTimeout(60000, TimeUnit.MILLISECONDS)
                .readTimeout(60000, TimeUnit.MILLISECONDS)
                .pingInterval(20,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    public static MediaType getMediaType(){
        return MediaType.parse("application/json; charset=utf-8");
    }


    public static String getEncoding(){
        return "gzip";
    }
}

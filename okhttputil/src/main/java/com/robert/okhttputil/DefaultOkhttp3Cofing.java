package com.robert.okhttputil;

import com.robert.okhttputil.https.HTTPSTrustManager;
import com.robert.okhttputil.interceptor.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * Retrofit2 HTTP 客户端调用类.
 * Created by robert on 16/03/16.
 */
public class DefaultOkhttp3Cofing {
    public static final long DEFAULT_CONNET_TIMEOUT = 15 * 1000;
    public static final long DEFAULT_READ_TIMEOUT = 20 * 1000;
    public static final long DEFAULT_WRITE_TIMEOUT = 20 * 1000;

    /**
     * 默认okhttp构造器
     */
    public static final OkHttpClient.Builder DEFAULT_OKHTTPBUILDER = new OkHttpClient.Builder()
            .sslSocketFactory(HTTPSTrustManager.getSSLsocketFactory())
            .connectTimeout(DEFAULT_CONNET_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS);

    /**
     * 默认LOG拦截器
     */
    public static final LoggingInterceptor DEFAULT_LOGGINGINTERCEPTOR = new LoggingInterceptor();


}
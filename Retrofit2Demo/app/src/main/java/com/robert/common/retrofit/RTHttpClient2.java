package com.robert.common.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retrofit2 HTTP 客户端调用类.
 * Created by robert on 16/03/16.
 */
public class RTHttpClient2 {
    public static Retrofit restAdapter = null;
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

    /**
     * 注意 okhttp3的OkHttpClien通过build方式构建,
     * 一旦创建,要修改只能rebuild
     */
    public static OkHttpClient okHttpClient;

    /**
     * cookie存储
     */
    public static Okhttp3CookieManager cookiejar;

    /**
     * 初始化RetrofitHttpClient
     *
     * @param _baseURL 基础地址
     */
    public static void init(String _baseURL, OkHttpClient okHttpClient) {
        if (null != restAdapter) {
            restAdapter = null;
        }
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(_baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);
        restAdapter = builder.build();
    }


    public static <T> T create(Class<T> service) {
        return restAdapter.create(service);
    }


}
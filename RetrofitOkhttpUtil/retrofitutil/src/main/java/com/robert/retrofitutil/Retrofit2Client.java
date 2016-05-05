package com.robert.retrofitutil;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit2 HTTP 客户端调用类.
 * Created by robert on 16/03/16.
 */
public class Retrofit2Client {
    public static Retrofit restAdapter = null;

    /**
     * 注意 okhttp3的OkHttpClien通过build方式构建,
     * 一旦创建,要修改只能rebuild
     */
    public static OkHttpClient okHttpClient;


    /**
     * 初始化RetrofitHttpClient
     *
     * @param _baseURL 基础地址
     */
    public static void init(String _baseURL, OkHttpClient okHttpClient) {
        if (null != restAdapter) {
            restAdapter = null;
        }
        Retrofit2Client.okHttpClient = okHttpClient;
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
package com.robert.okhttputil.interceptor;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

/**
 * Created by robert on 16/3/16.
 * okhttp日志拦截器
 */
public class LoggingInterceptor implements Interceptor {
    private static final String okhttptag = "okhttplog";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();

        Log.i(okhttptag, String.format("Sending request %s on %s%n%s",
                request.url(), chain.connection(), request.headers()));
        try {
            if (request.body() != null && request.body().contentLength() < 1024 * 100) {
                Buffer buffer = new Buffer();
                request.body().writeTo(buffer);
                Log.i(okhttptag, "okhttp body: " + buffer.readUtf8());
            }
        } catch (Exception e) {
            Log.e(okhttptag, "" + e.getMessage());
        }
        Response response = chain.proceed(request);

        long t2 = System.nanoTime();
        Log.i(okhttptag, String.format("Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));

        return response;
    }
}
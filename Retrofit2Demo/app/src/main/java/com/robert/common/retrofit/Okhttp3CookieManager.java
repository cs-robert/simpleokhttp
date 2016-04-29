package com.robert.common.retrofit;

import android.content.Context;

import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by robert on 16/3/16.
 * okhttp3 cooke管理
 */
public class Okhttp3CookieManager implements CookieJar {

    private Okhttp3PersistentCookieStore cookiestore;


    public Okhttp3CookieManager(Context context) {
        this.cookiestore = new Okhttp3PersistentCookieStore(context);
    }

    public Okhttp3PersistentCookieStore getCookieStore() {
        return cookiestore;
    }

    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookiestore.addAll(url.uri(), cookies);
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return cookiestore.getCookies();
    }
}

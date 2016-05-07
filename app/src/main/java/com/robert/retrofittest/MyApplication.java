package com.robert.retrofittest;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by robert on 16/5/5.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}

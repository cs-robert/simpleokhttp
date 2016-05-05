package com.robert.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.robert.okhttputil.DefaultOkhttp3Cofing;
import com.robert.okhttputil.cookie.Okhttp3CookieManager;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by robert on 16/5/5.
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.url)
    EditText eturl;
    @Bind(R.id.result)
    TextView tvresult;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        okHttpClient = DefaultOkhttp3Cofing.DEFAULT_OKHTTPBUILDER.addNetworkInterceptor(new StethoInterceptor()).cookieJar(new Okhttp3CookieManager(this)).build();
        tvresult.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @OnClick(R.id.request)
    protected void onClick(View v) {

        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        String url = eturl.getText().toString();
                        if (TextUtils.isEmpty(url)) {
                            url = "https://github.com/";
                        }
                        Request request = new Request.Builder().url(url).build();
                        Call call = okHttpClient.newCall(request);
                        String result = "";
                        try {
                            Response response = call.execute();
                            result = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                            result = "" + e.getMessage();
                        }
                        sub.onNext(result);
                        sub.onCompleted();
                    }
                }
        );
        myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        return "result: " + s;
                    }
                })
                .observeOn(Schedulers.io()).subscribe(
                new Observer<Object>() {
                    @Override
                    public void onNext(Object o) {
                        tvresult.setText(o.toString());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
}

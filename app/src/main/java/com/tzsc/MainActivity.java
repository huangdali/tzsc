package com.tzsc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hdl.elog.ELog;
import com.httplib.http.HttpSend;
import com.httplib.model.HttpResult;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogin(View view) {
        HttpSend.getInstance().login("admin", "132", new Observer<HttpResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                ELog.e(d);
            }

            @Override
            public void onNext(HttpResult value) {
                ELog.e(value);
            }

            @Override
            public void onError(Throwable e) {
                ELog.e(e);
            }

            @Override
            public void onComplete() {
                ELog.e("onComplete");
            }
        });
    }

    public void onLogout(View view) {
        HttpSend.getInstance().logout(new Observer<HttpResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                ELog.e(d);
            }

            @Override
            public void onNext(HttpResult value) {
                ELog.e(value);
            }

            @Override
            public void onError(Throwable e) {
                ELog.e(e);
            }

            @Override
            public void onComplete() {
                ELog.e("onComplete");
            }
        });
    }

    public void onLoadGoods(View view) {
        HttpSend.getInstance().logout(new Observer<HttpResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                ELog.e(d);
            }

            @Override
            public void onNext(HttpResult value) {
                ELog.e(value);
            }

            @Override
            public void onError(Throwable e) {
                ELog.e(e);
            }

            @Override
            public void onComplete() {
                ELog.e("onComplete");
            }
        });
    }
}

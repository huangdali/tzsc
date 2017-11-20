package com.tzsc.ui;

import android.view.View;

import com.hdl.elog.ELog;
import com.httplib.http.HttpSend;
import com.httplib.model.HttpResult;
import com.tzsc.R;
import com.tzsc.base.BaseActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {


    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        findViewById(R.id.tv_login).setOnClickListener(this);
    }

    @Override
    public void initData() {

    }
    public void onLogin() {
        HttpSend.getInstance().login("15519099928", "123456", new Observer<HttpResult>() {
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                onLogin();
                break;
        }
    }
}

package com.tzsc.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.hdl.elog.ELog;
import com.httplib.http.HttpSend;
import com.httplib.model.HttpResult;
import com.httplib.utils.HttpSpUtils;
import com.tzsc.R;
import com.tzsc.base.BaseActivity;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends BaseActivity {

    /**
     * 设置标题
     *
     * @return
     */
    @Override
    public CharSequence setTitleText() {
        return "主页";
    }

    @Override
    public boolean isNeedBack() {
        return true;
    }

    @Override
    public void onBack() {
        Toast.makeText(mContext, "点击返回了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getLayoutResId() {
        //判断是否登录
        if (!HttpSpUtils.getInstance().isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return R.layout.activity_main;
    }


    @Override
    public void initData() {

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

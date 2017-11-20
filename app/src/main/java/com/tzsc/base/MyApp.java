package com.tzsc.base;

import android.app.Application;

import com.httplib.http.HttpSend;

/**
 * Created by HDL on 2017/11/20.
 *
 * @author HDL
 */

public class MyApp extends Application {
    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        //初始化网络请求库
        HttpSend.getInstance().initContext(app);
    }
}

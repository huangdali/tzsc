package com.httplib.http;

import android.content.Context;

import com.httplib.model.HttpResult;
import com.httplib.utils.HttpInterceptor;
import com.httplib.utils.HttpSpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * 网络发送器
 * Created by HDL on 2017/11/20.
 *
 * @author HDL
 */

public class HttpSend {
    private static HttpSend mHttpSend;
    private ApiService apiService;

    private HttpSend() {
    }

    public static HttpSend getInstance() {
        if (mHttpSend == null) {
            synchronized (HttpSend.class) {
                if (mHttpSend == null) {
                    mHttpSend = new HttpSend();
                }
            }
        }
        return mHttpSend;
    }

    /**
     * 初始化上下文对象
     *
     * @param context
     */
    public void initContext(Context context) {
        HttpConfiger.getInstance().initContext(context.getApplicationContext());
        HttpSpUtils.getInstance().initContext(context.getApplicationContext());
        apiService = HttpConfiger.getInstance().getRetrofit().create(ApiService.class);
    }

    /**
     * 登录
     *
     * @param userName   用户名
     * @param pwd        密码
     * @param subscriber 请求进度回调
     */
    public void login(String userName, String pwd, Observer<HttpResult> subscriber) {
        Observable<HttpResult> login = apiService.login(userName, pwd)
                .map(new HttpInterceptor<>(HttpInterceptor.TYPE_LOGIN));//拦截网络请求
        HttpConfiger.getInstance().toSubscribe(login).subscribe(subscriber);
    }

    /**
     * 退出登录
     *
     * @param subscriber
     */
    public void logout(Observer<HttpResult> subscriber) {
        Observable<HttpResult> login = apiService.logout()
                .map(new HttpInterceptor<>(HttpInterceptor.TYPE_LOGOUT));//拦截网络请求
        HttpConfiger.getInstance().toSubscribe(login).subscribe(subscriber);
    }

    /**
     * 加载首页商品数据
     *
     * @param pSize      第几页
     * @param pCount     每页有多少个
     * @param subscriber
     */
    public void loadGoods(int pSize, int pCount, Observer<HttpResult> subscriber) {
        Observable<HttpResult> login = apiService.loadGoods(pSize, pCount)
                .map(new HttpInterceptor<>(HttpInterceptor.TYPE_NORMAL));//拦截网络请求
        HttpConfiger.getInstance().toSubscribe(login).subscribe(subscriber);
    }
}

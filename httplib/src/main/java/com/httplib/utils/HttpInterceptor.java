package com.httplib.utils;

import com.hdl.elog.ELog;
import com.httplib.base.HttpCode;
import com.httplib.model.HttpResult;
import com.httplib.model.UserInfoVo;

import io.reactivex.functions.Function;

/**
 * 请求结果拦截，主要拦截登录和退出两个动作
 *
 * @author HDL
 */
public class HttpInterceptor<T extends HttpResult> implements Function<T, T> {
    /**
     * api类型，以下三个枚举值
     */
    private int apiType;
    /**
     * 正常接口(除了登录、注销以外的接口)
     */
    public static final int TYPE_NORMAL = 0;
    /**
     * 登录接口
     */
    public static final int TYPE_LOGIN = 1;
    /**
     * 注销接口
     */
    public static final int TYPE_LOGOUT = 2;

    public HttpInterceptor(int apiType) {
        this.apiType = apiType;
    }

    @Override
    public T apply(T t) throws Exception {
        if (apiType == TYPE_LOGIN && t.getCode() == HttpCode.CODE_0) {
            //如果是登录则拦截存储用户信息  登录标记变为true
            ELog.e("保存用户信息");
            HttpResult<UserInfoVo> result = t;
            UserInfoVo data = result.getData();
            HttpSpUtils.getInstance().saveToken(data.getToken());
            HttpSpUtils.getInstance().saveUId(String.valueOf(data.getuId()));
            HttpSpUtils.getInstance().saveIsLogin(true);
        } else if (apiType == TYPE_LOGOUT && t.getCode() == HttpCode.CODE_0) {
            //如果是注销登录标记变为false
            HttpSpUtils.getInstance().saveIsLogin(false);
        }
        return t;
    }
}

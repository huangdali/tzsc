package com.httplib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * http库的sp管理工具
 * Created by HDL on 2017/11/20.
 *
 * @author HDL
 */

public class HttpSpUtils {
    /**
     * 使用单例模式，因为很多地方会频繁用到此类，比如获取token
     */
    private static HttpSpUtils mHttpSpUtils;
    /**
     * 文件名
     */
    private static final String KEY_FILENAME = "account";
    /**
     * 是否登录
     */
    private static final String KEY_ISLOGIN = "isLogin";
    /**
     * token
     */
    private static final String KEY_TOKEN = "token";
    /**
     * 用户id
     */
    private static final String KEY_UID = "uId";
    /**
     * 上下文对象，建议传入的时候使用context.getApplicationContext()防止内存泄露
     */
    private Context context;

    private HttpSpUtils() {
    }

    public static HttpSpUtils getInstance() {
        if (mHttpSpUtils == null) {
            synchronized (HttpSpUtils.class) {
                if (mHttpSpUtils == null) {
                    mHttpSpUtils = new HttpSpUtils();
                }
            }
        }
        return mHttpSpUtils;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void initContext(Context context) {
        this.context = context;
    }

    /**
     * 保存是否登录
     *
     * @param isLogin 是否已经登录
     */
    public void saveIsLogin(boolean isLogin) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE);
            sp.edit().putBoolean(KEY_ISLOGIN, isLogin).apply();
        }
    }

    /**
     * 是否已经登录过
     *
     * @return
     */
    public boolean isLogin() {
        if (context != null) {
            return context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE).getBoolean(KEY_ISLOGIN, false);
        }
        return false;
    }

    /**
     * 保存token
     *
     * @param token
     */
    public void saveToken(String token) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE);
            sp.edit().putString(KEY_TOKEN, token).apply();
        }
    }

    /**
     * 获取token
     *
     * @return
     */
    public String getToken() {
        if (context != null) {
            return context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE).getString(KEY_TOKEN, "");
        }
        return "";

    }

    /**
     * 保存用户id
     *
     * @param uId
     */
    public void saveUId(String uId) {
        if (context != null) {
            SharedPreferences sp = context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE);
            sp.edit().putString(KEY_UID, uId).apply();
        }
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public String getUId() {
        if (context != null) {
            return context.getSharedPreferences(KEY_FILENAME, Context.MODE_PRIVATE).getString(KEY_UID, "");
        }
        return "";
    }

}

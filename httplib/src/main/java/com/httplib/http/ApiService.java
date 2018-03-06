package com.httplib.http;

import com.httplib.model.GoodsVo;
import com.httplib.model.HttpResult;
import com.httplib.model.UserInfoVo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 所有的web接口都在此
 *
 * @author HDL
 */
public interface ApiService {
    /**
     * 用户登录
     *
     * @param userName 用户名
     * @param pwd      密码
     * @return 登录结果
     */
    @POST("api/login.action")
    Observable<HttpResult<UserInfoVo>> login(@Query("userName") String userName, @Query("pwd") String pwd);

    /**
     * 退出登录
     *
     * @return
     */
    @POST("api/logout.action")
    Observable<HttpResult> logout();

    /**
     * 查询首页商品数据
     *
     * @param pSize  第几页
     * @param pCount 每页有多少个
     * @return
     */
    @POST("api/queryAll.action")
    Observable<HttpResult<List<GoodsVo>>> loadGoods(@Query("pSize") int pSize, @Query("pCount") int pCount);
}

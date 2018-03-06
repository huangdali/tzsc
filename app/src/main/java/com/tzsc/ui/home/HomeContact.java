package com.tzsc.ui.home;

import com.httplib.base.ResultCallbackListener;
import com.httplib.model.GoodsVo;
import com.httplib.model.HttpResult;
import com.tzsc.base.BasePresenter;
import com.tzsc.base.BaseView;

import java.util.List;

/**
 * 首页mvp接口管理类
 * Created by HDL on 2018/3/6.
 *
 * @author HDL
 */

public class HomeContact {
    public interface IHomeModel {
        void loadNewsGoods(int page, int size, ResultCallbackListener<HttpResult<List<GoodsVo>>> resultCallback);
    }

    public interface IHomeView extends BaseView{
        void showNewsGoods(List<GoodsVo> data);
    }

    public interface IHomePresenter extends BasePresenter{
        void loadNewsGoods(int page, int size);
    }
}

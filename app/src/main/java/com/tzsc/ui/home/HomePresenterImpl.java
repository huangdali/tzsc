package com.tzsc.ui.home;

import com.hdl.elog.ELog;
import com.httplib.base.HttpCode;
import com.httplib.base.ResultCallbackListener;
import com.httplib.model.GoodsVo;
import com.httplib.model.HttpResult;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by HDL on 2018/3/6.
 *
 * @author HDL
 */

public class HomePresenterImpl implements HomeContact.IHomePresenter {
    private HomeContact.IHomeView view;
    private HomeContact.IHomeModel model;

    public HomePresenterImpl(HomeContact.IHomeView view) {
        this.view = view;
        model = new HomeModelImpl();
    }

    @Override
    public void loadNewsGoods(int page, int size) {
        view.showLoading();
        model.loadNewsGoods(page, size, new ResultCallbackListener<HttpResult<List<GoodsVo>>>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HttpResult<List<GoodsVo>> value) {
                ELog.e(value);
                if (value.getCode() == HttpCode.CODE_0) {
                    view.showNewsGoods(value.getData());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                view.closeLoading();
            }
        });
    }

    @Override
    public void onDestroy() {
        view = null;
        System.gc();
    }
}

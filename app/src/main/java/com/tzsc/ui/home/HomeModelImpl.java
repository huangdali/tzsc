package com.tzsc.ui.home;

import com.httplib.base.ResultCallbackListener;
import com.httplib.http.HttpSend;
import com.httplib.model.GoodsVo;
import com.httplib.model.HttpResult;

import java.util.List;

/**
 * Created by HDL on 2018/3/6.
 *
 * @author HDL
 */

public class HomeModelImpl implements HomeContact.IHomeModel {

    @Override
    public void loadNewsGoods(int page, int size, ResultCallbackListener<HttpResult<List<GoodsVo>>> resultCallback) {
        HttpSend.getInstance().loadGoods(page, size, resultCallback);
    }
}

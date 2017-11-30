package com.tzsc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * 实现mvp模式的公共activity类，所有使用mvp的activity页面都需要继承此类
 * <p>
 * 温馨提示：
 * 1、实际开发中需要将BaseMvpActivity继承的AppCompatActivity改成你自己的BaseActivity（如果有）
 * 2、如果页面再进去其他页面之后不需要了，一定要及时finish
 * <p>
 * Created by HDL on 2017/11/30.
 *
 * @author HDL
 */

public abstract class BaseMvpActivity extends BaseActivity {
    private BasePresenter presenter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = bindPresenter();
        super.onCreate(savedInstanceState);
    }
    /**
     * 绑定presenter，主要用于销毁工作
     *
     * @return
     */
    protected abstract BasePresenter bindPresenter();

    /**
     * 如果重写了此方法，一定要调用super.onDestroy();
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }
}
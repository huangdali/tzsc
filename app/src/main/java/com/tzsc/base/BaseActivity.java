package com.tzsc.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tzsc.R;
import com.tzsc.widget.TitleBarView;

/**
 * 公共基类
 * Created by HDL on 2017/11/20.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public Context mContext;
    private TitleBarView tbTitle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mContext = this;
        if (isShowTitleBar()) {
            tbTitle = (TitleBarView) findViewById(R.id.tb_title);
            tbTitle.setTitle(setTitleText());
            tbTitle.setTitleColor(Color.WHITE);
            if (isNeedBack()) {
                tbTitle.setLeftImageResource(R.mipmap.ic_back);
                tbTitle.setLeftClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onBack();
                    }
                });
            }
        }
        initView();
        initData();
    }

    /**
     * 当点击返回的时候回调，默认是销毁当前页面，如需特殊处理返回放牛就重写此方法并且不要调用super.onBack
     */
    public void onBack() {
        finish();
    }

    /**
     * 是否需要返回按钮,默认不需要
     *
     * @return
     */
    public boolean isNeedBack() {
        return false;
    }

    /**
     * 设置标题
     *
     * @return
     */
    public abstract CharSequence setTitleText();


    /**
     * 返回资源的布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 组件初始化操作
     */
    public abstract void initView();

    /**
     * 页面初始化页面数据，在initView之后调用
     */
    public abstract void initData();

    /**
     * 是否需要显示标题栏，默认显示
     *
     * @return
     */
    public boolean isShowTitleBar() {
        return true;
    }
}

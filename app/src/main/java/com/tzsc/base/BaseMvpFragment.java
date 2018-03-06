package com.tzsc.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HDL on 2017/11/30.
 *
 * @author HDL
 */
public abstract class BaseMvpFragment extends Fragment {
    private BasePresenter presenter = null;
    public Context mContext;
    private Unbinder unbinder;
    private LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        View view = View.inflate(mContext, getLayoutResId(), null);
        unbinder = ButterKnife.bind(this, view);
        presenter = bindPresenter();
        initView(view);
        initData();
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.setLoadingText("加载中...");//设置loading时显示的文字
        return view;
    }

    /**
     * 显示loading
     */
    public void showLoading() {
        if (loadingDialog != null) {
            loadingDialog.show();
        }
    }

    /**
     * 关闭loading
     */
    public void closeLoading() {
        if (loadingDialog != null) {
            loadingDialog.close();
        }
    }

    /**
     * 返回资源的布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 组件初始化操作,使用butterknif了，一般不用重写此方法，特殊需求时重写
     *
     * @param view 父view
     */
    public void initView(View view) {
    }

    /**
     * 页面初始化页面数据，在initView之后调用
     */
    public abstract void initData();

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
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }
}
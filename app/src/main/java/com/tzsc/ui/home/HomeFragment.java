package com.tzsc.ui.home;

import android.widget.TextView;

import com.hdl.elog.ELog;
import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;

import butterknife.BindView;

public class HomeFragment extends BaseMvpFragment {
    @BindView(R.id.tv_test)
    TextView tvTest;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        ELog.e("初始化了");
        tvTest.setText("我是bindview");
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}

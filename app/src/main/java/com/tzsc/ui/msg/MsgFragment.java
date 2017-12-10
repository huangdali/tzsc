package com.tzsc.ui.msg;

import android.graphics.Color;

import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.widget.TitleBarView;

import butterknife.BindView;

public class MsgFragment extends BaseMvpFragment {
    @BindView(R.id.tb_title)
    TitleBarView tvTitle;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_msg;
    }

    @Override
    public void initData() {
        tvTitle.setTitle("消息");
        tvTitle.setLeftVisible(false);
        tvTitle.setTitleColor(Color.WHITE);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}

package com.tzsc.ui.my;

import android.graphics.Color;
import android.widget.ImageView;

import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.utils.Glider;
import com.tzsc.widget.TitleBarView;

import butterknife.BindView;

public class MyFragment extends BaseMvpFragment {
    @BindView(R.id.tb_title)
    TitleBarView tvTitle;

    @BindView(R.id.iv_head)
    ImageView ivHead;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initData() {
        tvTitle.setTitle("我的");
        tvTitle.setLeftVisible(false);
        tvTitle.setTitleColor(Color.WHITE);
        Glider.getInstance().loadHead("",ivHead);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}

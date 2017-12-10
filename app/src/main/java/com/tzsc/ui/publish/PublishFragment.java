package com.tzsc.ui.publish;

import android.graphics.Color;

import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.widget.TitleBarView;

import butterknife.BindView;

public class PublishFragment extends BaseMvpFragment {
    @BindView(R.id.tb_title)
    TitleBarView tvTitle;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_publish;
    }

    @Override
    public void initData() {
        tvTitle.setTitle("发布");
        tvTitle.setLeftVisible(false);
        tvTitle.setTitleColor(Color.WHITE);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}

package com.tzsc.ui.my;

import android.content.Intent;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.ImageView;

import com.httplib.utils.HttpSpUtils;
import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.ui.login.LoginActivity;
import com.tzsc.ui.msg.ChatActivity;
import com.tzsc.utils.Glider;
import com.tzsc.widget.TitleBarView;

import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BaseMvpFragment {
    @BindView(R.id.tb_title)
    TitleBarView tvTitle;

    @BindView(R.id.iv_head)
    ImageView ivHead;

    @BindView(R.id.et_username)
    EditText etUsername;

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
    @OnClick(R.id.iv_head)
    public void onClear(){
        HttpSpUtils.getInstance().saveIsLogin(false);
        startActivity(new Intent(mContext, LoginActivity.class));
    }
    @OnClick(R.id.btn_chat)
    public void chat(){
        String username = etUsername.getText().toString().trim();
        ChatActivity.startActivity(mContext, username, null);
    }
}

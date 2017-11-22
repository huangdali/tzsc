package com.tzsc.ui;

import android.widget.EditText;

import com.tzsc.R;
import com.tzsc.base.BaseActivity;

import butterknife.BindView;

/**
 * 用户注册页面
 * <p>
 * 为了简化注册流程，用户头像、昵称、性别可到个人中心修改
 *
 * @author HDL
 */
public class RegisterActivity extends BaseActivity {
    /**
     * 手机号码
     */
    @BindView(R.id.et_register_phone)
    EditText etPhone;
    /**
     * 密码
     */
    @BindView(R.id.et_register_pwd)
    EditText etPwd;
    /**
     * 再次确认密码
     */
    @BindView(R.id.et_register_repwd)
    EditText etRePwd;
    /**
     * 学号
     */
    @BindView(R.id.et_register_no)
    EditText etNo;

    /**
     * 需要返回按钮
     *
     * @return
     */
    @Override
    public boolean isNeedBack() {
        return true;
    }

    @Override
    public CharSequence setTitleText() {
        return "注册";
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {

    }

}

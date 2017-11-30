package com.tzsc.ui.register;

import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.tzsc.R;
import com.tzsc.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.cpb_register)
    CircularProgressButton cpbProgress;

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

    @OnClick(R.id.cpb_register)
    public void onRegister() {
        cpbProgress.setIndeterminateProgressMode(true); // turn on indeterminate progress
        cpbProgress.setProgress(50); // set progress > 0 & < 100 to display indeterminate progress
        cpbProgress.setProgress(99); // set progress to 100 or -1 to indicate complete or error state
        cpbProgress.setProgress(0); // set progress to 0 to switch back to normal state
        cpbProgress.setProgress(50); // set progress > 0 & < 100 to display indeterminate progress
        cpbProgress.setProgress(99); // set progress to 100 or -1 to indicate complete or error state
    }

}

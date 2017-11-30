package com.tzsc.ui.login;

import android.content.Intent;
import android.graphics.Color;

import com.dd.CircularProgressButton;
import com.hdl.elog.ELog;
import com.httplib.http.HttpSend;
import com.httplib.model.HttpResult;
import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.ui.register.RegisterActivity;
import com.tzsc.utils.SystemStatusManager;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.btnWithText)
     CircularProgressButton cpbProgress;

    /**
     * 设置标题
     *
     * @return
     */
    @Override
    public CharSequence setTitleText() {
        return "登录";
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }


    @Override
    public void initData() {
        new SystemStatusManager(this).setTranslucentStatus(R.color.black);
        tbTitle.setTitleColor(Color.GRAY);
    }

    public void onLogin() {
        HttpSend.getInstance().login("15519099928", "123456", new Observer<HttpResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                ELog.e(d);
            }

            @Override
            public void onNext(HttpResult value) {
                ELog.e(value);
            }

            @Override
            public void onError(Throwable e) {
                ELog.e(e);
            }

            @Override
            public void onComplete() {
                ELog.e("onComplete");
            }
        });
    }


    @OnClick(R.id.btnWithText)
    public void onLoginServer() {
        cpbProgress.setIndeterminateProgressMode(true); // turn on indeterminate progress
        cpbProgress.setProgress(50); // set progress > 0 & < 100 to display indeterminate progress
        cpbProgress.setProgress(99); // set progress to 100 or -1 to indicate complete or error state
        cpbProgress.setProgress(0); // set progress to 0 to switch back to normal state
        cpbProgress.setProgress(50); // set progress > 0 & < 100 to display indeterminate progress
        cpbProgress.setProgress(99); // set progress to 100 or -1 to indicate complete or error state
    }


    @OnClick(R.id.tv_login_register)
    public void toRegister() {
        startActivity(new Intent(mContext, RegisterActivity.class));
    }
}

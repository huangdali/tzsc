package com.tzsc.ui.login;

import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.widget.EditText;

import com.dd.CircularProgressButton;
import com.hdl.elog.ELog;
import com.httplib.http.HttpSend;
import com.httplib.model.HttpResult;
import com.httplib.model.UserInfoVo;
import com.httplib.utils.HttpSpUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.cache.UserCacheInfo;
import com.hyphenate.easeui.cache.UserCacheManager;
import com.hyphenate.exceptions.HyphenateException;
import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.base.GlobelContact;
import com.tzsc.ui.MainActivity;
import com.tzsc.ui.register.RegisterActivity;
import com.tzsc.utils.SystemStatusManager;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.btnWithText)
    CircularProgressButton cpbProgress;
    @BindView(R.id.et_login_username)
    EditText etUsername;

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
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setuId(19);
        userInfoVo.setToken("023759817239");
        userInfoVo.setuPhone(etUsername.getText().toString().trim());
        userInfoVo.setuNickName("大力哥");
        userInfoVo.setuPwd(GlobelContact.KEY_HX_USER_PWD);
        loginHX(userInfoVo);
    }

    private void loginHX(final UserInfoVo userInfoVo) {
        //将这个token注册到环信  环信用户的ID 是 token  密码是用户的密码
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //注册失败会抛出HyphenateException
                    EMClient.getInstance().createAccount(userInfoVo.getuPhone(), userInfoVo.getuPwd());//同步方法
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    ELog.e("注册失败");
                }
                //注册成功了，进行登录
                ELog.e("环信账号=" + userInfoVo.getuPhone());
                ELog.e("环信密码=" + userInfoVo.getuPwd());
                EMClient.getInstance().login(userInfoVo.getuPhone(), userInfoVo.getuPwd(), new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        //获取对话列表
                        EMClient.getInstance().chatManager().loadAllConversations();
                        ELog.e("main", "登录聊天服务器成功！");
                        // 登录成功，将用户的环信ID、昵称和头像缓存在本地
                        String nickname = userInfoVo.getuNickName();
                        if (TextUtils.isEmpty(nickname)) {
                            nickname = userInfoVo.getuPhone();
                        }
                        String head_pic = userInfoVo.getHeadurl();
                        if (TextUtils.isEmpty(head_pic)) {
                            head_pic = "";
                        }
                        UserCacheManager.save(userInfoVo.getuPhone(), nickname, head_pic);
                        UserCacheInfo userCacheInfo = UserCacheManager.get(userInfoVo.getuPhone());
                        if (userCacheInfo != null) {
                            ELog.e("登录环信成功，打印缓存数据=" + userCacheInfo.getNickName() + ";user>>>>>>" + userCacheInfo.getAvatarUrl());
                        }
//                        hideLoadding();
//                        MainV2Activity.startActivity(mContext, 0);
                        HttpSpUtils.getInstance().saveIsLogin(true);
                        startActivity(new Intent(mContext, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        ELog.e("main", "登录聊天服务器失败！code=" + code + ";msg=" + message);
                        showMsg("登录失败了");
                        HttpSpUtils.getInstance().saveIsLogin(false);
//                        showToast("登录失败");
//                        hideLoadding();
                    }
                });
            }
        }).start();
    }


    @OnClick(R.id.tv_login_register)
    public void toRegister() {
        startActivity(new Intent(mContext, RegisterActivity.class));
    }
}

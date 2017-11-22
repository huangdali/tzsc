package com.tzsc.ui;

import android.content.Intent;

import com.tzsc.R;
import com.tzsc.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Logo页面
 * Created by HDL on 2017/11/20.
 */

public class LogoActivity extends BaseActivity {
    private static final int WHAT_DURATION = 2000;


    @Override
    public boolean isShowTitleBar() {
        return false;
    }

    /**
     * 设置标题
     *
     * @return
     */
    @Override
    public CharSequence setTitleText() {
        return null;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_logo;
    }


    @Override
    public void initData() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(mContext, MainActivity.class));
                        finish();
                    }
                });
            }
        }, WHAT_DURATION);
    }

}

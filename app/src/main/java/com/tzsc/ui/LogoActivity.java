package com.tzsc.ui;

import android.content.Intent;
import android.view.View;

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
    public int getLayoutResId() {
        return R.layout.activity_logo;
    }

    @Override
    public void initView() {
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

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}

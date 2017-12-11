package com.tzsc.ui.publish;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.widget.ImageView;

import com.tzsc.R;
import com.tzsc.base.BaseMvpFragment;
import com.tzsc.base.BasePresenter;
import com.tzsc.widget.TitleBarView;

import butterknife.BindView;

public class PublishFragment extends BaseMvpFragment {
    @BindView(R.id.tb_title)
    TitleBarView tvTitle;

    @BindView(R.id.iv_flea)
    ImageView ivFlea;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_publish;
    }

    private PropertyValuesHolder scaleX;
    private PropertyValuesHolder scaleY;
    private ObjectAnimator animator;

    @Override
    public void initData() {
        tvTitle.setTitle("发布");
        tvTitle.setLeftVisible(false);
        tvTitle.setTitleColor(Color.WHITE);
        scaleX = PropertyValuesHolder.ofFloat("ScaleX", 0, 1);
        scaleY = PropertyValuesHolder.ofFloat("ScaleY", 0, 1);
        animator = ObjectAnimator.ofPropertyValuesHolder(ivFlea, scaleX, scaleY);
        animator.setDuration(1000);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            //相当于Fragment的onPause
        }else{
            //相当于Fragment的onResume
            if (animator != null) {
                animator.start();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
//        ELog.e("可见了");
        if (animator != null) {
            animator.start();
        }
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

}

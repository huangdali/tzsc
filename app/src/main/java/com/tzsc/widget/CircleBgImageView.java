package com.tzsc.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;

import com.tzsc.R;

/**
 * 圆形背景的圆
 * Created by HDL on 2017/9/18.
 */

public class CircleBgImageView extends android.support.v7.widget.AppCompatImageView {
    private boolean isChecked = false;
    private ObjectAnimator objectAnimatorAdd = ObjectAnimator.ofFloat(this, "Rotation", 0, 135);
    private ObjectAnimator objectAnimatorSub = ObjectAnimator.ofFloat(this, "Rotation", 135, 0);

    public CircleBgImageView(Context context) {
        this(context, null);
    }

    public CircleBgImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleBgImageView(final Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setPadding(10, 10, 10, 10);
        this.setBackgroundResource(R.drawable.shape_home_publish);
        objectAnimatorAdd.setDuration(300);
        objectAnimatorSub.setDuration(300);
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isChecked = !isChecked;
//                updateState(isChecked);
//            }
//        });
    }

    /**
     * 更新选中状态
     *
     * @param isChecked
     */
    private void updateState(boolean isChecked) {
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onChange(isChecked);
        }
        if (isChecked) {
            objectAnimatorAdd.start();
        } else {
            objectAnimatorSub.start();
        }
    }

    private OnCheckedChangeListener onCheckedChangeListener;

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    public interface OnCheckedChangeListener {
        void onChange(boolean isChecked);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        updateState(isChecked);
    }
}

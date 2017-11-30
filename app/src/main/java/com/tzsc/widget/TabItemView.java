package com.tzsc.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tzsc.R;

/**
 * 首页tab子项
 * Created by HDL on 2017/9/18.
 */

public class TabItemView extends LinearLayout {
    private Context mContext;
    private String text;
    private Drawable src;
    private Drawable src_press;
    private boolean isChecked = false;
    private ImageView ivIcon;
    private TextView tvTitle;
    private int textColor = 0xff4e4e4e;
    private int textColor_press = 0xff049bf9;
    private OnCheckedOnChangeListener onCheckedOnChangeListener;

    public TabItemView(Context context) {
        this(context, null);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
        this.setGravity(Gravity.CENTER);
        initAttr(attrs, defStyle);
        View view = View.inflate(context, R.layout.tab_item, null);
//        view.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                isChecked = !isChecked;
//                setChecked(isChecked);
//                if (onCheckedOnChangeListener != null) {
//                    onCheckedOnChangeListener.onChecked(isChecked);
//                }
//            }
//        });
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvTitle.setText(text);
        setChecked(isChecked);
        this.addView(view);
    }

    public void setChecked(boolean isChecked) {
        if (isChecked) {
            ivIcon.setImageDrawable(src_press);
            tvTitle.setTextColor(textColor_press);
        } else {
            ivIcon.setImageDrawable(src);
            tvTitle.setTextColor(textColor);
        }
    }

    public boolean isChecked() {
        return isChecked;
    }

    /**
     * 初始化属性
     *
     * @param attrs
     * @param defStyle
     */
    private void initAttr(AttributeSet attrs, int defStyle) {
        TypedArray ta = mContext.getTheme().obtainStyledAttributes(attrs, R.styleable.TabItemView, defStyle, 0);
        text = ta.getString(R.styleable.TabItemView_text);
        src = ta.getDrawable(R.styleable.TabItemView_src);
        src_press = ta.getDrawable(R.styleable.TabItemView_src_press);
        isChecked = ta.getBoolean(R.styleable.TabItemView_checked, false);
        textColor = ta.getColor(R.styleable.TabItemView_textColor, textColor);
        textColor_press = ta.getColor(R.styleable.TabItemView_textColor_press, textColor_press);
        ta.recycle();//及时释放
    }

    public void setOnCheckedOnChangeListener(OnCheckedOnChangeListener onCheckedOnChangeListener) {
        this.onCheckedOnChangeListener = onCheckedOnChangeListener;
    }

    public interface OnCheckedOnChangeListener {
        void onChecked(boolean isChecked);
    }
}

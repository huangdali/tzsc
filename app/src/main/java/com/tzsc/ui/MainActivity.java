package com.tzsc.ui;

import android.util.Log;
import android.widget.Toast;

import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.widget.CircleBgImageView;
import com.tzsc.widget.TabItemView;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.iv_publish)
    CircleBgImageView ivPublish;

    @BindView(R.id.tbv_home)
    TabItemView tabHome;

    @BindView(R.id.tbv_classify)
    TabItemView tabClassify;

    @BindView(R.id.tbv_msg)
    TabItemView tabMsg;

    @BindView(R.id.tbv_my)
    TabItemView tabMy;

    private TabItemView tabs[] = new TabItemView[4];

    /**
     * 设置标题
     *
     * @return
     */
    @Override
    public CharSequence setTitleText() {
        return "";
    }

    /**
     * 不需要显示标题栏
     *
     * @return
     */
    @Override
    public boolean isShowTitleBar() {
        return false;
    }

    @Override
    public void onBack() {
        Toast.makeText(mContext, "点击返回了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getLayoutResId() {
//        //判断是否登录
//        if (!HttpSpUtils.getInstance().isLogin()) {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
        return R.layout.activity_main;
    }


    @Override
    public void initData() {
        tabs[0]=tabHome;
        tabs[1]=tabClassify;
        tabs[2]=tabMsg;
        tabs[3]=tabMy;
        ivPublish.setOnCheckedChangeListener(new CircleBgImageView.OnCheckedChangeListener() {
            @Override
            public void onChange(boolean isChecked) {
                Toast.makeText(mContext, isChecked ? "点击发布" : "取消发布", Toast.LENGTH_SHORT).show();
                updateCheck(4);
            }
        });
    }

    @OnClick(R.id.tbv_home)
    public void toHome() {
        updateCheck(0);
    }

    @OnClick(R.id.tbv_classify)
    public void toClassify() {
        updateCheck(1);
    }

    @OnClick(R.id.tbv_msg)
    public void toMsg() {
        updateCheck(2);
    }

    @OnClick(R.id.tbv_my)
    public void toMy() {
        updateCheck(3);
    }

    public void updateCheck(int index) {
        /**
         * 自己被选中
         */
        if (index < tabs.length) {
            Log.e("test", "updateCheck: 第几个设置为选中：" + index);
            tabs[index].setChecked(true);
        }
        for (int i = 0; i < tabs.length; i++) {
            if (i != index) {
                Log.e("test", "updateCheck: 设置不选中：" + i);
                tabs[i].setChecked(false);
            }
        }
    }
}

package com.tzsc.ui;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.ui.classify.ClassifyFragment;
import com.tzsc.ui.home.HomeFragment;
import com.tzsc.ui.msg.MsgFragment;
import com.tzsc.ui.my.MyFragment;
import com.tzsc.ui.publish.PublishFragment;
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
    private HomeFragment homeFragment = new HomeFragment();
    private ClassifyFragment classifyFragment = new ClassifyFragment();
    private PublishFragment publishFragment = new PublishFragment();
    private MsgFragment msgFragment = new MsgFragment();
    private MyFragment myFragment = new MyFragment();
    private Fragment[] fragments = {homeFragment, classifyFragment, publishFragment, msgFragment, myFragment};
    private FragmentManager manager;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
                Window window = getWindow();
                View decorView = window.getDecorView();
                //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
                //导航栏颜色也可以正常设置
//                window.setNavigationBarColor(Color.TRANSPARENT);
            } else {
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
                int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
                attributes.flags |= flagTranslucentStatus;
//                attributes.flags |= flagTranslucentNavigation;
                window.setAttributes(attributes);
            }
        }
//        //判断是否登录
//        if (!HttpSpUtils.getInstance().isLogin()) {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }
        return R.layout.activity_main;
    }


    @Override
    public void initData() {
        tabs[0] = tabHome;
        tabs[1] = tabClassify;
        tabs[2] = tabMsg;
        tabs[3] = tabMy;
        initFragment();
        updateFrgm(0);
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (Fragment fragment : fragments) {
            if (!fragment.isAdded()) {
                transaction.add(R.id.fl_main_parent, fragment);
            }
        }
        transaction.commit();
    }

    @OnClick(R.id.tbv_home)
    public void toHome() {
        updateCheck(0);
        updateFrgm(0);
    }

    @OnClick(R.id.tbv_classify)
    public void toClassify() {
        updateCheck(1);
        updateFrgm(1);
    }

    @OnClick(R.id.tbv_msg)
    public void toMsg() {
        updateCheck(2);
        updateFrgm(3);

    }

    @OnClick(R.id.tbv_my)
    public void toMy() {
        updateCheck(3);
        updateFrgm(4);
    }

    @OnClick(R.id.iv_publish)
    public void toPublish() {
        if (!ivPublish.isChecked()) {
            ivPublish.setChecked(true);
            updateCheck(4);
            updateFrgm(2);
        }
    }

    /**
     * 改变5个标签的状态
     *
     * @param index
     */
    private void updateCheck(int index) {
        /**
         * 自己被选中
         */
        if (index < tabs.length) {
            tabs[index].setChecked(true);
            //需要将发布复位
            if (ivPublish.isChecked()) {
                ivPublish.setChecked(false);
            }
        }
        /**
         * 其他变为未选中
         */
        for (int i = 0; i < tabs.length; i++) {
            if (i != index) {
                tabs[i].setChecked(false);
            }
        }
    }

    /**
     * 切换fragment
     *
     * @param currentTabIndex
     */
    private void updateFrgm(int currentTabIndex) {
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < fragments.length; i++) {
            if (i == currentTabIndex) {
                transaction.show(fragments[currentTabIndex]);
            } else {
                transaction.hide(fragments[i]);
            }
        }
        transaction.commit();
    }
}

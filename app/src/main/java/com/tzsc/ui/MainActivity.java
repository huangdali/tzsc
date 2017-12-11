package com.tzsc.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.hdl.elog.ELog;
import com.httplib.utils.HttpSpUtils;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.ui.classify.ClassifyFragment;
import com.tzsc.ui.home.HomeFragment;
import com.tzsc.ui.login.LoginActivity;
import com.tzsc.ui.msg.ChatActivity;
import com.tzsc.ui.my.MyFragment;
import com.tzsc.ui.publish.PublishFragment;
import com.tzsc.widget.CircleBgImageView;
import com.tzsc.widget.TabItemView;

import java.util.List;

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
//    private MsgFragment msgFragment = new MsgFragment();
    private EaseConversationListFragment msgListFragment = new EaseConversationListFragment();
    private MyFragment myFragment = new MyFragment();
    private Fragment[] fragments = {homeFragment, classifyFragment, publishFragment, msgListFragment, myFragment};
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
        //判断是否登录
        if (!HttpSpUtils.getInstance().isLogin()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
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
        initHXMessage();
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
    private void initHXMessage() {
        EMMessageListener  msgListener = new EMMessageListener() {

            @Override
            public void onMessageReceived(final List<EMMessage> messages) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        msgListFragment.refresh();
//                        if (currentTabIndex != 3) {
//                            mUnReadMsg.setVisibility(View.VISIBLE);
//                        }
                    }
                });
            }

            @Override
            public void onCmdMessageReceived(List<EMMessage> messages) {
                //收到透传消息
            }

            @Override
            public void onMessageRead(List<EMMessage> messages) {
                //收到已读回执
            }

            @Override
            public void onMessageDelivered(List<EMMessage> message) {
                //收到已送达回执
            }

            @Override
            public void onMessageRecalled(List<EMMessage> messages) {
                //消息被撤回
            }

            @Override
            public void onMessageChanged(EMMessage message, Object change) {
                //消息状态变动
            }
        };
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        msgListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {

            @Override
            public void onListItemClicked(EMConversation conversation) {

                ChatActivity.startActivity(mContext, conversation.conversationId(), null);
            }

            @Override
            public void onListItemLongClicked(final EMConversation conversation) {
//                //删除和某个user的整个的聊天记录（包括本地）
//                ConfirmDialog.newInstance("1", "是否确定要删除当前对话？", new ConfirmOkCallBack() {
//                    @Override
//                    public void onOk() {
//                        EMClient.getInstance().chatManager()
//                                .deleteConversation(conversation.conversationId(), true);
//                        msgFrag.refresh();
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                })
//                        .setMargin(60)
//                        .setOutCancel(false)
//                        .show(getSupportFragmentManager());
            }
        });
        EMClient.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {


            }

            @Override
            public void onDisconnected(int error) {
                ELog.e("-------"+error);
                if (error == EMError.USER_LOGIN_ANOTHER_DEVICE) {
                    showMsg("您的账号在别处登录了");
                    ELog.e("您的账号在别处登录了");
                    startActivity(new Intent(mContext,LoginActivity.class));
                    finish();
                }
            }
        });
    }
}

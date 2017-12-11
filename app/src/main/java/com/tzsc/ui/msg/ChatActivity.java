package com.tzsc.ui.msg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.tzsc.R;
import com.tzsc.base.BaseMvpActivity;
import com.tzsc.base.BasePresenter;


/**
 * 聊天界面
 */
public class ChatActivity extends BaseMvpActivity {

    private static String toUser;
//    private static Goods curGoodsInfo;
    private static String curUrl;

//    @Override
//    public void bindView() {
//        setContentView(R.layout.activity_chat);
//
//        EaseChatFragment chatFragment = new EaseChatFragment();
//        chatFragment.setOnGoodsOperate(new OnGoodsOperate() {
//            @Override
//            public void onGoodsClick(String goodsInfo) {
//                ELog.e("goodsInfo=" + goodsInfo);
//                Goods goods = new Gson().fromJson(goodsInfo, Goods.class);
//                GoodsDetailUrlActivity.startActivity(mContext, goods);
//            }
//
//            @Override
//            public void onClickGoodsUrl(String goodsUrl) {
//                //处理网址 获取指定信息 跳转到指定界面
//                Goods goods = new Goods();
//                //获取goods_id
//                String[] temp = goodsUrl.split("goods_id");
//                if (temp.length == 2) {
//                    String[] split = temp[1].split("/");
//                    ELog.e("聊天中 这个商品id点击了" + split[1]);
//                    if (split.length > 2) {
//                        String te = split[1];
//                        if (!TextUtils.isEmpty(te) && TextUtils.isDigitsOnly(te)) {
//                            goods.setGoods_id(Integer.parseInt(split[1]));
//                            GoodsDetailUrlActivity.startActivity(mContext, goods);
//                        }
//                    }
//                }
//            }
//        });

//        Bundle bundle = new Bundle();
//        bundle.putString(EaseConstant.EXTRA_USER_ID, toUser);
//        String goods = new Gson().toJson(curGoodsInfo, Goods.class);
//        bundle.putString(EaseConstant.EXTRA_USER_GOODS, goods);
//        bundle.putString(EaseConstant.EXTRA_USER_GOODS_URL, curUrl);
//        chatFragment.setArguments(bundle);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.fl_chat, chatFragment, "chat")
//                .commit();
//    }

    public static void startActivity(Context context, String toChatUser, String url) {
        context.startActivity(new Intent(context, ChatActivity.class));
//        curGoodsInfo = goodsInfo;
        toUser = toChatUser;
        curUrl = url;
    }


    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toUser = null;
//        curGoodsInfo = null;
        curUrl = null;
    }

    @Override
    public CharSequence setTitleText() {
        return "聊天";
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chat;
    }
    @Override
    public void initData() {
        EaseChatFragment chatFragment = new EaseChatFragment();
//        chatFragment.setOnGoodsOperate(new OnGoodsOperate() {
//            @Override
//            public void onGoodsClick(String goodsInfo) {
//                ELog.e("goodsInfo=" + goodsInfo);
//                Goods goods = new Gson().fromJson(goodsInfo, Goods.class);
//                GoodsDetailUrlActivity.startActivity(mContext, goods);
//            }
//
//            @Override
//            public void onClickGoodsUrl(String goodsUrl) {
//                //处理网址 获取指定信息 跳转到指定界面
//                Goods goods = new Goods();
//                //获取goods_id
//                String[] temp = goodsUrl.split("goods_id");
//                if (temp.length == 2) {
//                    String[] split = temp[1].split("/");
//                    ELog.e("聊天中 这个商品id点击了" + split[1]);
//                    if (split.length > 2) {
//                        String te = split[1];
//                        if (!TextUtils.isEmpty(te) && TextUtils.isDigitsOnly(te)) {
//                            goods.setGoods_id(Integer.parseInt(split[1]));
//                            GoodsDetailUrlActivity.startActivity(mContext, goods);
//                        }
//                    }
//                }
//            }
//        });

        Bundle bundle = new Bundle();
        bundle.putString(EaseConstant.EXTRA_USER_ID, toUser);
//        String goods = new Gson().toJson(curGoodsInfo, Goods.class);
//        bundle.putString(EaseConstant.EXTRA_USER_GOODS, goods);
//        bundle.putString(EaseConstant.EXTRA_USER_GOODS_URL, curUrl);
        chatFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_chat, chatFragment, "chat")
                .commit();
    }
}
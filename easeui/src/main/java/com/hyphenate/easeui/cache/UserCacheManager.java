package com.hyphenate.easeui.cache;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.hdl.myhttputils.MyHttpUtils;
import com.hdl.myhttputils.bean.CommCallback;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.bean_cus.Goods;
import com.hyphenate.easeui.bean_cus.User;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.exceptions.HyphenateException;
import com.j256.ormlite.dao.Dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户缓存管理类
 * Created by Martin on 2017/4/24.
 */
public class UserCacheManager {

    /**
     * 消息扩展属性
     */
    private static final String kChatUserId = "ChatUserId";// 环信ID
    private static final String kChatUserNick = "ChatUserNick";// 昵称
    private static final String kChatUserPic = "ChatUserPic";// 头像Url

    private static final String kChatToUserId = "ChatToUserId";//聊天对象 环信ID
    private static final String kChatToUserNick = "ChatToUserNick";// 昵称
    private static final String kChatToUserPic = "ChatToUserPic";// 头像Url

    private static final String kChatGoodsJson = "goodsInfo";// 商品json数据

    /**
     * 获取所有用户信息
     *
     * @return
     */
    public static List<UserCacheInfo> getAll() {
        Dao<UserCacheInfo, Integer> dao = SqliteHelper.getInstance().getUserDao();
        try {
            return dao.queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户环信ID
     * @return
     */
    public static UserCacheInfo get(final String userId) {
        UserCacheInfo info = null;

        // 如果本地缓存不存在或者过期，则从存储服务器获取
        if (notExistedOrExpired(userId)) {
            // TODO: 2017/9/21 显示昵称修改，在主项目中进行，主项目中获取到要交易的用户信息，然后保存到本地数据库
            MyHttpUtils.build()//构建myhttputils
                    .url(EaseConstant.userInfo_url)//获取ip的url
                    .addParam("v", "1.0.1")//请求参数
                    .addParam("token", userId)//请求参数
                    .setJavaBean(User.class)//设置请求结果对应的java对象
                    .onExecute(new CommCallback<User>() {
                        @Override
                        public void onSucceed(User user) {
                            String nickName = user.getNickname();
                            String pic = user.getHead_pic();
                            save(userId, nickName, pic);
                        }

                        @Override
                        public void onFailed(Throwable throwable) {
                        }
                    });
        }

        // 从本地缓存中获取用户数据
        info = getFromCache(userId);
        if (info != null) {
            String nickName = info.getNickName();
            info.setNickName(TextUtils.isEmpty(nickName) ? "对方没有设置昵称" : nickName);
        }

        return info;
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户环信ID
     * @return
     */
    public static UserCacheInfo getFromCache(String userId) {

        try {
            Dao<UserCacheInfo, Integer> dao = SqliteHelper.getInstance().getUserDao();
            UserCacheInfo model = dao.queryBuilder().where().eq("userId", userId).queryForFirst();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    public static EaseUser getEaseUser(String userId) {

        UserCacheInfo user = get(userId);
        if (user == null) return null;

        EaseUser easeUser = new EaseUser(userId);
        easeUser.setAvatar(user.getAvatarUrl());
        easeUser.setNickname(user.getNickName());

        return easeUser;
    }

    /**
     * 用户是否存在
     *
     * @param userId 用户环信ID
     * @return
     */
    public static boolean isExisted(String userId) {
        Dao<UserCacheInfo, Integer> dao = SqliteHelper.getInstance().getUserDao();
        try {
            long count = dao.queryBuilder().where().eq("userId", userId).countOf();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 用户不存在或已过期
     *
     * @param userId 用户环信ID
     * @return
     */
    public static boolean notExistedOrExpired(String userId) {
        Dao<UserCacheInfo, Integer> dao = SqliteHelper.getInstance().getUserDao();
        try {
            long count = dao.queryBuilder().where()
                    .eq("userId", userId).and()
                    .gt("expiredDate", new Date().getTime())
                    .countOf();
            return count <= 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 缓存用户信息
     *
     * @param userId    用户环信ID
     * @param avatarUrl 头像Url
     * @param nickName  昵称
     * @return
     */
    public static boolean save(String userId, String nickName, String avatarUrl) {
        try {
            Dao<UserCacheInfo, Integer> dao = SqliteHelper.getInstance().getUserDao();
            UserCacheInfo user = getFromCache(userId);
            // 新增
            if (user == null) {
                user = new UserCacheInfo();
            }
            user.setUserId(userId);
            user.setAvatarUrl(avatarUrl);
            user.setNickName(nickName);
            user.setExpiredDate(new Date().getTime() + 24 * 60 * 60 * 1000);// 一天过期，单位：毫秒

            Dao.CreateOrUpdateStatus status = dao.createOrUpdate(user);
            if (status.getNumLinesChanged() > 0) {
                Log.e("UserCacheManager", "操作成功~");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("UserCacheManager", "操作异常~");
        }

        return false;
    }

    /**
     * 更新当前用户的昵称
     *
     * @param nickName 昵称
     */
    public static void updateMyNick(String nickName) {
        UserCacheInfo user = getMyInfo();
        if (user == null) return;

        save(user.getUserId(), nickName, user.getAvatarUrl());
    }


    /**
     * 更新当前用户的头像
     *
     * @param avatarUrl 头像Url（完成路径）
     */
    public static void updateMyAvatar(String avatarUrl) {
        UserCacheInfo user = getMyInfo();
        if (user == null) return;

        save(user.getUserId(), user.getNickName(), avatarUrl);
    }

    /**
     * 缓存用户信息
     *
     * @param model 用户信息
     * @return
     */
    public static boolean save(UserCacheInfo model) {

        if (model == null) return false;

        return save(model.getUserId(), model.getNickName(), model.getAvatarUrl());
    }

    /**
     * 缓存用户信息
     *
     * @param ext 用户信息
     * @return
     */
    public static boolean save(String ext) {
        if (ext == null) return false;

        UserCacheInfo user = UserCacheInfo.parse(ext);
        return save(user);
    }

    /**
     * 缓存用户信息
     *
     * @param ext 消息的扩展属性
     * @return
     */
    public static void save(Map<String, Object> ext) {

        if (ext == null) return;

        try {
            String userId = ext.get(kChatUserId).toString();
            String avatarUrl = ext.get(kChatUserPic).toString();
            String nickName = ext.get(kChatUserNick).toString();

            String goodsInfo = ext.get(kChatGoodsJson).toString();
            if (TextUtils.isEmpty(goodsInfo)) {
                save(userId, nickName, avatarUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前环信用户信息
     *
     * @return
     */
    public static UserCacheInfo getMyInfo() {
        return get(EMClient.getInstance().getCurrentUser());
    }

    /**
     * 获取用户昵称
     *
     * @return
     */
    public static String getMyNickName() {
        UserCacheInfo user = getMyInfo();
        if (user == null) return EMClient.getInstance().getCurrentUser();

        return user.getNickName();
    }

    /**
     * 设置消息的扩展属性
     *
     * @param msg 发送的消息
     */
    public static void setMsgExt(EMMessage msg, String toUserToken) {
        if (msg == null) return;

        UserCacheInfo user = getMyInfo();
        Log.e("tjs", "发送消息时携带》》》》userId=" + user.toString());
        msg.setAttribute(kChatUserId, user.getUserId());
        msg.setAttribute(kChatUserNick, user.getNickName());
        msg.setAttribute(kChatUserPic, user.getAvatarUrl());

        UserCacheInfo toUserCacheInfo = get(toUserToken);
        if (toUserCacheInfo != null) {
            Log.e("tjs", "ios》》》》扩展=" + toUserCacheInfo.toString());
            msg.setAttribute(kChatToUserId, toUserCacheInfo.getUserId());
            msg.setAttribute(kChatToUserNick, toUserCacheInfo.getNickName());
            msg.setAttribute(kChatToUserPic, toUserCacheInfo.getAvatarUrl());
        }

//        if (goods != null) {
//            msg.setAttribute(kChatGoodsJson, new Gson().toJson(goods));
//        }
    }

    /**
     * 根据消息获取商品的扩展信息
     *
     * @param msg
     * @return
     * @throws HyphenateException
     */
    public static Goods getMsgGoods(EMMessage msg) {
        if (msg == null) {
            return null;
        }
        try {
            String goodsJson = msg.getStringAttribute(kChatGoodsJson);
            if (TextUtils.isEmpty(goodsJson)) {
                return null;
            }
            return new Gson().fromJson(goodsJson, Goods.class);
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getUserData(EMMessage msg) {
        if (msg == null) {
            return null;
        }
        try {
            String uId = msg.getStringAttribute(kChatUserId);
            String uNick = msg.getStringAttribute(kChatUserNick);
            String uPic = msg.getStringAttribute(kChatUserPic);
            return uId + ">>>>>>" + uNick + ">>>>>>>" + uPic;
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取登录用户的昵称头像
     *
     * @return
     */
    public static String getMyInfoStr() {

        Map<String, Object> map = new HashMap<>();

        UserCacheInfo user = getMyInfo();
        map.put(kChatUserId, user.getUserId());
        map.put(kChatUserNick, user.getNickName());
        map.put(kChatUserPic, user.getAvatarUrl());

        return new Gson().toJson(map);
    }
}

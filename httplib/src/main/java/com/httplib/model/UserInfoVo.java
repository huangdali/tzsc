package com.httplib.model;

/**
 * 用户信息,登录成功之后服务器返回的
 * Created by HDL on 2017/11/20.
 *
 * @author HDL
 */

public class UserInfoVo {
    private int uId;
    private String uNickName;
    private boolean uSex;
    private String uPhone;
    private String token;
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuNickName() {
        return uNickName;
    }

    public void setuNickName(String uNickName) {
        this.uNickName = uNickName;
    }

    public boolean isuSex() {
        return uSex;
    }

    public void setuSex(boolean uSex) {
        this.uSex = uSex;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfoVo{" +
                "uId=" + uId +
                ", uNickName='" + uNickName + '\'' +
                ", uSex=" + uSex +
                ", uPhone='" + uPhone + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

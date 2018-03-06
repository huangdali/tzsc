package com.httplib.model;

/**
 * Created by HDL on 2018/2/28.
 *
 * @author HDL
 */

public class GoodsVo {
    /**
     * uPhone : 15685188016
     * uNickname : 李高业
     * tName : 图书教材
     * url : /users/15685188016/15685188016_1464312436026.png,/users/15685188016/15685188016_1464312436370.png,/users/15685188016/15685188016_1464312436760.png,/users/15685188016/15685188016_1464312437245.png,/users/15685188016/15685188016_1464312437792.png
     * gId : 166
     * gTitle : 软件水平考试， 中级， 软件设计师
     * uId : 14
     * gPrice : 30
     * gOldprice : 130
     * gPublishtype : 0
     * gState : false
     * gPinkage : false
     * gUrgent : false
     * gAddress : 樱花苑1栋
     * gDeadline : null
     * gBrowcount : 6
     * gNice : 0
     * gTime : 1464312435464
     * tId : 8
     * gDesc : 软件设计师教程和软件设计师2009-2012试题分析与解答。8成新， 另外，可送软考初级程序员教程和习题集。有需要的联系
     * gMaxPrice : 15
     */

    private String uPhone;
    private String uNickname;
    private String tName;
    private String url;
    private int gId;
    private String gTitle;
    private int uId;
    private int gPrice;
    private int gOldprice;
    private int gPublishtype;
    private boolean gState;
    private boolean gPinkage;
    private boolean gUrgent;
    private String gAddress;
    private String gDeadline;
    private int gBrowcount;
    private int gNice;
    private String gTime;
    private int tId;
    private String gDesc;
    private String gMaxPrice;

    public String getUPhone() {
        return uPhone;
    }

    public void setUPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getUNickname() {
        return uNickname;
    }

    public void setUNickname(String uNickname) {
        this.uNickname = uNickname;
    }

    public String getTName() {
        return tName;
    }

    public void setTName(String tName) {
        this.tName = tName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getGId() {
        return gId;
    }

    public void setGId(int gId) {
        this.gId = gId;
    }

    public String getGTitle() {
        return gTitle;
    }

    public void setGTitle(String gTitle) {
        this.gTitle = gTitle;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public int getGPrice() {
        return gPrice;
    }

    public void setGPrice(int gPrice) {
        this.gPrice = gPrice;
    }

    public int getGOldprice() {
        return gOldprice;
    }

    public void setGOldprice(int gOldprice) {
        this.gOldprice = gOldprice;
    }

    public int getGPublishtype() {
        return gPublishtype;
    }

    public void setGPublishtype(int gPublishtype) {
        this.gPublishtype = gPublishtype;
    }

    public boolean isGState() {
        return gState;
    }

    public void setGState(boolean gState) {
        this.gState = gState;
    }

    public boolean isGPinkage() {
        return gPinkage;
    }

    public void setGPinkage(boolean gPinkage) {
        this.gPinkage = gPinkage;
    }

    public boolean isGUrgent() {
        return gUrgent;
    }

    public void setGUrgent(boolean gUrgent) {
        this.gUrgent = gUrgent;
    }

    public String getGAddress() {
        return gAddress;
    }

    public void setGAddress(String gAddress) {
        this.gAddress = gAddress;
    }

    public String getGDeadline() {
        return gDeadline;
    }

    public void setGDeadline(String gDeadline) {
        this.gDeadline = gDeadline;
    }

    public int getGBrowcount() {
        return gBrowcount;
    }

    public void setGBrowcount(int gBrowcount) {
        this.gBrowcount = gBrowcount;
    }

    public int getGNice() {
        return gNice;
    }

    public void setGNice(int gNice) {
        this.gNice = gNice;
    }

    public String getGTime() {
        return gTime;
    }

    public void setGTime(String gTime) {
        this.gTime = gTime;
    }

    public int getTId() {
        return tId;
    }

    public void setTId(int tId) {
        this.tId = tId;
    }

    public String getGDesc() {
        return gDesc;
    }

    public void setGDesc(String gDesc) {
        this.gDesc = gDesc;
    }

    public String getGMaxPrice() {
        return gMaxPrice;
    }

    public void setGMaxPrice(String gMaxPrice) {
        this.gMaxPrice = gMaxPrice;
    }

    @Override
    public String toString() {
        return "GoodsVo{" +
                "uPhone='" + uPhone + '\'' +
                ", uNickname='" + uNickname + '\'' +
                ", tName='" + tName + '\'' +
                ", url='" + url + '\'' +
                ", gId=" + gId +
                ", gTitle='" + gTitle + '\'' +
                ", uId=" + uId +
                ", gPrice=" + gPrice +
                ", gOldprice=" + gOldprice +
                ", gPublishtype=" + gPublishtype +
                ", gState=" + gState +
                ", gPinkage=" + gPinkage +
                ", gUrgent=" + gUrgent +
                ", gAddress='" + gAddress + '\'' +
                ", gDeadline='" + gDeadline + '\'' +
                ", gBrowcount=" + gBrowcount +
                ", gNice=" + gNice +
                ", gTime='" + gTime + '\'' +
                ", tId=" + tId +
                ", gDesc='" + gDesc + '\'' +
                ", gMaxPrice='" + gMaxPrice + '\'' +
                '}';
    }
}

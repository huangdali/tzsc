package com.tzsc.entity;

/**
 * 分类信息
 * Created by HDL on 2017/12/10.
 *
 * @author HDL
 */

public class ClassifyVo {
    /**
     * 图标
     */
    private int iconResId;
    /**
     * 标题
     */
    private String title;
    /**
     * 描述
     */
    private String desc;

    public ClassifyVo() {
    }

    public ClassifyVo(int iconResId, String title, String desc) {
        this.iconResId = iconResId;
        this.title = title;
        this.desc = desc;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

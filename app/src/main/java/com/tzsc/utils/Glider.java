package com.tzsc.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tzsc.R;
import com.tzsc.base.MyApp;

/**
 * 图片加载
 * Created by HDL on 2017/9/29.
 *
 * @author HDL
 */

public class Glider {
    private static Glider mGlider = null;

    private Glider() {
    }

    public static Glider getInstance() {
        if (mGlider == null) {
            synchronized (Glider.class) {
                if (mGlider == null) {
                    mGlider = new Glider();
                }
            }
        }
        return mGlider;
    }

    /**
     * 加载图片
     *
     * @param path
     * @param target
     */
    public static void load(String path, ImageView target) {
        Glide.with(MyApp.app)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_default)
                .into(target);
    }

    /**
     * 加载图片
     *
     * @param path
     * @param target
     */
    public static void load(Object path, ImageView target) {
        Glide.with(MyApp.app)
                .load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_default)
                .into(target);
    }

    /**
     * 加载图片，资源文件
     *
     * @param resId  资源id
     * @param target
     */
    public static void load(int resId, ImageView target) {
        Glide.with(MyApp.app)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_default)
                .error(R.mipmap.ic_default)
                .into(target);
    }

}

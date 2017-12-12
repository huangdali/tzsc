package com.tzsc.ui.goodsdetail;

import android.content.Context;
import android.widget.ImageView;

import com.tzsc.R;
import com.tzsc.base.BaseActivity;
import com.tzsc.utils.Glider;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;

import butterknife.BindView;

public class GoodsDetailActivity extends BaseActivity {
    /**
     * 广告轮播
     */
    @BindView(R.id.home_banner)
    Banner banner;

    @Override
    public boolean isNeedBack() {
        return true;
    }

    @Override
    public CharSequence setTitleText() {
        return "商品详情";
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initData() {
        //设置图片加载器
        banner.setImageLoader(new ImageLoader() {

            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glider.getInstance().load(path, imageView);
            }
        });
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        //设置图片集合
        banner.setImages(Arrays.asList("http://img.zcool.cn/community/01ea0657cce2e00000012e7e322bc9.jpg@900w_1l_2o_100sh.jpg",
                "http://img.zcool.cn/community/0164c45940f898a8012193a3ee8db7.jpg@1280w_1l_2o_100sh.jpg",
                "http://img.zcool.cn/community/01a7ca58df3145a801219c77b4110e.jpg@2o.jpg",
                "http://www.yongxintex.com/Html/images/b1.jpg",
                "http://img.zcool.cn/community/012d3658de20b6a801219c77083959.jpg"));
        banner.setDelayTime(2500);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
